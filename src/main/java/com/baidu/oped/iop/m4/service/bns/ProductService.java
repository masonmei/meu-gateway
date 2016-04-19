package com.baidu.oped.iop.m4.service.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.MergedProduct;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedRole;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedUser;
import com.baidu.oped.iop.m4.mvc.dto.bns.UserDto;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * Product Client for bns application.
 *
 * @author mason
 */
@FeignClient(name = "bnsProductClient", url = "${project.service-man-url}")
public interface ProductService {

    String PRODUCTS_MAPPING_PREFIX = "products";
    String NAME_PATH = "/{" + NAME + "}";

    /**
     * Search products of the login
     *
     * @param userId   user id
     * @param queryMap query param map
     * @return product page
     */
    @RequestLine(V1_GET + "users/{userId}/products")
    BaseResponse<List<MergedProduct>> search(@Param("userId") String userId,
            @QueryMap Map<String, List<String>> queryMap);

    /**
     * Make product of given user default.
     *
     * @param userId      user id
     * @param productName product name
     * @return success or not
     */
    @RequestLine(V1_PUT + "users/{userId}/products/{productName}/default")
    BaseResponse<Void> makeProductDefault(@Param("userId") String userId, @Param(PRODUCT_NAME) String productName);


    /**
     * List the roles of given products.
     *
     * @param productName product name
     * @return all the product roles
     */
    @RequestLine(V1_GET + PRODUCTS_MAPPING_PREFIX + "/{productName}/roles")
    BaseResponse<List<MergedRole>> searchProductRole(@Param(PRODUCT_NAME) String productName);

    /**
     * Search the users of given role of product.
     *
     * @param productName product name
     * @param roleId      role id
     * @param queryMap    query params
     * @return user page
     */
    @RequestLine(V1_GET + PRODUCTS_MAPPING_PREFIX + "/{productName}/roles/{roleId}/users")
    BaseResponse<PageData<MergedUser>> searchProductRoleUsers(@Param(PRODUCT_NAME) String productName,
            @Param("roleId") Long roleId, @QueryMap Map<String, List<String>> queryMap);

    /**
     * Add product role users.
     *
     * @param productName product name
     * @param roleId      role id
     * @param dto         users dto
     * @return success or not.
     */
    @RequestLine(V1_POST + PRODUCTS_MAPPING_PREFIX + "/{productName}/roles/{roleId}/users")
    BaseResponse<Void> addProductRoleUsers(@Param(PRODUCT_NAME) String productName, @Param("roleId") Long roleId,
            UserDto dto);

    /**
     * Remove role users of product.
     *
     * @param productName product name
     * @param roleId      role id
     * @param userId      user id
     * @return success or not
     */
    @RequestLine(V1_DELETE + PRODUCTS_MAPPING_PREFIX + "/{productName}/roles/{roleId}/users/{userId}")
    BaseResponse<Void> deleteProductRoleUsers(@Param(PRODUCT_NAME) String productName, @Param("roleId") Long roleId,
            @Param("userId") String userId);

    /**
     * Create product operation.
     *
     * @param product product
     * @return create product
     */
    @RequestLine(V1_POST + PRODUCTS_MAPPING_PREFIX)
    BaseResponse<MergedProduct> create(MergedProduct product);

    /**
     * Find product operation.
     *
     * @param productName product name
     * @return product
     */
    @RequestLine(V1_GET + PRODUCTS_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedProduct> find(@Param(NAME) String productName);

    /**
     * Update product operation.
     *
     * @param productName product name
     * @param product     product
     * @return updated product
     */
    @RequestLine(V1_PUT + PRODUCTS_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedProduct> update(@Param(NAME) String productName, MergedProduct product);

    /**
     * Delete product operation.
     *
     * @param productName product name
     * @return deleted product
     */
    @RequestLine(V1_DELETE + PRODUCTS_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedProduct> delete(@Param(NAME) String productName);
}
