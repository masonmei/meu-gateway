package com.baidu.oped.iop.m4.mvc.dto.common;

import java.io.Serializable;

/**
 * @author mason
 */
public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = 5808925257752748852L;

    private Long id;

    private String name;
    private String alias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
