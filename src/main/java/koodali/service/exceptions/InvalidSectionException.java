package koodali.service.exceptions;

public class InvalidSectionException extends RuntimeException {
    public InvalidSectionException() {
        super("The name you entered for the section is not valid or not Formatted properly, or the name already exists. in this case, please go to the Edit section page");
    }
}
