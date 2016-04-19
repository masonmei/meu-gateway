package com.baidu.oped.iop.m4.mvc.rest.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_ACTION_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.alert.ActionDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.alert.MergedAction;
import com.baidu.oped.iop.m4.service.conf.ActionService;
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
 * @author mason
 */
@RestController
@RequestMapping(API_V1 + CONF_ACTION_MAPPING_PREFIX)
public class ActionController {
    private static final Logger LOG = LoggerFactory.getLogger(ActionController.class);

    private static final String ACTION = "actionName";
    private static final String ACTION_PATH = "{" + ACTION + "}";

    @Autowired
    private ActionService actionService;

    @RequestMapping(method = GET)
    public PageData<MergedAction> search(@PathVariable(PRODUCT_NAME) String productName,
            @ApiParam @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search Actions with productName: {}", productName);

        return actionService.search(productName, queryMap)
                .getData();
    }

    @RequestMapping(value = ACTION_PATH, method = GET)
    public MergedAction find(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(ACTION) String actionName) {
        LOG.debug("Find Action with productName: {} and ActionName: {}", productName, actionName);

        return actionService.find(productName, actionName)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedAction create(@PathVariable(PRODUCT_NAME) String productName, @RequestBody ActionDto dto) {
        LOG.debug("Create Action for productName: {} and appName: {} with ActionDto: {}", productName, dto);

        return actionService.create(productName, dto)
                .getData();
    }

    @RequestMapping(value = ACTION_PATH, method = PUT)
    public MergedAction update(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(ACTION) String taskName,
            @RequestBody ActionDto dto) {
        LOG.debug("Update Action {}.{}.{} with ActionDto: {}", productName, taskName, dto);

        return actionService.update(productName, taskName, dto)
                .getData();
    }

    @RequestMapping(value = ACTION_PATH, method = DELETE)
    public MergedAction delete(@PathVariable(
            PRODUCT_NAME) String productName, @PathVariable(ACTION) String taskName) {
        LOG.debug("Delete Action {}.{} ", productName, taskName);
        return actionService.delete(productName, taskName)
                .getData();
    }

}
