package koodali.service;

import koodali.model.*;
import koodali.service.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOperationService {

    /**
     * all the repositories. Here is where most of the crossover work betwwen these repositories will
     * be happening.
     */
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final AdministratorService adminService;
    private final SectionService sectionService;

    public AdminOperationService(
            StudentService studentService,
            TeacherService teacherService,
            AdministratorService adminService,
            SectionService sectionService
    ) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.adminService = adminService;
        this.sectionService = sectionService;
    }

    /**
     * checks the permission of the admin.
     * So the admin permission list is two  letters and corresponds with the first two letters of the section
     * we compare these two and if its not equal we throw a IllegalAdminActionException.
     * This is an internal method. I don't think this should be visible outside
     *
     * @param admin   administrator
     * @param section Section to check
     * @return true or false
     * @throws IllegalAdminActionException if for example a Munich admin tries changing Ingolstadt
     */
    public boolean checkPermissionToModify(Administrator admin, Section section) throws IllegalAdminActionException {
        String area = section.getName().substring(0, 2);
        for (AdminPermissions permission : admin.getPermissions()) {
            if (!permission.toString().equals(area)) {
                throw new IllegalAdminActionException();
            }
        }
        return true;
    }

    /**
     * helper methods to reduce code duplication! (I sound super fancy now, don't I)
     * returns the Person/Section it finds
     */
    private Administrator findAdmin(String adminID) {
        return adminService.findByID(adminID);
    }

    private Teacher findTeacher(String teacherID) {
        return teacherService.findByID(teacherID);
    }

    private Student findStudent(String studentID) {
        return studentService.findByID(studentID);
    }

    private Section findSection(String sectionName) {
        return sectionService.getSectionByName(sectionName);
    }

    /**
     * creates/adds person to system
     *
     * @param person either the Student, Admin or Teacher to be added to the system
     * @return the person added
     * @throws DuplicatePersonException if the person has already been added
     */
    public Person addPersonToSystem(Person person) {
        if (person instanceof Student s) {
            if (studentService.contains(s)) {
                throw new DuplicatePersonException();

            }
            return studentService.save(s);

        }
        if (person instanceof Teacher t) {
            if (teacherService.contains(t)) {
                throw new DuplicatePersonException();

            }
            return teacherService.save(t);
        }
        if (person instanceof Administrator a) {
            if (adminService.contains(a)) {
                throw new DuplicatePersonException();
            }
            return adminService.save(a);
        } else throw new IllegalStateException("Unsupported person type: " + person.getClass());
    }

    /**
     * adds a List of person(s) to the System.
     *
     * @param personList a List of people
     * @return the list of people added.
     */
    public List<Person> addListOfPersonsToSystem(List<Person> personList) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            result.add(addPersonToSystem(person));
        }
        return result;
    }


    /**
     * adds Student to Section. first we check if we can find all we have. Then we check the permission of the admin
     * to edit this section. For example, an Erlangen admin cannot edi the Munich Intermediate 1 section.
     * Then, we set the students' section  to the section, and we add the student to the sections' student list
     * we then update the repositories.
     * So with a find - check problem - add - update structure we will go through most of this Service
     *
     *
     * @param studentID students ID
     * @param sectionID as String! eventhough in Student we have ClassNames_DEPR, we have to find and validate the section
     *                  In our case, sectionID is simply the name of the section
     * @throws SectionNotFoundException    if Section was not found (wich means the id is wrong!)
     * @throws StudentNotFoundException    if the student was not found
     *
     */
    public Student addStudentToSection(String studentID, String sectionID) {

        Student student = findStudent(studentID);


        Section section = findSection(sectionID);

        student.setSection(section.getName());
        section.getStudents().put(student.getID(), student);
        sectionService.updateSection(section);
        return studentService.save(student);


    }

    /**
     * same game with the teacher, just with a different exception thrown this time!
     */
    public Teacher addTeacherToSection(String teacherID, String sectionID) throws
            SectionNotFoundException,
            AdminNotFoundException,
            IllegalAdminActionException,
            TeacherNotFoundException {



        Section section = findSection(sectionID);

        Teacher teacher = findTeacher(teacherID);

        teacher.setSections(List.of(section.getName()));
        section.getTeachers().put(teacher.getID(), teacher);
        sectionService.updateSection(section);
        return teacherService.save(teacher);


    }


    /**
     * The next two methods reassign students/teachers from their old section into the new one
     * also has List methods
     */
    public Student reassignStudentToSection(String studentID, String newSectionID) {

        Student student = findStudent(studentID);

        Section section = findSection(newSectionID);
        //change the section in the student
        student.setSection(section.getName());
        //add student to new class
        section.getStudents().put(studentID, student);
        //update section
        sectionService.updateSection(section);
        //update student in studentRepo and return
        return studentService.save(student);

    }

    public List<Student> reassignListOfStudentsToSection( List<String> studentIDs, String newSectionID) {
        //create new list to return
        List<Student> students = new ArrayList<>();
        //find the new section to assign the student to
        Section section = findSection(newSectionID);
        for (String id : studentIDs) {
            //find student object
            Student student = findStudent(id);
            //delete student from section
            deleteStudentFromSection( id, student.getSection());
            //add section to student
            student.setSection(section.getName());
            //add student to new class
            section.getStudents().put(student.getID(), student);
            //update section in sectionRepo
            sectionService.updateSection(section);
            students.add(student);
        }
        return students;
    }


    public Teacher reassignTeacherToSection(String adminID, String teacherID, String oldSectionID, String sectionID) {
        Teacher teacher = deleteTeacherFromSection( teacherID, sectionID);
        teacher.getSections().remove(sectionService.getSectionByName(sectionID).getName());
        String className = findSection(sectionID).getName();
        Section section = findSection(sectionID);

        section.getTeachers().put(teacherID, teacher);

        sectionService.updateSection(section);

        return teacherService.save(teacher);

    }


    /**
     * the next two methods remove the teacher/student from the system entirely.
     * This method needs special care because... well it's removing a student from the koodali.repository.
     */
    public Student removeStudentFromSystem(String adminID, String studentID) {

        Administrator admin = findAdmin(adminID);
        Student student = findStudent(studentID);
        Section section = findSection(student.getSection());
        checkPermissionToModify(admin, section);

        return studentService.delete(studentID);
    }


    public Teacher removeTeacherFromSystem(String adminID, String teacherID) {


        Administrator admin = findAdmin(adminID);
        Teacher teacher = findTeacher(teacherID);
        Section section = findSection(teacher.getSections().toString());
        checkPermissionToModify(admin, section);


        return teacherService.delete(teacherID);
    }

    public Student deleteStudentFromSection( String studentID, String sectionID) {

        Student student = findStudent(studentID);

        Section section = findSection(sectionID);


        //remove student from old section
        sectionService
                .getSectionByName(student.getSection()).getStudents().remove(studentID, student);
        student.setSection(null);
        return student;


    }


    public Teacher deleteTeacherFromSection(String teacherID, String sectionID) {

        Teacher teacher = findTeacher(teacherID);

        Section section = findSection(sectionID);

        //remove teacher from old section
        sectionService
                .getSectionByName(teacher.getSections().toString()).getTeachers().remove(teacherID, teacher);
        teacher.setSections(null);
        return teacher;


    }


}
