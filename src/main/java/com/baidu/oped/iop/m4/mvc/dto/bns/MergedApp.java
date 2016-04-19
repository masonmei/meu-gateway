package com.baidu.oped.iop.m4.mvc.dto.bns;

import com.baidu.oped.iop.m4.mvc.dto.common.ProductLayerDto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mason
 */
public class MergedApp extends ProductLayerDto {

    private static final long serialVersionUID = -764751389443600568L;

    @JsonProperty("instanceNum")
    private int instanceNumber;
    @JsonProperty("incidentNum")
    private int incidentPolicyNumber;
    @JsonProperty("insufficientNum")
    private int insufficientPolicyNumber;
    @JsonProperty("resumeNum")
    private int resumePolicyNumber;

    public int getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(int instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public int getIncidentPolicyNumber() {
        return incidentPolicyNumber;
    }

    public void setIncidentPolicyNumber(int incidentPolicyNumber) {
        this.incidentPolicyNumber = incidentPolicyNumber;
    }

    public int getInsufficientPolicyNumber() {
        return insufficientPolicyNumber;
    }

    public void setInsufficientPolicyNumber(int insufficientPolicyNumber) {
        this.insufficientPolicyNumber = insufficientPolicyNumber;
    }

    public int getResumePolicyNumber() {
        return resumePolicyNumber;
    }

    public void setResumePolicyNumber(int resumePolicyNumber) {
        this.resumePolicyNumber = resumePolicyNumber;
    }
}
