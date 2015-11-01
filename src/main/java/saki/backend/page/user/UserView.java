package saki.backend.page.user;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import saki.backend.dto.user.User;
import saki.backend.entity.Result;
import saki.backend.exception.BaseException;
import saki.backend.exception.ViewException;
import saki.backend.page.AbstractPage;
import saki.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liverliu on 15/10/31.
 */
@Controller
@RequestMapping("user")
@Scope("prototype")
public class UserView extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserView.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/register", method = {RequestMethod.PUT, RequestMethod.GET})
    public String Register(@RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String nickname,
                           Model model) {
        LOGGER.info(String.format("Register page: action:%s, username:%s, nickname:%s", request.getMethod(), username, nickname));
        try {
            if("PUT".equals(request.getMethod())) {
                Map<String, String> params = Maps.newHashMap();
                params.put("username", username);
                params.put("password", password);
                checkNotEmptyParams(params);
                User user = userService.find(username);
                if(user == null) {
                    user = userService.addUser(username, password, nickname);
                }
                model.addAttribute("user", user);
                return REGISTER_SUCCESS;
            }
            return REGISTER;
        } catch (BaseException ex) {
            LOGGER.error("BaseException:", ex);
            errcode = 0;
            errmsg = ex.getMessage();
        } catch (Exception ex) {
            LOGGER.error("OtherException:", ex);
            errcode = 0;
            errmsg = ex.getMessage();
        } finally {
            model.addAttribute("errcode", errcode);
            model.addAttribute("errmsg", errmsg);
        }
        return ERROR_PAGE;
    }


    @Override
    protected <T> void checkOtherParams(T... params) {

    }
}
