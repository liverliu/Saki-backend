package saki.backend.api.user;

import com.google.common.collect.Maps;
import saki.backend.annotation.CheckEmpty;
import saki.backend.annotation.LogExeTime;
import saki.backend.annotation.LogInvoke;
import saki.backend.api.BaseApi;
import saki.backend.dto.user.User;
import saki.backend.entity.Result;
import saki.backend.exception.ApiException;
import saki.backend.exception.BaseException;
import saki.backend.service.UserService;
import saki.backend.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by shijianliu on 10/9/15.
 */
@RestController
@Scope("prototype")
@RequestMapping("/user")
public class UserApi extends BaseApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserService userService;

    @LogExeTime
    @LogInvoke(params = {"username", "password", "nickname"})
    @CheckEmpty(params = {"username", "password", "nickname"})
    @RequestMapping(value = "/register", method = {RequestMethod.PUT, RequestMethod.GET})
    public Result<User> Register(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = false) String nickname) {
        User resp = null;
        try {
            if(!StringUtil.isAlphaOrNum(username)) {
                throw new ApiException("用户名包含非法字符");
            }
            if(!StringUtil.isAlphaOrNum(password)) {
                throw new ApiException("密码包含非法字符");
            }
            resp = userService.find(username);
            if(resp != null) {
                throw new ApiException("用户命已存在");
            }
            resp = userService.addUser(username, password, nickname);
        } catch (BaseException ex) {
            LOGGER.error("BaseException:", ex);
            errcode = -1;
            errmsg = ex.getMessage();
        } catch (Exception ex) {
            LOGGER.error("OtherException:", ex);
            errcode = -1;
            errmsg = ex.getMessage();
        }
        return new Result<>(errcode, errmsg, resp);
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public Result<String> login(@RequestParam(required = false) String username,
                                @RequestParam(required = false) String password) {
        return null;
    }


    @RequestMapping(value = "/info/{username}", method = RequestMethod.GET)
    public Result<User> info(@PathVariable String username) {
        return new Result<>(0, "success", new User(username, "abc", "abc"));
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public Result<List<User>> list(@PathVariable Integer page) {
        return null;
    }

    @Override
    protected <T> void checkOtherParams(T... params) {

    }
}
