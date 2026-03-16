package koodali.service.exceptions;

public class IllegalAdminActionException extends RuntimeException {


    public IllegalAdminActionException() {
        super("You cannot complete this action as you are not authorized for this section." +
                "If you believe you have the permissions needed, please contact Samhitha");
    }
}
