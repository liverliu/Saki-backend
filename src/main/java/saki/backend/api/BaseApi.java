package saki.backend.api;

import saki.backend.exception.ApiException;
import saki.backend.utils.StringUtil;

import java.util.Map;


public abstract class BaseApi {

    public int errcode = 0;
    public String errmsg = "";

    protected void checkNotEmptyParams(Map<String, String> params) {
        params.forEach((k, v) -> {
            if(StringUtil.isEmpty(v)) {
                throw new ApiException("缺少参数: " + k);
            }
        });
    }

    protected abstract <T> void checkOtherParams(T... params);
}
