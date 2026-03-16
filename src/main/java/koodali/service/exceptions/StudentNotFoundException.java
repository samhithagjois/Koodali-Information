package koodali.service.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super("The student was not found.\n" +
                "please check the student id or if said student is enrolled");
    }
}
