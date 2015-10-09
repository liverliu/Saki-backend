package com.saki.backend.service;

import com.saki.backend.dao.mysql.read.UserReadDao;
import com.saki.backend.dao.mysql.write.UserWriteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
