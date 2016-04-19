package com.baidu.oped.iop.m4.mvc.rest.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.MergedApp;
import com.baidu.oped.iop.m4.service.bns.ApplicationService;
import com.baidu.oped.iop.m4.utils.PageData;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Application controller.
 *
 * @author mason
 */
@RestController
@RequestMapping(API_V1 + "products/{productName}/apps")
public class ApplicationController {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

    private static final String NAME_PATH = "{" + NAME + "}";

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(method = GET)
    public PageData<MergedApp> search(@PathVariable(PRODUCT_NAME) String productName,
            @ApiParam @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search applications.");

        return applicationService.search(productName, queryMap)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedApp create(@PathVariable(PRODUCT_NAME) String productName, @RequestBody MergedApp app) {
        LOG.debug("Create application with product: {} and app: {}", productName, app);
        return applicationService.create(productName, app)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = GET)
    public MergedApp find(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(NAME) String name) {
        LOG.debug("Find application with productName: {} and name: {}", productName, name);

        return applicationService.find(productName, name)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = PUT)
    public MergedApp update(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(NAME) String name,
            @RequestBody MergedApp app) {
        LOG.debug("Update application with productName: {} and app: {}", name, app);

        return applicationService.update(productName, name, app)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = DELETE)
    public MergedApp delete(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(NAME) String name) {
        LOG.debug("Delete application with productName:{} and name: {}", name);

        return applicationService.delete(productName, name)
                .getData();
    }

}
