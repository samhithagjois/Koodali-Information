package koodali.service;

import koodali.model.AttendanceEntity;
import koodali.model.Section;
import koodali.model.Student;
import koodali.model.dto.AttendanceDTO;
import koodali.model.dto.studentDTO.SectionStudentOverviewDTO;
import koodali.repository.AttendanceRepository;
import koodali.service.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    private final StudentService studentService;


    public AttendanceService(AttendanceRepository attendanceRepository, StudentService studentService) {
        this.attendanceRepository = attendanceRepository;
        this.studentService = studentService;
    }

    public double calculateAttendanceForStudentOfSection(String sectionID, String studentID){
        studentService
                .listStudentsInSection(sectionID)
                .stream().map(SectionStudentOverviewDTO::personID)
                .filter(s -> s.equals(studentID))
                .findFirst()
                .orElseThrow(StudentNotFoundException::new);

        return calculateAttendanceForStudent(studentID);

    }

    private double calculateAttendanceForStudent(String studentID) {
        List<LocalDate> dates = attendanceRepository
                .findAll()
                .stream()
                .map(AttendanceEntity::getWeek)
                .distinct()
                .toList();

        long count = attendanceRepository
                .findAll()
                .stream()
                .filter(a -> a.getStudentID().equals(studentID))
                .filter(AttendanceEntity::isAttended)
                .count();

        double attendance =  Math.floorDiv(count, dates.size()) * 100;
        studentService.findByID(studentID).setAttendance(attendance);


        return attendance;
    }


    public AttendanceDTO updateAttendance(String studentID,LocalDate week, boolean attended){
        AttendanceEntity entity =
                attendanceRepository.findAll()
                        .stream()
                        .filter(e -> e.getStudentID().equals(studentID) && e.getWeek().isEqual(week))
                        .findFirst()
                        .orElseThrow();
        entity.setAttended(attended);
        return new AttendanceDTO(entity.getId(),entity.getStudentID(), entity.getName(), entity.getWeek(),entity.isAttended());


    }



    public List<AttendanceDTO> getAttendancesPerSection(String sectionName){
        List<String> studentIDsOfSection = studentService
                .getAll()
                .stream()
                .filter(s -> s
                        .getSection()
                        .equals(sectionName))
                .map(Student::getID).toList();

        return attendanceRepository
                .findAll()
                .stream()
                .filter(a -> studentIDsOfSection
                        .contains(a.getStudentID()))
                .map(this::entityToDTO)
                .toList();
    }

    public AttendanceDTO entityToDTO(AttendanceEntity entity){
        return new AttendanceDTO(entity.getId(),entity.getStudentID(),entity.getName(),entity.getWeek(), entity.isAttended());
    }


    public List<AttendanceDTO> getAllAttendances(){
        return attendanceRepository.findAll().stream().map(e -> new AttendanceDTO(e.getId(),e.getStudentID(), e.getName(), e.getWeek(),e.isAttended())).toList();
    }




}

