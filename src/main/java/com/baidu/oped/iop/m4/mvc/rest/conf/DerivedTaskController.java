package com.baidu.oped.iop.m4.mvc.rest.conf;


import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_DERIVED_TASK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.collect.DerivedTaskDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.collect.MergedDerivedTask;
import com.baidu.oped.iop.m4.service.conf.DerivedTaskService;
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
 * @author mason
 */
@RestController
@RequestMapping(API_V1 + CONF_DERIVED_TASK_MAPPING_PREFIX)
public class DerivedTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(DerivedTaskController.class);

    private static final String DERIVED_TASK = "policyName";
    private static final String DERIVED_TASK_PATH = "{" + DERIVED_TASK + "}";

    @Autowired
    private DerivedTaskService derivedTaskService;

    @RequestMapping(method = GET)
    public PageData<MergedDerivedTask> search(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search DerivedTasks with productName: {} and appName: {}", productName, appName);

        return derivedTaskService.search(productName, appName, queryMap).getData();
    }

    @RequestMapping(value = DERIVED_TASK_PATH, method = GET)
    public MergedDerivedTask find(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(DERIVED_TASK) String taskName) {
        LOG.debug("Find DerivedTask with productName: {}, appName: {} and DerivedTaskName: {}", productName, appName,
                taskName);

        return derivedTaskService.find(productName, appName, taskName)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedDerivedTask create(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestBody DerivedTaskDto dto) {
        LOG.debug("Create DerivedTask for productName: {} and appName: {} with DerivedTaskDto: {}", productName,
                appName, dto);

        return derivedTaskService.create(productName, appName, dto)
                .getData();
    }

    @RequestMapping(value = DERIVED_TASK_PATH, method = PUT)
    public MergedDerivedTask update(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(DERIVED_TASK) String taskName,
            @RequestBody DerivedTaskDto dto) {
        LOG.debug("Update DerivedTask {}.{}.{} with DerivedTaskDto: {}", productName, appName, taskName, dto);

        return derivedTaskService.update(productName, appName, taskName, dto)
                .getData();
    }

    @RequestMapping(value = DERIVED_TASK_PATH, method = DELETE)
    public MergedDerivedTask delete(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(DERIVED_TASK) String taskName) {
        LOG.debug("Delete DerivedTask {}.{}.{} ", productName, appName, taskName);

        return derivedTaskService.delete(productName, appName, taskName)
                .getData();
    }

}
