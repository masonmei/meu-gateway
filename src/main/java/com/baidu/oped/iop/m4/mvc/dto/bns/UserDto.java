package com.baidu.oped.iop.m4.mvc.dto.bns;

import java.io.Serializable;

/**
 * User dto.
 *
 * @author mason
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -2569927548517335337L;

    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
