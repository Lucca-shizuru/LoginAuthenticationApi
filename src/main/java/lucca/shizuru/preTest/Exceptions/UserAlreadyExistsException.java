package lucca.shizuru.preTest.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User with this login already exists.");
    }
}
