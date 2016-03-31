package com.baidu.oped.iop.m4.configuration.properties;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    private String policyStatusUrl;
    private String incidentUrl;
    private String confManUrl;
    private String serviceManUrl;
    private String dataServiceUrl;
    private AppInfo info;
    private Uuap uuap;

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


}
