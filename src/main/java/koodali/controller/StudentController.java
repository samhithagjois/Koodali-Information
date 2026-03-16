package koodali.controller;

import koodali.model.Student;
import koodali.model.dto.studentDTO.CreateStudentDTO;
import koodali.model.dto.studentDTO.StudentOverviewDTO;
import koodali.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*
     * ----------------------------------GET MAPPINGS----------------------------------
     */
    @GetMapping("/students")
    public ResponseEntity<List<StudentOverviewDTO>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudentOverviewDTOs(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentOverviewDTO> getStudent(@PathVariable String id) {
        return new ResponseEntity<>(studentService.findDTOByID(id), HttpStatus.OK);
    }

    /*
     * ----------------------------------PUT MAPPINGS----------------------------------
     */


    @PutMapping("/students/{id}")
    public ResponseEntity<StudentOverviewDTO> updateStudent(@PathVariable String id,@RequestBody StudentOverviewDTO updatedStudent) {
        return new ResponseEntity<>(studentService.updateStudent(id,updatedStudent), HttpStatus.OK);
    }

    /*
     * ----------------------------------POST MAPPINGS----------------------------------
     */
    @PostMapping("/students")
    public ResponseEntity<StudentOverviewDTO> createStudent( @RequestBody CreateStudentDTO newStudent) {
              return new ResponseEntity<>(studentService.createStudent(newStudent), HttpStatus.CREATED);
    }

    /*
     * ----------------------------------DELETE MAPPINGS----------------------------------
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentOverviewDTO> deleteStudent(@PathVariable String id) {
        return new ResponseEntity<>(studentService.deleteById(id), HttpStatus.NO_CONTENT);
    }
}
