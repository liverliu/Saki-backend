package saki.backend.page;

import saki.backend.exception.ApiException;
import saki.backend.utils.StringUtil;

import java.util.Map;

/**
 * Created by liverliu on 15/10/31.
 */
public abstract class AbstractPage {

    protected static final String ERROR_PAGE = "/error";

    //Register
    protected static final String REGISTER = "/user/register";
    protected static final String REGISTER_SUCCESS = "/user/register_success";

    protected int errcode = 0;
    protected String errmsg = "success";

    protected void checkNotEmptyParams(Map<String, String> params) {
        params.forEach((k, v) -> {
            if(StringUtil.isEmpty(v)) {
                throw new ApiException("缺少参数: " + k);
            }
        });
    }

    protected abstract <T> void checkOtherParams(T... params);
}
