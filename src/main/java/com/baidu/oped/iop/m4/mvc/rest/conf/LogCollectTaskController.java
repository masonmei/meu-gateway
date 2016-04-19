package com.baidu.oped.iop.m4.mvc.rest.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_LOG_COLLECT_TASK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.collect.LogCollectTaskDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.collect.MergedLogCollectTask;
import com.baidu.oped.iop.m4.service.conf.LogCollectTaskService;
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
@RequestMapping(API_V1 + CONF_LOG_COLLECT_TASK_MAPPING_PREFIX)
public class LogCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(LogCollectTaskController.class);

    private static final String LOG_COLLECT_TASK = "taskName";
    private static final String LOG_COLLECT_TASK_PATH = "{" + LOG_COLLECT_TASK + "}";

    @Autowired
    private LogCollectTaskService logCollectTaskService;

    @RequestMapping(method = GET)
    public PageData<MergedLogCollectTask> search(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search LogCollectTasks with productName: {} and appName: {}", productName, appName);

        return logCollectTaskService.search(productName, appName, queryMap)
                .getData();
    }

    @RequestMapping(value = LOG_COLLECT_TASK_PATH, method = GET)
    public MergedLogCollectTask find(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(LOG_COLLECT_TASK) String taskName) {
        LOG.debug("Find LogCollectTask with productName: {}, appName: {} and LogCollectTaskName: {}", productName,
                appName, taskName);

        return logCollectTaskService.find(productName, appName, taskName)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedLogCollectTask create(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestBody LogCollectTaskDto dto) {
        LOG.debug("Create LogCollectTask for productName: {} and appName: {} with LogCollectTaskDto: {}", productName,
                appName, dto);

        return logCollectTaskService.create(productName, appName, dto)
                .getData();
    }

    @RequestMapping(value = LOG_COLLECT_TASK_PATH, method = PUT)
    public MergedLogCollectTask update(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(LOG_COLLECT_TASK) String taskName,
            @RequestBody LogCollectTaskDto dto) {
        LOG.debug("Update LogCollectTask {}.{}.{} with LogCollectTaskDto: {}", productName, appName, taskName, dto);

        return logCollectTaskService.update(productName, appName, taskName, dto)
                .getData();
    }

    @RequestMapping(value = LOG_COLLECT_TASK_PATH, method = DELETE)
    public MergedLogCollectTask delete(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(LOG_COLLECT_TASK) String taskName) {
        LOG.debug("Delete LogCollectTask {}.{}.{} ", productName, appName, taskName);

        return logCollectTaskService.delete(productName, appName, taskName)
                .getData();
    }

}
