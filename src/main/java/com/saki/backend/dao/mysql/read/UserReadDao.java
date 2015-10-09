package com.saki.backend.dao.mysql.read;

import com.saki.backend.dao.mysql.BaseReadDao;
import com.saki.backend.dto.user.User;
import org.springframework.stereotype.Repository;

/**
 * Created by shijianliu on 10/9/15.
 */
@Repository
public class UserReadDao extends BaseReadDao<User> {

    @Override
    public String getSqlNameSpace() {
        return "user";
    }
}
