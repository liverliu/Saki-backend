package saki.backend.exception;

/**
 * Created by liverliu on 15/10/31.
 */
public class ViewException extends BaseException {

    public ViewException() {
        super();
    }

    public ViewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ViewException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViewException(String message) {
        super(message);
    }

    public ViewException(Throwable cause) {
        super(cause);
    }
}
