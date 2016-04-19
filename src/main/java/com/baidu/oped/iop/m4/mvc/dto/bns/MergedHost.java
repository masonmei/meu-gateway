package com.baidu.oped.iop.m4.mvc.dto.bns;

import com.baidu.oped.iop.m4.mvc.dto.common.ProductLayerDto;

/**
 * @author mason
 */
public class MergedHost extends ProductLayerDto {

    private double cpuIdle;
    private double memUsedPercent;
    private double diskUsedPercent;

    public double getCpuIdle() {
        return cpuIdle;
    }

    public void setCpuIdle(double cpuIdle) {
        this.cpuIdle = cpuIdle;
    }

    public double getMemUsedPercent() {
        return memUsedPercent;
    }

    public void setMemUsedPercent(double memUsedPercent) {
        this.memUsedPercent = memUsedPercent;
    }

    public double getDiskUsedPercent() {
        return diskUsedPercent;
    }

    public void setDiskUsedPercent(double diskUsedPercent) {
        this.diskUsedPercent = diskUsedPercent;
    }
}
