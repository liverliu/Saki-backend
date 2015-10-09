package com.saki.backend.dao.mysql.write;

import com.saki.backend.dao.mysql.BaseWriteDao;
import com.saki.backend.dto.user.User;
import org.springframework.stereotype.Repository;

/**
 * Created by shijianliu on 10/9/15.
 */
@Repository
public class UserWriteDao extends BaseWriteDao<User> {

    @Override
    public String getSqlNameSpace() {
        return "user";
    }
}
