package com.baidu.oped.iop.m4.mvc.dto.common;

/**
 * @author mason
 */
public abstract class AppLayerDto extends ProductLayerDto {

    private static final long serialVersionUID = 2521732547650430404L;

    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
