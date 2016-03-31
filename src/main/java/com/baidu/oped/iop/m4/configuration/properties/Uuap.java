package com.baidu.oped.iop.m4.configuration.properties;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * Uuap purpose properties.
 *
 * @author mason
 */
public class Uuap implements Serializable {

    private static final long serialVersionUID = 2350191432361609742L;

    @NotNull
    private String casUrlLogin;

    @NotNull
    private String casUrlLogout;

    @NotNull
    private String casUrlPrefix;

    @NotNull
    private String casServiceUrl;

    @NotNull
    private String appServiceHome;


    public String getAppServiceHome() {
        return appServiceHome;
    }


    public String getCasUrlLogin() {
        return casUrlLogin;
    }

    public void setAppServiceHome(String appServiceHome) {
        this.appServiceHome = appServiceHome;
    }

    public void setCasUrlLogin(String casUrlLogin) {
        this.casUrlLogin = casUrlLogin;
    }

    public String getCasUrlLogout() {
        return casUrlLogout;
    }

    public void setCasUrlLogout(String casUrlLogout) {
        this.casUrlLogout = casUrlLogout;
    }

    public String getCasUrlPrefix() {
        return casUrlPrefix;
    }

    public void setCasUrlPrefix(String casUrlPrefix) {
        this.casUrlPrefix = casUrlPrefix;
    }

    public String getCasServiceUrl() {
        return casServiceUrl;
    }

    public void setCasServiceUrl(String casServiceUrl) {
        this.casServiceUrl = casServiceUrl;
    }

}
