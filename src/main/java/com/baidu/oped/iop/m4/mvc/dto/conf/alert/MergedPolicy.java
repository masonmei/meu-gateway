package com.baidu.oped.iop.m4.mvc.dto.conf.alert;

import com.baidu.oped.iop.m4.mvc.dto.alert.PolicyDto;

/**
 * @author mason
 */
public class MergedPolicy extends PolicyDto {

    private static final long serialVersionUID = 6025620930391603695L;

    private String policyStatus;
    private String blockStatus;

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }
}
