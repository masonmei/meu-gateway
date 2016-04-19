package com.baidu.oped.iop.m4.mvc.rest.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.custom.security.AppUserDetails;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedProduct;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedUser;
import com.baidu.oped.iop.m4.service.bns.ProductService;
import com.baidu.oped.iop.m4.service.bns.UserService;
import com.baidu.oped.iop.m4.utils.PageData;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Product controller.
 *
 * @author mason
 */
@RestController
@RequestMapping(API_V1 + "users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static final String USER_ID = "userId";
    private static final String NAME_PATH = "{" + NAME + "}";

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public PageData<MergedUser> search(@RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search Products.");

        return userService.search(queryMap).getData();
    }

    @RequestMapping(method = POST)
    public MergedUser create(@RequestBody MergedUser user) {
        LOG.debug("Create user with : {}", user);
        return userService.create(user)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = GET)
    public MergedUser find(@PathVariable(NAME) String name) {
        LOG.debug("Find User with name: {}", name);

        return userService.find(name)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = PUT)
    public MergedUser update(@PathVariable(NAME) String name, @RequestBody MergedUser user) {
        LOG.debug("Update user: {} with: {}", name, user);
        return userService.update(name, user)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = DELETE)
    public MergedUser delete(@PathVariable(NAME) String name) {
        LOG.debug("Delete user: {}", name);
        return userService.delete(name).getData();
    }

    @RequestMapping(value = "me", method = GET)
    public AppUserDetails me(Principal principal) {
        Assert.state(principal instanceof AbstractAuthenticationToken, "Principal must be and authentication token.");
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) principal;
        Object details = token.getPrincipal();
        Assert.state(details instanceof AppUserDetails, "Token Details must be and AppUserDetails");
        AppUserDetails userDetails = (AppUserDetails) details;

        AppUserDetails myUserDetails = userService.me(userDetails.getUsername(), "bdpassport")
                .getData();
        if(!StringUtils.hasText(myUserDetails.getDefaultProduct())){
            myUserDetails.setDefaultProduct("testProduct");
        }
        return myUserDetails;
    }


}
