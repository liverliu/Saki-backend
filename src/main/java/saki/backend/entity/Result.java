package saki.backend.entity;

/**
 * Created by liverliu on 14/12/15.
 */
public class Result<T> {

    private int errcode;
    private String errmsg;
    private T result;

    public Result(int errcode, String errmsg, T result) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.result = result;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
