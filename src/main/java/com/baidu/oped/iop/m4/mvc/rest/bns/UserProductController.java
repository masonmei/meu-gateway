package com.baidu.oped.iop.m4.mvc.rest.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.custom.security.AppUserDetails;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedProduct;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedRole;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedUser;
import com.baidu.oped.iop.m4.mvc.dto.bns.UserDto;
import com.baidu.oped.iop.m4.service.bns.ProductService;
import com.baidu.oped.iop.m4.utils.PageData;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Product controller.
 *
 * @author mason
 */
@RestController
@RequestMapping(API_V1 + "products")
public class UserProductController {
    private static final Logger LOG = LoggerFactory.getLogger(UserProductController.class);

    private static final String USER_ID = "userId";
    private static final String NAME_PATH = "{" + NAME + "}";

    @Autowired
    private ProductService productService;

    @RequestMapping(method = GET)
    public List<MergedProduct> search(Principal principal,
            @ApiParam @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search Products.");
        Assert.state(principal instanceof AbstractAuthenticationToken, "Principal must be and authentication token.");
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) principal;
        Object details = token.getPrincipal();
        Assert.state(details instanceof AppUserDetails, "Token Details must be and AppUserDetails");
        AppUserDetails userDetails = (AppUserDetails) details;

        return productService.search(String.format("%d", userDetails.getId()), queryMap)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedProduct create(@RequestBody MergedProduct product) {
        LOG.debug("Create product with : {}", product);
        return productService.create(product)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = GET)
    public MergedProduct find(@PathVariable(NAME) String name) {
        LOG.debug("Find Product with name: {}", name);

        return productService.find(name)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = PUT)
    public MergedProduct update(@PathVariable(NAME) String name, @RequestBody MergedProduct product) {
        LOG.debug("Update product: {} with: {}", name, product);
        return productService.update(name, product)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = DELETE)
    public MergedProduct delete(@PathVariable(NAME) String name) {
        LOG.debug("Delete product: {}", name);
        return productService.delete(name)
                .getData();
    }

    /**
     * Make product of given user default.
     *
     * @param productName product name
     * @return success or not
     */
    @RequestMapping(value = "{productName}/default", method = PUT)
    Void makeProductDefault(Principal principal, @PathVariable(PRODUCT_NAME) String productName) {
        Assert.state(principal instanceof AbstractAuthenticationToken, "Principal must be and authentication token.");
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) principal;
        Object details = token.getPrincipal();
        Assert.state(details instanceof AppUserDetails, "Token Details must be and AppUserDetails");
        AppUserDetails userDetails = (AppUserDetails) details;
        return productService.makeProductDefault(String.format("%d", userDetails.getId()), productName)
                .getData();
    }


    /**
     * List the roles of given products.
     *
     * @param productName product name
     * @return all the product roles
     */
    @RequestMapping(value = "{productName}/roles", method = GET)
    List<MergedRole> searchProductRole(@PathVariable(PRODUCT_NAME) String productName) {
        LOG.debug("List roles of product: {}.", productName);
        return productService.searchProductRole(productName)
                .getData();
    }

    /**
     * Search the users of given role of product.
     *
     * @param productName product name
     * @param roleId      role id
     * @param queryMap    query params
     * @return user page
     */
    @RequestMapping(value = "{productName}/roles/{roleId}/users", method = GET)
    PageData<MergedUser> searchProductRoleUsers(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable("roleId") Long roleId, @RequestParam MultiValueMap<String, String> queryMap) {
        return productService.searchProductRoleUsers(productName, roleId, queryMap)
                .getData();
    }

    /**
     * Add product role users.
     *
     * @param productName product name
     * @param roleId      role id
     * @param dto         users dto
     * @return success or not.
     */
    @RequestMapping(value = "{productName}/roles/{roleId}/users", method = POST)
    Void addProductRoleUsers(@PathVariable(PRODUCT_NAME) String productName, @PathVariable("roleId") Long roleId,
            @RequestBody UserDto dto) {
        return productService.addProductRoleUsers(productName, roleId, dto)
                .getData();
    }

    /**
     * Remove role users of product.
     *
     * @param productName product name
     * @param roleId      role id
     * @param userId      user id
     * @return success or not
     */
    @RequestMapping(value = "{productName}/roles/{roleId}/users/{userId}", method = DELETE)
    Void deleteProductRoleUsers(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable("roleId") Long roleId, @PathVariable("userId") String userId) {
        return productService.deleteProductRoleUsers(productName, roleId, userId).getData();
    }


}
