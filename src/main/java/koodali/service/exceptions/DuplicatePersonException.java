package koodali.service.exceptions;

public class DuplicatePersonException extends RuntimeException {

    public DuplicatePersonException() {
        super("This person already exists in the system! Check again if you got all the IDs right, " +
                "and if you don't want to update the person instead. Should there be an error, please contact Samhitha.");
    }
}
