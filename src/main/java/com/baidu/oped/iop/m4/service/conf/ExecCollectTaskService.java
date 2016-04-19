package com.baidu.oped.iop.m4.service.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.collect.ExecCollectTaskDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.collect.MergedExecCollectTask;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * @author mason
 */
@FeignClient(name = "confExecCollectTaskClient", url = "${project.conf-man-url}")
public interface ExecCollectTaskService {

    String TASK = "taskName";
    String TASK_PATH = "/{" + TASK + "}";

    @RequestLine(V1_POST + CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX)
    BaseResponse<MergedExecCollectTask> create(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            ExecCollectTaskDto dto);

    @RequestLine(V1_DELETE + CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX + TASK_PATH)
    BaseResponse<MergedExecCollectTask> delete(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(TASK) String taskName);

    @RequestLine(V1_GET + CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX + TASK_PATH)
    BaseResponse<MergedExecCollectTask> find(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(TASK) String taskName);

    @RequestLine(V1_GET + CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX)
    BaseResponse<PageData<MergedExecCollectTask>> search(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_PUT + CONF_EXEC_COLLECT_TASK_MAPPING_PREFIX + TASK_PATH)
    BaseResponse<MergedExecCollectTask> update(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(TASK) String taskName, ExecCollectTaskDto dto);
}
