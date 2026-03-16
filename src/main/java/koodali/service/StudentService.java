package koodali.service;

import koodali.model.Section;
import koodali.model.Student;
import koodali.model.dto.studentDTO.CreateStudentDTO;
import koodali.model.dto.studentDTO.SectionStudentOverviewDTO;
import koodali.model.dto.studentDTO.StudentFeesDTO;
import koodali.model.dto.studentDTO.StudentOverviewDTO;
import koodali.repository.StudentRepository;
import koodali.service.exceptions.SectionNotFoundException;
import koodali.service.exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService extends PersonService<Student> {

    private final StudentRepository studentRepo;

    private final SectionService sectionService;


    public StudentService(StudentRepository studentRepo, SectionService sectionService) {
        super(studentRepo);
        this.studentRepo = studentRepo;

        this.sectionService = sectionService;
    }

    private Student CreateStudentDTOToStudent(CreateStudentDTO dto) {
        return new Student(dto.firstName(),
                dto.lastName(),
                "NKS_" + (UUID.randomUUID().toString().substring(0, 2)),
                dto.city(), dto.pinCode(), dto.country(), dto.fullPostalAddress(), LocalDateTime.now(), LocalDateTime.now(), true,
                sectionService.returnSectionNameIfValid(dto.sectionName()), //here
                0, 0, 0, 0, dto.dateOfBirth(), LocalDate.now(), dto.pathToPhoto(), dto.pathToConsentForm(), dto.mothersName(), dto.fathersName(), dto.fathersEmailID(),
                dto.mothersEmailID(), dto.childEmailID(), 0.0, dto.phoneNumber(), dto.whatsappNumber());
    }

    private StudentOverviewDTO StudentToStudentOverviewDTO(Student student) {

        return new StudentOverviewDTO(
                student.isActiveStatus(),
                student.getID(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),

                student.getFullPostalAdress(),

                student.getDateOfClassStart(),
                student.getSection(), //here
                student.getAmountOfTextbooks(),

                student.getPendingFees(),
                student.getHomeworkLeaderBoardScore(),

                student.getPhoneNumber(),
                student.getWhatsappNumber()
        );
    }

    public SectionStudentOverviewDTO StudentToSectionStudentOverviewDTO(Student student) {

        return new SectionStudentOverviewDTO(
                student.getID(),
                student.getFirstName(),
                student.getLastName(),
                student.getHomeworkLeaderBoardScore(),
                student.getAttendance()
        );
    }

    private StudentFeesDTO StudentToStudentFeeDTO(Student student) {

        return new StudentFeesDTO(
                student.getID(),
                student.getFirstName(),
                student.getLastName(),
                student.getFeesPaid(),
                student.getPendingFees()
        );
    }

    public List<StudentOverviewDTO> getAllStudentOverviewDTOs(){
        return studentRepo.findAll().stream().map(this::StudentToStudentOverviewDTO).toList();
    }
    public List<SectionStudentOverviewDTO> getAllSectionStudentDTOs(){
        return studentRepo.findAll().stream().map(this::StudentToSectionStudentOverviewDTO).toList();
    }
    public List<StudentFeesDTO> getAllStudentFeesDTOs(){
        return studentRepo.findAll().stream().map(this::StudentToStudentFeeDTO).toList();
    }
    public Student findByID(String studentID) {
        return studentRepo
                .findById(studentID)
                .orElseThrow(StudentNotFoundException::new);
    }

    public StudentOverviewDTO findDTOByID(String studentID) {
        return StudentToStudentOverviewDTO(findByID(studentID));
    }

    public Student findStudentbyName(String firstName, String lastName) {
        return studentRepo
                .findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(StudentNotFoundException::new);
    }

    public SectionStudentOverviewDTO findStudentOfSection(String sectionID, String studentID){

        Student s = findByID(studentID);
        if(s.getSection().equals(sectionID)){
            return StudentToSectionStudentOverviewDTO(s);
        }else{
            throw new StudentNotFoundException();
        }
    }

    public StudentOverviewDTO createStudent(CreateStudentDTO student) {
           return StudentToStudentOverviewDTO(
                studentRepo.save(
                        CreateStudentDTOToStudent(student)));
    }

    public CreateStudentDTO updateStudent(CreateStudentDTO dto) {

        Student s = findStudentbyName(dto.firstName(), dto.lastName());
        s.setFirstName(dto.firstName());
        s.setLastName(dto.lastName());
        s.setSection(sectionService.getSectionByName(dto.sectionName()).getName());

        s.setCity(dto.city());
        s.setCountry(dto.country());
        s.setPinCode(dto.pinCode());
        s.setFullPostalAdress(dto.fullPostalAddress());

        s.setDateOfBirth(dto.dateOfBirth());

        s.setFathersName(dto.fathersName());
        s.setMothersName(dto.mothersName());
        s.setFathersEmailID(dto.fathersEmailID());
        s.setMothersEmailID(dto.mothersEmailID());
        s.setChildEmailID(dto.childEmailID());

        s.setWhatsappNumber(dto.whatsappNumber());
        s.setPhoneNumber(dto.phoneNumber());

        s.setPathToPhoto(dto.pathToPhoto());
        s.setPathToConsentForm(dto.pathToConsentForm());

        return dto;


    }

    public StudentOverviewDTO updateStudent(String id, StudentOverviewDTO dto) {


        Student s = findByID(id);
        s.setFirstName(dto.firstName());
        s.setLastName(dto.lastName());
        s.setSection(dto.sectionId());


        s.setActiveStatus(dto.activeStatus());

        s.setHomeworkLeaderBoardScore(dto.homeworkLeaderBoardScore());

        s.setFullPostalAdress(dto.fullPostalAddress());

        s.setDateOfBirth(dto.dateOfBirth());

        s.setPendingFees(dto.pendingFees());

        s.setAmountOfTextbooks(dto.amountOfTextbooks());

        s.setDateOfClassStart(dto.dateOfClassStart());


        s.setWhatsappNumber(dto.whatsappNumber());
        s.setPhoneNumber(dto.phoneNumber());


        return dto;


    }

    public Student createStudent(String studentId,
                                 String firstName,
                                 String lastName,
                                 String sectionID,
                                 String city, String pinCode, String country,
                                 String fullPostalAddress, LocalDateTime dateOfRegistration,
                                 LocalDateTime dateOfClassStart, boolean activeStatus,
                                 int amountOfTextbooks, int feesPaid, int pendingFees, int homeworkLeaderBoardScore,
                                 LocalDate dateOfBirth,
                                 LocalDate dateOfFirstClass, String pathToPhoto,
                                 String pathToConsentForm, String mothersName, String fathersName,
                                 String fathersEmailID, String mothersEmailID, String childEmailAddress, double attendancePercentage,
                                 String phoneNumber, String whatsappNumber) {

        Section section = sectionService.getSectionByName(sectionID);
        Student student = new Student(
                firstName, lastName, studentId, city, pinCode, country, fullPostalAddress,
                dateOfRegistration, dateOfClassStart, activeStatus, sectionID,
                amountOfTextbooks, feesPaid, pendingFees, homeworkLeaderBoardScore,
                dateOfBirth, dateOfFirstClass, pathToPhoto, pathToConsentForm, mothersName, fathersName, fathersEmailID,
                mothersEmailID, childEmailAddress, attendancePercentage, phoneNumber, whatsappNumber
        );
        section.getStudents().put(student.getID(), student);
        return studentRepo.save(student);

    }

    public void createStudentsFromList(List<Student> students) {
        for (Student student : students) {
            studentRepo.save(student);
            sectionService
                    .getAllSections()
                    .stream()
                    .filter(
                            section -> section
                                    .getName()
                                    .equals(student.getSection()))
                    .findFirst()
                    .ifPresent(section -> section
                            .getStudents()
                            .put(student.getID(), student));
        }
    }

    public Student addHomeworkPoints(String studentID, LocalDate week, int points) {
        Student student = findByID(studentID);
        student.setHomeworkLeaderBoardScore(student.getHomeworkLeaderBoardScore() + points);
        return update(student);

    }

    public int getTotalHomeworkPoints(String studentID) {
        Student student = findByID(studentID);
        return student.getHomeworkLeaderBoardScore();
    }


    public List<SectionStudentOverviewDTO> listStudentsInSection(String sectionID) {
        try {
            Section section = sectionService.getSectionByName(sectionID);
            return section.getStudents().values().stream().map(this::StudentToSectionStudentOverviewDTO).toList();
        } catch (SectionNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public StudentOverviewDTO deleteById(String studentID){
        Student s = findByID(studentID);
        studentRepo.deleteById(studentID);
        return StudentToStudentOverviewDTO(s);
    }


}
