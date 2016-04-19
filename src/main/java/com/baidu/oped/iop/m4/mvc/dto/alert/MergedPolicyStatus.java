package com.baidu.oped.iop.m4.mvc.dto.alert;

import com.baidu.oped.iop.m4.mvc.dto.common.AppLayerDto;
import com.baidu.oped.iop.m4.mvc.dto.common.PolicyStatus;

/**
 * @author mason
 */
public class MergedPolicyStatus extends AppLayerDto {

    private static final long serialVersionUID = 8763638840689019637L;

    private PolicyStatus status = PolicyStatus.NORMAL;

    private int abnormalCount;
    private int normalCount;
    private int insufficientCount;
    private int totalCount;

    public PolicyStatus getStatus() {
        return status;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public int getAbnormalCount() {
        return abnormalCount;
    }

    public void setAbnormalCount(int abnormalCount) {
        this.abnormalCount = abnormalCount;
    }

    public int getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(int normalCount) {
        this.normalCount = normalCount;
    }

    public int getInsufficientCount() {
        return insufficientCount;
    }

    public void setInsufficientCount(int insufficientCount) {
        this.insufficientCount = insufficientCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
