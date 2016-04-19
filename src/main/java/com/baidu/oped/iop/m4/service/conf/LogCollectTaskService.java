package com.baidu.oped.iop.m4.service.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_LOG_COLLECT_TASK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.collect.LogCollectTaskDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.collect.MergedLogCollectTask;
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
@FeignClient(name = "confLogCollectTaskClient", url = "${project.conf-man-url}")
public interface LogCollectTaskService {

    String TASK = "taskName";
    String TASK_PATH = "/{" + TASK + "}";

    @RequestLine(V1_POST + CONF_LOG_COLLECT_TASK_MAPPING_PREFIX)
    BaseResponse<MergedLogCollectTask> create(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            LogCollectTaskDto dto);

    @RequestLine(V1_DELETE + CONF_LOG_COLLECT_TASK_MAPPING_PREFIX + TASK_PATH)
    BaseResponse<MergedLogCollectTask> delete(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(TASK) String taskName);

    @RequestLine(V1_GET + CONF_LOG_COLLECT_TASK_MAPPING_PREFIX + TASK_PATH)
    BaseResponse<MergedLogCollectTask> find(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(TASK) String taskName);

    @RequestLine(V1_GET + CONF_LOG_COLLECT_TASK_MAPPING_PREFIX)
    BaseResponse<PageData<MergedLogCollectTask>> search(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_PUT + CONF_LOG_COLLECT_TASK_MAPPING_PREFIX + TASK_PATH)
    BaseResponse<MergedLogCollectTask> update(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(TASK) String taskName, LogCollectTaskDto dto);

}
