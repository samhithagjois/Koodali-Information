package koodali.controller;

import koodali.model.Student;
import koodali.model.dto.SectionDTO;
import koodali.model.dto.studentDTO.SectionStudentOverviewDTO;
import koodali.model.dto.studentDTO.StudentOverviewDTO;
import koodali.model.dto.teacherDTO.SectionTeacherOverviewDTO;
import koodali.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    /**
     * All the Service classes needed for our Admin Operations
     */
    private final AdminOperationService adminOperationService;

    private final AdministratorService adminService;
    private final StudentService studentService;
    private final SectionService sectionService;
    private final TeacherService teacherService;

    @Autowired
    public AdminController(AdminOperationService adminOperationService,
                           AdministratorService adminService,
                           StudentService studentService,
                           SectionService sectionService,
                           TeacherService teacherService) {

        this.adminOperationService = adminOperationService;
        this.adminService = adminService;
        this.studentService = studentService;
        this.sectionService = sectionService;
        this.teacherService = teacherService;
    }

    //___________________________

    /**
     * this method gets all sections and displays them in a List
     */
    @GetMapping("admin/sections")
    public ResponseEntity<List<SectionDTO>> manageSections() {
        return new ResponseEntity<>(sectionService.getAllSectionsDTO(), HttpStatus.OK);
    }

    /**
     * this method selects a section
     *
     * @param classId the name of the class
     * @return Section
     */
    @GetMapping("admin/sections/{classId}")
    public ResponseEntity<SectionDTO> selectSection(@PathVariable String classId) {

        return new ResponseEntity<>(sectionService.getSectionDTOByName(classId), HttpStatus.OK);

    }


    @GetMapping("admin/sections/{classId}/students")
    public ResponseEntity<List<SectionStudentOverviewDTO>> listStudentsOfSection(@PathVariable String classId) {

        return new ResponseEntity<>(studentService.listStudentsInSection(classId), HttpStatus.OK);

    }

    @GetMapping("admin/sections/{classId}/teachers")
    public ResponseEntity<List<SectionTeacherOverviewDTO>> listTeachersOfSection(@PathVariable String classId) {

        return new ResponseEntity<>(teacherService.listTeachersInSection(classId), HttpStatus.OK);

    }


    /**
     * this method selects a Student from the section
     *
     * @param classId   class name
     * @param studentId student ID
     */
    @GetMapping("admin/section/{classId}/students/{studentId}")
    public ResponseEntity<SectionStudentOverviewDTO> selectStudentFromSection(@PathVariable String classId, @PathVariable String studentId) {

        return new ResponseEntity<>(studentService.findStudentOfSection(classId, studentId), HttpStatus.OK);

    }

    //-------------

    /**
     * remove a Section from the Section repository
     *
     * @param classId class name
     */
    @DeleteMapping("admin/sections/{classId}")
    public ResponseEntity<SectionDTO> removeSection(@PathVariable String classId) {

        return new ResponseEntity<>(sectionService.deleteSectionbyName(classId), HttpStatus.OK);
    }

//-------------

    /**
     * updates the section that exists already
     *
     * @param updatedSection Section object that is updated
     */
    @PutMapping("admin/sections/{id}")
    public ResponseEntity<SectionDTO> updateSection(@PathVariable int id,@RequestBody SectionDTO updatedSection) {

        return new ResponseEntity<>(sectionService.updateSection(updatedSection,id), HttpStatus.OK);
    }


    /**
     * reassigns a student to the new section.
     *
     * @param sectionId name of the new class
     * @param studentId ID of the student
     */
    @PutMapping("admin/sections/{sectionId}/students/{studentId}")
    ResponseEntity<SectionStudentOverviewDTO> reassignStudent(@PathVariable String sectionId, @PathVariable String studentId) {
        SectionStudentOverviewDTO s = studentService.StudentToSectionStudentOverviewDTO(adminOperationService.reassignStudentToSection(studentId, sectionId));
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PutMapping("admin/sections/{sectionId}/students")
    ResponseEntity<SectionStudentOverviewDTO> addStudentToSection(@PathVariable String sectionId, String studentId) {
        SectionStudentOverviewDTO s = studentService.StudentToSectionStudentOverviewDTO(adminOperationService.addStudentToSection(studentId, sectionId));
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    //---------------------------------------------------------------

    /**
     * Post mappings
     * */


    //-----------------------------------------------------------

    /**
     * getters
     */


    // logic :
    // AdminController receives form
    // call studentService.createStudent(... all above)
    // student koodali.service checks for duplicates, assigns to Section with s.setSection
    //StudentRepository.save


    //2 : import from excel
    // click on "import from excel file", choose excel file from local
    // call StudentExcelService to extract student info from excel file and create student objects
    // call StudentService to save said objects in StudentRepository
    //StudentRepository adds student


}
