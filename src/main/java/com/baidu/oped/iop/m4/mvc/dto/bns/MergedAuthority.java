package com.baidu.oped.iop.m4.mvc.dto.bns;

import java.io.Serializable;

/**
 * @author mason
 */
public class MergedAuthority implements Serializable {
    private static final long serialVersionUID = 2710970187947803182L;

    private String productName;
    private String roleName;
    private Long roleId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
