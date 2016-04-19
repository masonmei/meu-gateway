package com.baidu.oped.iop.m4.mvc.dto.alert;

import static com.baidu.oped.sia.boot.utils.Constrains.Datetime.DATE_TIME_FORMAT;

import com.baidu.oped.iop.m4.domain.entity.alert.PolicyLevel;
import com.baidu.oped.iop.m4.mvc.dto.common.AppLayerDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author mason
 */
public class MergedIncident extends AppLayerDto {
    private static final long serialVersionUID = 552213992227662021L;

    private String incidentId;
    private String policyName;
    private PolicyLevel policyLevel = PolicyLevel.NOTICE;

    private String monitorObject;
    private String monitorType;

    private String formula;
    private String metricValue;

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private Date startTime;

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private Date endTime;

    private String content;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public PolicyLevel getPolicyLevel() {
        return policyLevel;
    }

    public void setPolicyLevel(PolicyLevel policyLevel) {
        this.policyLevel = policyLevel;
    }

    public String getMonitorObject() {
        return monitorObject;
    }

    public void setMonitorObject(String monitorObject) {
        this.monitorObject = monitorObject;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(String metricValue) {
        this.metricValue = metricValue;
    }
}
