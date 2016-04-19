package com.baidu.oped.iop.m4.mvc.rest.conf;


import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.collect.ExecCollectTaskDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.collect.MergedExecCollectTask;
import com.baidu.oped.iop.m4.service.conf.ExecCollectTaskService;
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
@RequestMapping(API_V1 + CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX)
public class ExecCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(ExecCollectTaskController.class);

    private static final String EXEC_COLLECT_TASK = "taskName";
    private static final String EXEC_COLLECT_TASK_PATH = "{" + EXEC_COLLECT_TASK + "}";

    @Autowired
    private ExecCollectTaskService execCollectTaskService;

    @RequestMapping(method = GET)
    public PageData<MergedExecCollectTask> search(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search ExecCollectTasks with productName: {} and appName: {}", productName, appName);

        return execCollectTaskService.search(productName, appName, queryMap)
                .getData();
    }

    @RequestMapping(value = EXEC_COLLECT_TASK_PATH, method = GET)
    public MergedExecCollectTask find(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(EXEC_COLLECT_TASK) String taskName) {
        LOG.debug("Find ExecCollectTask with productName: {}, appName: {} and ExecCollectTaskName: {}", productName,
                appName, taskName);

        return execCollectTaskService.find(productName, appName, taskName)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedExecCollectTask create(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestBody ExecCollectTaskDto dto) {
        LOG.debug("Create ExecCollectTask for productName: {} and appName: {} with ExecCollectTaskDto: {}", productName,
                appName, dto);

        return execCollectTaskService.create(productName, appName, dto)
                .getData();
    }

    @RequestMapping(value = EXEC_COLLECT_TASK_PATH, method = PUT)
    public MergedExecCollectTask update(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(EXEC_COLLECT_TASK) String taskName,
            @RequestBody ExecCollectTaskDto dto) {
        LOG.debug("Update ExecCollectTask {}.{}.{} with ExecCollectTaskDto: {}", productName, appName, taskName, dto);

        return execCollectTaskService.update(productName, appName, taskName, dto)
                .getData();
    }

    @RequestMapping(value = EXEC_COLLECT_TASK_PATH, method = DELETE)
    public MergedExecCollectTask delete(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(EXEC_COLLECT_TASK) String taskName) {
        LOG.debug("Delete ExecCollectTask {}.{}.{} ", productName, appName, taskName);

        return execCollectTaskService.delete(productName, appName, taskName)
                .getData();
    }

}
