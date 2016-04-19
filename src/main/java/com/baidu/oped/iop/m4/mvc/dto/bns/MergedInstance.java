package com.baidu.oped.iop.m4.mvc.dto.bns;

import com.baidu.oped.iop.m4.mvc.dto.common.AppLayerDto;

/**
 * @author mason
 */
public class MergedInstance extends AppLayerDto {
    private String hostname;
    private String tags;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
