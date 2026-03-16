package koodali.service.exceptions;

public class DuplicateSectionException  extends RuntimeException {
    public DuplicateSectionException() {
        super("The section you're trying to create already exists. Consider editing the exiting section.");
    }
}