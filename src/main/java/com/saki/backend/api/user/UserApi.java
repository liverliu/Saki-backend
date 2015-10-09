package com.saki.backend.api.user;

import com.saki.backend.api.BaseApi;
import com.saki.backend.dto.user.User;
import com.saki.backend.entity.Result;
import com.saki.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by shijianliu on 10/9/15.
 */
@Controller
@RequestMapping("/user")
public class UserApi extends BaseApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    @ResponseBody
    public Result<User> Register(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = false) String nickname) {
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> login(@RequestParam(required = false) String username,
                                @RequestParam(required = false) String password) {
        return null;
    }

    @RequestMapping(value = "/info/${username}", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> info(@PathVariable String username) {
        return null;
    }

    @RequestMapping(value = "/list/${page}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<User>> list(@PathVariable Integer page) {
        return null;
    }


    @Override
    protected <T> void checkOtherParams(T... params) {

    }
}
