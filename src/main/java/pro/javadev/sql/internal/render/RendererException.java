package pro.javadev.sql.internal.render;

public class RendererException extends RuntimeException {

    public RendererException(String message) {
        super(message);
    }

    public RendererException(String message, Throwable cause) {
        super(message, cause);
    }

}
