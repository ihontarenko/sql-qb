package pro.javadev.sql.library.render;

public class RendererException extends RuntimeException {

    public RendererException(String message) {
        super(message);
    }

    public RendererException(String message, Throwable cause) {
        super(message, cause);
    }

}
