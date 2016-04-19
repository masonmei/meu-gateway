package com.baidu.oped.iop.m4.mvc.rest.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_PATROL_COLLECT_TASK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.collect.PatrolCollectTaskDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.collect.MergedPatrolCollectTask;
import com.baidu.oped.iop.m4.service.conf.PatrolCollectTaskService;
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
@RequestMapping(API_V1 + CONF_PATROL_COLLECT_TASK_MAPPING_PREFIX)
public class PatrolCollectTaskController {
    private static final Logger LOG = LoggerFactory.getLogger(PatrolCollectTaskController.class);

    private static final String PATROLCOLLECTTASK = "taskName";
    private static final String PATROLCOLLECTTASK_PATH = "{" + PATROLCOLLECTTASK + "}";

    @Autowired
    private PatrolCollectTaskService patrolCollectTaskService;

    @RequestMapping(method = GET)
    public PageData<MergedPatrolCollectTask> search(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search PatrolCollectTasks with productName: {} and appName: {}", productName, appName);

        return patrolCollectTaskService.search(productName, appName, queryMap)
                .getData();
    }

    @RequestMapping(value = PATROLCOLLECTTASK_PATH, method = GET)
    public MergedPatrolCollectTask find(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(PATROLCOLLECTTASK) String taskName) {
        LOG.debug("Find PatrolCollectTask with productName: {}, appName: {} and PatrolCollectTaskName: {}", productName,
                appName, taskName);

        return patrolCollectTaskService.find(productName, appName, taskName)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedPatrolCollectTask create(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestBody PatrolCollectTaskDto dto) {
        LOG.debug("Create PatrolCollectTask for productName: {} and appName: {} with PatrolCollectTaskDto: {}",
                productName, appName, dto);

        return patrolCollectTaskService.create(productName, appName, dto)
                .getData();
    }

    @RequestMapping(value = PATROLCOLLECTTASK_PATH, method = PUT)
    public MergedPatrolCollectTask update(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(PATROLCOLLECTTASK) String taskName,
            @RequestBody PatrolCollectTaskDto dto) {
        LOG.debug("Update PatrolCollectTask {}.{}.{} with PatrolCollectTaskDto: {}", productName, appName, taskName,
                dto);

        return patrolCollectTaskService.update(productName, appName, taskName, dto)
                .getData();
    }

    @RequestMapping(value = PATROLCOLLECTTASK_PATH, method = DELETE)
    public MergedPatrolCollectTask delete(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(PATROLCOLLECTTASK) String taskName) {
        LOG.debug("Delete PatrolCollectTask {}.{}.{} ", productName, appName, taskName);

        return patrolCollectTaskService.delete(productName, appName, taskName)
                .getData();
    }

}
