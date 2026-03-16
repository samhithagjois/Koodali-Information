package koodali.service;

import koodali.model.Section;
import koodali.model.Student;
import koodali.model.Teacher;
import koodali.model.dto.SectionDTO;
import koodali.model.dto.teacherDTO.CreateTeacherDTO;
import koodali.model.dto.teacherDTO.SectionTeacherOverviewDTO;
import koodali.model.dto.teacherDTO.TeacherOverviewDTO;
import koodali.repository.TeacherRepository;
import koodali.service.exceptions.TeacherNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService extends PersonService<Teacher> {
    private final TeacherRepository teacherRepo;

    private final SectionService sectionService;


    public TeacherService(TeacherRepository teacherRepo, SectionService sectionService) {
        super(teacherRepo);
        this.teacherRepo = teacherRepo;

        this.sectionService = sectionService;
    }

private SectionTeacherOverviewDTO teacherToSectionTeacherOverviewDTO(Teacher teacher){
        return new SectionTeacherOverviewDTO(teacher.getID(),teacher.getFirstName()+teacher.getLastName(),teacher.getJoinDate());
}

    public Teacher findByID(String teacherID) {
        return teacherRepo
                .findById(teacherID)
                .orElseThrow(TeacherNotFoundException::new);
    }


    public Teacher findTeacherbyName(String firstName, String lastName) {
        return teacherRepo
                .findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(TeacherNotFoundException::new);
    }

    public TeacherOverviewDTO getTeacherOverviewDTO(String id){
        return teacherToTeacherOverviewDTO(findByID(id));
    }

    public List<TeacherOverviewDTO> getAllTeacherOverviewDTOs(){
        return teacherRepo
                .findAll()
                .stream()
                .map(this::teacherToTeacherOverviewDTO)
                .collect(Collectors.toList());
    }

    public TeacherOverviewDTO updateTeacherToDTO(CreateTeacherDTO dto){
        Teacher t = findTeacherbyName(dto.firstName(),dto.lastName());
        //String firstName,
        //                               String lastName,
        //                               String city,
        //                               String pinCode,
        //                               String fullPostalAddress,
        //                               String country,
        //                               String phoneNumber,
        //                               String whatsappNumber,
        //                               String sections
        t.setCity(dto.city());
        t.setPinCode(dto.pinCode());
        t.setFullPostalAdress(dto.fullPostalAddress());
        t.setCountry(dto.country());
        List<String> sections = Arrays.stream(dto.sections().split(";")).toList();
        t.setSections(sections);

        return teacherToTeacherOverviewDTO(t);

    }

    public TeacherOverviewDTO createTeacher(CreateTeacherDTO dto) {
       return teacherToTeacherOverviewDTO(teacherfromCreateTeacherDTO(dto));
    }

    public Teacher teacherfromCreateTeacherDTO(CreateTeacherDTO dto) {
       return teacherRepo.save(
                new Teacher(

                        dto.firstName()
                        ,dto.lastName()
                )
        );

    }

    public TeacherOverviewDTO teacherToTeacherOverviewDTO(Teacher teacher){
        String sectionsOfTeacher = teacher.getSections().stream().reduce(String::concat).orElseThrow();
        return new TeacherOverviewDTO(teacher.getID()
                ,teacher.getFirstName()
                , teacher.getLastName()
                , teacher.getJoinDate()
                ,sectionsOfTeacher);
    }


    /*public Teacher createTeacher(String teacherId, String firstName, String lastName, List<String> sectionIDs) {

        List<String> teacherClasses = new ArrayList<>();
        for (String s : sectionIDs) {
            Section sectionByID = sectionService.getSectionByName(s);
            teacherClasses.add(sectionByID.getName());

        }


        Teacher teacher = new Teacher(teacherId, firstName, lastName, teacherClasses);

        for (String name : teacherClasses) {
            sectionService.getSectionByName(name).getTeachers().put(teacher.getID(), teacher);
        }

        return teacherRepo.save(teacher);

    }*/

    public List<String> addSectionToTeacher(String sectionId, String teacherId){
       Teacher t =  teacherRepo.getReferenceById(teacherId);
    t.getSections().add(sectionService.returnSectionNameIfValid(sectionId));
        return t.getSections();

    }

    public boolean createTeachersFromList(List<Teacher> Teachers) {
        for (Teacher teacher : Teachers) {
            teacherRepo.save(teacher);
            sectionService
                    .getAllSections()
                    .stream()
                    .filter(
                            section -> teacher.getSections().contains(section.getName()))
                    .findFirst()
                    .ifPresent(section -> section
                            .getTeachers()
                            .put(teacher.getID(), teacher));
        }
        return true;
    }

    public TeacherOverviewDTO deleteTeacher(String id){
        Teacher t = findByID(id);
        teacherRepo.deleteById(id);
        return teacherToTeacherOverviewDTO(t);
    }


    public List<SectionTeacherOverviewDTO> listTeachersInSection(String sectionID) {

        Section section = sectionService.getSectionByName(sectionID);
        return section.getTeachers().values().stream().map(this::teacherToSectionTeacherOverviewDTO).toList();

    }

}
