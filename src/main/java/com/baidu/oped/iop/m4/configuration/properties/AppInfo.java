package com.baidu.oped.iop.m4.configuration.properties;

import java.io.Serializable;

/**
 * Application Info.
 *
 * @author mason
 */
public class AppInfo implements Serializable {
    private static final long serialVersionUID = 4671911894738932484L;

    private String version;
    private String description;
    private String department;
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
