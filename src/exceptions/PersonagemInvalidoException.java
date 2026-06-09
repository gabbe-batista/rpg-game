package exceptions;

public class PersonagemInvalidoException extends RuntimeException {
    public PersonagemInvalidoException(String message) {
        super(message);
    }

    public PersonagemInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
