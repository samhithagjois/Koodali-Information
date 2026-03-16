package koodali.service.exceptions;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException() {
        super("The teacher was not found.\n" +
                "please check the id and if they are registered");
    }
}
