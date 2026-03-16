package koodali.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.UUID;

@Entity
public class Student extends Person {


    private String section;


    //new variables
    //      date of birth : Date
    //      fathers name : String
    //      fathers email id : String
    //      fathers phone number : float
    //      mothers name : String
    //      mothers email id : String
    //      mothers phone number : float
    //
    //      fees to pay : int
    //      feed paid : int
    //
    //      attendance percentage : double
    //      homework leaderboard score : int
    //      assessment test result : seperate page
    //      photo of child : Multipart file
    //      consent form  : Multipart file
    private int amountOfTextbooks, feesPaid, pendingFees, homeworkLeaderBoardScore;


    private String pathToPhoto;
    private String pathToConsentForm;
    private LocalDate dateOfBirth;
    private LocalDateTime dateOfRegistration;
    private LocalDate dateOfFirstClass;

    private String mothersName;
    private String fathersName;
    private String fathersEmailID;
    private String mothersEmailID;



    private String childEmailID;

    private double attendance;
    private String phoneNumber;
    private String whatsappNumber;

    public Student(String firstName,
                   String lastName,
                   String personID,
                   String city,
                   String pinCode,
                   String country,
                   String fullPostalAddress,
                   LocalDateTime dateOfRegistration,
                   LocalDateTime dateOfClassStart,
                   boolean activeStatus,
                   String section,
                   int amountOfTextbooks,
                   int feesPaid,
                   int pendingFees,
                   int homeworkLeaderBoardScore,
                   LocalDate dateOfBirth,
                   LocalDate dateOfFirstClass,
                   String pathToPhoto,
                   String pathToConsentForm,
                   String mothersName,
                   String fathersName,
                   String fathersEmailID,
                   String mothersEmailID,
                   String childEmailID,
                   double attendancePercentage,
                   String phoneNumber,
                   String whatsappNumber) {

        super(firstName, lastName, personID,
                city, pinCode, country, fullPostalAddress,
                dateOfRegistration, dateOfClassStart,
                activeStatus);
        this.section = section;
        this.amountOfTextbooks = amountOfTextbooks;
        this.feesPaid = feesPaid;
        this.pendingFees = pendingFees;
        this.homeworkLeaderBoardScore = homeworkLeaderBoardScore;
        this.pathToPhoto = pathToPhoto;
        this.pathToConsentForm = pathToConsentForm;
        this.dateOfBirth = dateOfBirth;
        this.dateOfFirstClass = dateOfFirstClass;
        this.mothersName = mothersName;
        this.fathersName = fathersName;
        this.fathersEmailID = fathersEmailID;
        this.mothersEmailID = mothersEmailID;
        this.childEmailID = childEmailID;
        this.attendance = attendancePercentage;
        this.phoneNumber = phoneNumber;
        this.whatsappNumber = whatsappNumber;
    }

    public Student() {
        super();
        this.personID = "NKS_"+ (UUID.randomUUID().toString().substring(0,2));
        this.section = "UNASSIGNED";
        this.amountOfTextbooks = 0;
        this.feesPaid = 0;
        this.pendingFees = 0;
        this.homeworkLeaderBoardScore = 0;
        this.pathToPhoto = "";
        this.pathToConsentForm = "";
        this.dateOfBirth = null;
        this.dateOfFirstClass = null;
        this.mothersName = "";
        this.fathersName = "";
        this.fathersEmailID = "";
        this.mothersEmailID = "";
        this.childEmailID = "";
        this.attendance = 0.0;
        this.phoneNumber = "";
        this.whatsappNumber = "";
    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }


    public int getAmountOfTextbooks() {
        return amountOfTextbooks;
    }

    public void setAmountOfTextbooks(int amountOfTextbooks) {
        this.amountOfTextbooks = amountOfTextbooks;
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(int feesPaid) {
        this.feesPaid = feesPaid;
    }

    public int getPendingFees() {
        return pendingFees;
    }

    public void setPendingFees(int pendingFees) {
        this.pendingFees = pendingFees;
    }

    public int getHomeworkLeaderBoardScore() {
        return homeworkLeaderBoardScore;
    }

    public void setHomeworkLeaderBoardScore(int homeworkLeaderBoardScore) {
        this.homeworkLeaderBoardScore = homeworkLeaderBoardScore;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String photo) {
        this.pathToPhoto = photo;
    }

    public String getPathToConsentForm() {
        return pathToConsentForm;
    }

    public void setPathToConsentForm(String consentForm) {
        this.pathToConsentForm = consentForm;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public LocalDate getDateOfFirstClass() {
        return dateOfFirstClass;
    }

    public void setDateOfFirstClass(LocalDate dateOfFirstClass) {
        this.dateOfFirstClass = dateOfFirstClass;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getFathersEmailID() {
        return fathersEmailID;
    }

    public void setFathersEmailID(String fathersEmailID) {
        this.fathersEmailID = fathersEmailID;
    }

    public String getMothersEmailID() {
        return mothersEmailID;
    }

    public void setMothersEmailID(String mothersEmailID) {
        this.mothersEmailID = mothersEmailID;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getChildEmailID() {
        return childEmailID;
    }

    public void setChildEmailID(String childEmailID) {
        this.childEmailID = childEmailID;
    }

}
