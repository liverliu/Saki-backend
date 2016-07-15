package saki.backend.api;


public abstract class BaseApi {

    public int errcode = 0;
    public String errmsg = "success";

    protected abstract <T> void checkOtherParams(T... params);
}
