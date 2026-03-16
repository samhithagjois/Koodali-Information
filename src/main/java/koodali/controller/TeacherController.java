package koodali.controller;

import koodali.model.Teacher;
import koodali.model.dto.AttendanceDTO;
import koodali.model.dto.teacherDTO.CreateTeacherDTO;
import koodali.model.dto.teacherDTO.TeacherOverviewDTO;
import koodali.service.AttendanceService;
import koodali.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {

    private final TeacherService teacherService;

    private final AttendanceService attendanceService;

    @Autowired
    public TeacherController(TeacherService teacherService, AttendanceService attendanceService) {
        this.teacherService = teacherService;
        this.attendanceService = attendanceService;
    }

    //DTO-ify

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherOverviewDTO>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeacherOverviewDTOs(), HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherOverviewDTO> getTeacher(@PathVariable String id) {
        return new ResponseEntity<>(teacherService.getTeacherOverviewDTO(id), HttpStatus.OK);
    }

    @PutMapping("/teachers")
    public ResponseEntity<TeacherOverviewDTO> updateTeacher(@RequestBody CreateTeacherDTO updatedTeacher) {
        return new ResponseEntity<>(teacherService.updateTeacherToDTO(updatedTeacher), HttpStatus.OK);
    }


    @PostMapping("/teachers")
    public ResponseEntity<TeacherOverviewDTO> createTeacher(@RequestBody CreateTeacherDTO newTeacher) {
        return new ResponseEntity<>(teacherService.createTeacher(newTeacher), HttpStatus.CREATED);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<TeacherOverviewDTO> deleteTeacher(@PathVariable String id) {
        return new ResponseEntity<>(teacherService.deleteTeacher(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/teachers/attendance")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances(){
        return new ResponseEntity<>(attendanceService.getAllAttendances(),HttpStatus.OK);
    }

    @GetMapping("/teachers/attendance/{classid}")
    public ResponseEntity<List<AttendanceDTO>> getAttendancesForSection(@PathVariable String classid){
        return new ResponseEntity<>(attendanceService.getAttendancesPerSection(classid),HttpStatus.OK);
    }


    @PatchMapping("/teachers/attendance")
    public void updateAttendance(@RequestBody AttendanceDTO dto) {
        attendanceService.updateAttendance(dto.studentId(), dto.date(), dto.present());
    }

    @GetMapping("/teachers/attendance/{id}")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendancesForSection(@PathVariable String id){

        return new ResponseEntity<>(attendanceService.getAttendancesPerSection(id),HttpStatus.OK);
    }

    @GetMapping("/teachers/attendance/{classid}/{studentid}")
    public ResponseEntity<Double> getAttendanceForStudentOfSection(@PathVariable String classid, @PathVariable String studentid){
        return new ResponseEntity<>(attendanceService.calculateAttendanceForStudentOfSection(classid, studentid), HttpStatus.OK);
    }
}
