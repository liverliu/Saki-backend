package saki.backend.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import saki.backend.dao.mysql.read.UserReadDao;
import saki.backend.dao.mysql.write.UserWriteDao;
import saki.backend.dto.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by shijianliu on 10/9/15.
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserReadDao userReadDao;

    @Autowired
    private UserWriteDao userWriteDao;

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    public User addUser(String username, String password, String nickname) {
        User user = new User(username, password, nickname);
        int id = userWriteDao.insert(user);
        user.setId(id);
        long now = System.currentTimeMillis();
        user.setTsUpdate(new Timestamp(now));
        return user;
    }

}
