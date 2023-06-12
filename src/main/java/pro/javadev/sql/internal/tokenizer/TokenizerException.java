package pro.javadev.sql.internal.tokenizer;

public class TokenizerException extends RuntimeException {

    public TokenizerException(String message) {
        super(message);
    }

    public TokenizerException(String message, Throwable cause) {
        super(message, cause);
    }

}
