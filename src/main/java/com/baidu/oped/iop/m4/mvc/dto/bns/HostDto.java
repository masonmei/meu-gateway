package com.baidu.oped.iop.m4.mvc.dto.bns;

import java.io.Serializable;

/**
 * Host dto.
 *
 * @author mason
 */
public class HostDto implements Serializable {
    private static final long serialVersionUID = -2569927548517335337L;

    private String hosts;

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }
}
