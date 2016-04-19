package com.baidu.oped.iop.m4.mvc.dto.bns;

import com.baidu.oped.iop.m4.mvc.dto.common.BaseDto;

/**
 * @author mason
 */
public class MergedRole extends BaseDto {
    private static final long serialVersionUID = -6920367438105016190L;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
