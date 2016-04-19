package com.baidu.oped.iop.m4.custom.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Meu User Details.
 *
 * @author mason
 */
public class AppUserDetails implements UserDetails {
    private static final long serialVersionUID = 6308518180850513611L;

    private int id;
    private String name;
    private String alias;
    private String telephone;
    private String email;
    private String plat;
    private String defaultProduct;

    private Set<AppAuthority> roles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

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

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public Set<AppAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppAuthority> roles) {
        this.roles = roles;
    }

    public String getDefaultProduct() {
        return defaultProduct;
    }

    public void setDefaultProduct(String defaultProduct) {
        this.defaultProduct = defaultProduct;
    }

    @Override
    @JsonIgnore
    public Collection<AppAuthority> getAuthorities() {
        return roles;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return name;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
