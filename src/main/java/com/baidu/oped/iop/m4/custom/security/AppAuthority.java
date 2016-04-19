package com.baidu.oped.iop.m4.custom.security;

import static java.lang.String.format;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * Meu Authority.
 *
 * @author mason
 */
public class AppAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 7700631575972947758L;

    private int id;
    private String name;
    private String productName;

    AppAuthority() {
    }

    public AppAuthority(String productName, String name) {
        Assert.hasText(productName, "Product Name must have content.");
        Assert.hasText(name, "Product Role name must have content.");
        this.productName = productName;
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public String getName() {
        return name;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return format("%s:%s", productName, name);
    }
}
