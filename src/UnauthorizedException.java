/**
 * @author Hawkins Peterson
 * @version 11/14/2021
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
