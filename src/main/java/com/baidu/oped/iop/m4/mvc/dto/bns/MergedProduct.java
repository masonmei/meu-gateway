package com.baidu.oped.iop.m4.mvc.dto.bns;

import static com.baidu.oped.sia.boot.utils.Constrains.Datetime.DATE_TIME_FORMAT;

import com.baidu.oped.iop.m4.mvc.dto.common.BaseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author mason
 */
public class MergedProduct extends BaseDto {
    private static final long serialVersionUID = 1220533403516078433L;

    private String tenantId;
    private String creator;
    private String creatorAlias;
    private String description;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private Date createTime;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorAlias() {
        return creatorAlias;
    }

    public void setCreatorAlias(String creatorAlias) {
        this.creatorAlias = creatorAlias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
