package saki.backend.exception;


/**
 * Created by liverliu on 14-6-18.
 */
public class UtilException extends BaseException {

    public UtilException() {
        super();
    }

    public UtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

}
