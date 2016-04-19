package com.baidu.oped.iop.m4.mvc.dto.common;

/**
 * @author mason
 */
public abstract class ProductLayerDto extends BaseDto {

    private static final long serialVersionUID = 3670593870432704592L;

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
