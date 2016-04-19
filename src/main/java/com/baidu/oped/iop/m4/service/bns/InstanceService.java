package com.baidu.oped.iop.m4.service.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.MergedInstance;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * Instance Client for bns application.
 *
 * @author mason
 */
@FeignClient(name = "bnsInstanceClient", url = "${project.service-man-url}")
public interface InstanceService {

    String BNS_INSTANCES_MAPPING_PREFIX = "products/{productName}/apps/{appName}";
    String INSTANCE_NAME = "instanceName";
    String INSTANCE_NAME_PATH = "/{" + INSTANCE_NAME + "}";

    /**
     * Search instance operation.
     *
     * @param productName product name
     * @param appName     application name
     * @param queryMap    query parameter map
     * @return instance page
     */
    @RequestLine(V1_GET + BNS_INSTANCES_MAPPING_PREFIX)
    BaseResponse<PageData<MergedInstance>> search(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @QueryMap Map<String, List<String>> queryMap);

    /**
     * Create instance operation.
     *
     * @param productName product name
     * @param appName     application name
     * @param instance    instance
     * @return created instance
     */
    @RequestLine(V1_POST + BNS_INSTANCES_MAPPING_PREFIX)
    BaseResponse<MergedInstance> create(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            MergedInstance instance);

    /**
     * Find instance name operation.
     *
     * @param productName  product name
     * @param appName      application name
     * @param instanceName instance name
     * @return instance
     */
    @RequestLine(V1_GET + BNS_INSTANCES_MAPPING_PREFIX + INSTANCE_NAME_PATH)
    BaseResponse<MergedInstance> find(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(INSTANCE_NAME) String instanceName);

    /**
     * Update instance operation.
     *
     * @param productName  product name
     * @param appName      application name
     * @param instanceName instance name
     * @param instance     instance
     * @return updated instance
     */
    @RequestLine(V1_PUT + BNS_INSTANCES_MAPPING_PREFIX + INSTANCE_NAME_PATH)
    BaseResponse<MergedInstance> update(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(INSTANCE_NAME) String instanceName, MergedInstance instance);

    /**
     * Delete instance operation.
     *
     * @param productName  product name
     * @param appName      application name
     * @param instanceName instance name
     * @return deleted instance
     */
    @RequestLine(V1_DELETE + BNS_INSTANCES_MAPPING_PREFIX + INSTANCE_NAME_PATH)
    BaseResponse<MergedInstance> delete(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(INSTANCE_NAME) String instanceName);
}
