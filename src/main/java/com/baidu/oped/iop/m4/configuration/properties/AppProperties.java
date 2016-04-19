package com.baidu.oped.iop.m4.configuration.properties;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Application Propertity Configuration.
 *
 * @author mason
 */
@Component
@ConfigurationProperties(prefix = "project")
public class AppProperties {
    @URL
    private String userProjectInfo;
    @URL
    private String policyStatusUrl;
    @URL
    private String incidentUrl;
    @URL
    private String confManUrl;
    @URL
    private String serviceManUrl;
    @URL
    private String dataServiceUrl;

    @URL
    private String homePage;

    private AppInfo info = new AppInfo();
    private Uuap uuap = new Uuap();
    private CorsConfiguration cors = new CorsConfiguration();

    public String getUserProjectInfo() {
        return userProjectInfo;
    }

    public void setUserProjectInfo(String userProjectInfo) {
        this.userProjectInfo = userProjectInfo;
    }

    public String getPolicyStatusUrl() {
        return policyStatusUrl;
    }

    public void setPolicyStatusUrl(String policyStatusUrl) {
        this.policyStatusUrl = policyStatusUrl;
    }

    public String getIncidentUrl() {
        return incidentUrl;
    }

    public void setIncidentUrl(String incidentUrl) {
        this.incidentUrl = incidentUrl;
    }

    public String getConfManUrl() {
        return confManUrl;
    }

    public void setConfManUrl(String confManUrl) {
        this.confManUrl = confManUrl;
    }

    public String getServiceManUrl() {
        return serviceManUrl;
    }

    public void setServiceManUrl(String serviceManUrl) {
        this.serviceManUrl = serviceManUrl;
    }

    public String getDataServiceUrl() {
        return dataServiceUrl;
    }

    public void setDataServiceUrl(String dataServiceUrl) {
        this.dataServiceUrl = dataServiceUrl;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public AppInfo getInfo() {
        return info;
    }

    public void setInfo(AppInfo info) {
        this.info = info;
    }

    public Uuap getUuap() {
        return uuap;
    }

    public void setUuap(Uuap uuap) {
        this.uuap = uuap;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public void setCors(CorsConfiguration cors) {
        this.cors = cors;
    }
}
