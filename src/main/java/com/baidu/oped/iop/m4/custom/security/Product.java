package com.baidu.oped.iop.m4.custom.security;

import java.util.HashSet;
import java.util.Set;

/**
 * Product model.
 *
 * @author mason
 */
public class Product {
    private Set<String> roles = new HashSet<>();
    private String name;

    public String getName() {
        return name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
