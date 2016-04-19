package com.baidu.oped.iop.m4.mvc.dto.bns;

import com.baidu.oped.iop.m4.mvc.dto.common.BaseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mason
 */
public class MergedUser extends BaseDto {
    private static final long serialVersionUID = -3017290162085517945L;

    private String telephone;
    private String email;
    @JsonProperty("plat")
    private String platform;
    private String defaultProduct;

    private Set<MergedAuthority> roles = new HashSet<>();

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<MergedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<MergedAuthority> roles) {
        this.roles = roles;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDefaultProduct() {
        return defaultProduct;
    }

    public void setDefaultProduct(String defaultProduct) {
        this.defaultProduct = defaultProduct;
    }
}
