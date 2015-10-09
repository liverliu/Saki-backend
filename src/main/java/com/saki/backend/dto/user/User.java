package com.saki.backend.dto.user;

import java.sql.Timestamp;

/**
 * Created by shijianliu on 10/9/15.
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Timestamp tsUpdate;
    private Timestamp lastLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getTsUpdate() {
        return tsUpdate;
    }

    public void setTsUpdate(Timestamp tsUpdate) {
        this.tsUpdate = tsUpdate;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}
