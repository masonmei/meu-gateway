package com.baidu.oped.iop.m4.mvc.rest.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.HostDto;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedHost;
import com.baidu.oped.iop.m4.service.bns.HostService;
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
 * Host controller.
 *
 * @author mason
 */
@RestController
@RequestMapping(API_V1 + "products/{productName}/hosts")
public class HostController {
    private static final Logger LOG = LoggerFactory.getLogger(HostController.class);

    private static final String NAME_PATH = "{" + NAME + "}";

    @Autowired
    private HostService hostService;

    @RequestMapping(method = GET)
    public PageData<MergedHost> search(@PathVariable(PRODUCT_NAME) String productName,
            @ApiParam @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search applications.");

        return hostService.search(productName, queryMap).getData();
    }

    @RequestMapping(method = POST)
    public MergedHost create(@PathVariable(PRODUCT_NAME) String productName, @RequestBody HostDto host) {
        LOG.debug("Create application with product: {} and host: {}", productName, host);
        return hostService.create(productName, host).getData();
    }

    @RequestMapping(value = NAME_PATH, method = GET)
    public MergedHost find(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(NAME) String name) {
        LOG.debug("Find application with productName: {} and name: {}", productName, name);

        return hostService.find(productName, name).getData();
    }

    @RequestMapping(value = NAME_PATH, method = PUT)
    public MergedHost update(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(NAME) String name,
            @RequestBody MergedHost host) {
        LOG.debug("Update application with productName: {} and host: {}", name, host);

        return hostService.update(productName, name, host).getData();
    }

    @RequestMapping(value = NAME_PATH, method = DELETE)
    public MergedHost delete(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(NAME) String name) {
        LOG.debug("Delete application with productName:{} and name: {}", name);

        return hostService.delete(productName, name).getData();
    }

}
