package koodali.service.exceptions;

public class AdminNotFoundException extends RuntimeException {

    public AdminNotFoundException() {
        super("This admin was not found. Please check if you are logged in LOL");
    }
}
