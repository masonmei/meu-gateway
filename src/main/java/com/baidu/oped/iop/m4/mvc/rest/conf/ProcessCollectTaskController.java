package com.baidu.oped.iop.m4.mvc.rest.conf;


import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_PROCESS_COLLECT_TASK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.collect.ProcessCollectTaskDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.collect.MergedProcessCollectTask;
import com.baidu.oped.iop.m4.service.conf.ProcessCollectTaskService;
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
@RequestMapping(API_V1 + CONF_PROCESS_COLLECT_TASK_MAPPING_PREFIX)
public class ProcessCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessCollectTaskController.class);

    private static final String PROCESS_COLLECT_TASK = "taskName";
    private static final String PROCESS_COLLECT_TASK_PATH = "{" + PROCESS_COLLECT_TASK + "}";

    @Autowired
    private ProcessCollectTaskService processCollectTaskService;

    @RequestMapping(method = GET)
    public PageData<MergedProcessCollectTask> search(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search ProcessCollectTasks with productName: {} and appName: {}", productName, appName);

        return processCollectTaskService.search(productName, appName, queryMap)
                .getData();
    }

    @RequestMapping(value = PROCESS_COLLECT_TASK_PATH, method = GET)
    public MergedProcessCollectTask find(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(PROCESS_COLLECT_TASK) String taskName) {
        LOG.debug("Find ProcessCollectTask with productName: {}, appName: {} and ProcessCollectTaskName: {}",
                productName, appName, taskName);

        return processCollectTaskService.find(productName, appName, taskName)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedProcessCollectTask create(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestBody ProcessCollectTaskDto dto) {
        LOG.debug("Create ProcessCollectTask for productName: {} and appName: {} with ProcessCollectTaskDto: {}",
                productName, appName, dto);

        return processCollectTaskService.create(productName, appName, dto)
                .getData();
    }

    @RequestMapping(value = PROCESS_COLLECT_TASK_PATH, method = PUT)
    public MergedProcessCollectTask update(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(PROCESS_COLLECT_TASK) String taskName,
            @RequestBody ProcessCollectTaskDto dto) {
        LOG.debug("Update ProcessCollectTask {}.{}.{} with ProcessCollectTaskDto: {}", productName, appName, taskName,
                dto);

        return processCollectTaskService.update(productName, appName, taskName, dto)
                .getData();
    }

    @RequestMapping(value = PROCESS_COLLECT_TASK_PATH, method = DELETE)
    public MergedProcessCollectTask delete(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(PROCESS_COLLECT_TASK) String taskName) {
        LOG.debug("Delete ProcessCollectTask {}.{}.{} ", productName, appName, taskName);

        return processCollectTaskService.delete(productName, appName, taskName)
                .getData();
    }

}
