package com.baidu.oped.iop.m4.mvc.rest.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.MergedApp;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedInstance;
import com.baidu.oped.iop.m4.service.bns.InstanceService;
import com.baidu.oped.iop.m4.utils.PageData;

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
@RequestMapping(API_V1 + "products/{productName}/apps/{appName}/instances")
public class InstanceController {
    private static final Logger LOG = LoggerFactory.getLogger(InstanceController.class);

    private static final String NAME_PATH = "{" + NAME + "}";

    @Autowired
    private InstanceService instanceService;

    @RequestMapping(method = GET)
    public PageData<MergedInstance> search(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search instances with productName:{} and appName: {}.", productName, appName);

        return instanceService.search(productName, appName, queryMap)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedInstance create(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @RequestBody MergedInstance instance) {
        LOG.debug("Create instance with productName:{} and appName : {} and instance: {}", productName, appName,
                instance);
        return instanceService.create(productName, appName, instance)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = GET)
    public MergedInstance find(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(NAME) String name) {
        LOG.debug("Find application with productName: {} , appName: {} and name: {}", productName, appName, name);

        return instanceService.find(productName, appName, name)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = PUT)
    public MergedInstance update(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(NAME) String name, @RequestBody MergedInstance instance) {
        LOG.debug("Update application with productName: {} appName: {} and instance: {}", name, appName, instance);

        return instanceService.update(productName, appName, name, instance)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = DELETE)
    public MergedInstance delete(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(NAME) String name) {
        LOG.debug("Delete application with productName:{} and name: {}", name);

        return instanceService.delete(productName, appName, name)
                .getData();
    }

}
