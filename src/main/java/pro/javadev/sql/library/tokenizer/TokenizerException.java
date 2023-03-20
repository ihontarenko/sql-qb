package pro.javadev.sql.library.tokenizer;

public class TokenizerException extends RuntimeException {

    public TokenizerException(String message) {
        super(message);
    }

    public TokenizerException(String message, Throwable cause) {
        super(message, cause);
    }

}
