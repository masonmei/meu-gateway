package com.baidu.oped.iop.m4.service.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.HostDto;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedHost;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * Host Client for bns application.
 *
 * @author mason
 */
@FeignClient(name = "bnsHostClient", url = "${project.service-man-url}")
public interface HostService {

    String BNS_APPS_MAPPING_PREFIX = "products/{productName}/hosts";
    String HOST_NAME = "hostName";
    String HOST_NAME_PATH = "/{" + HOST_NAME + "}";

    /**
     * Search host operation.
     *
     * @param productName product name
     * @param queryMap    query parameter map
     * @return host page
     */
    @RequestLine(V1_GET + BNS_APPS_MAPPING_PREFIX)
    BaseResponse<PageData<MergedHost>> search(@Param(PRODUCT_NAME) String productName,
            @QueryMap Map<String, List<String>> queryMap);

    /**
     * Create host operation.
     *
     * @param productName product name
     * @param host        application
     * @return create host
     */
    @RequestLine(V1_POST + BNS_APPS_MAPPING_PREFIX)
    BaseResponse<MergedHost> create(@Param(PRODUCT_NAME) String productName, HostDto host);

    /**
     * Find host name operation.
     *
     * @param productName product name
     * @param hostName    application name
     * @return host
     */
    @RequestLine(V1_GET + BNS_APPS_MAPPING_PREFIX + HOST_NAME_PATH)
    BaseResponse<MergedHost> find(@Param(PRODUCT_NAME) String productName, @Param(HOST_NAME) String hostName);

    /**
     * Update host operation.
     *
     * @param productName product name
     * @param hostName    application name
     * @param host        application
     * @return updated host
     */
    @RequestLine(V1_PUT + BNS_APPS_MAPPING_PREFIX + HOST_NAME_PATH)
    BaseResponse<MergedHost> update(@Param(PRODUCT_NAME) String productName, @Param(HOST_NAME) String hostName,
            MergedHost host);

    /**
     * Delete host operation.
     *
     * @param productName product name
     * @param hostName    application name
     * @return deleted host
     */
    @RequestLine(V1_DELETE + BNS_APPS_MAPPING_PREFIX + HOST_NAME_PATH)
    BaseResponse<MergedHost> delete(@Param(PRODUCT_NAME) String productName, @Param(HOST_NAME) String hostName);
}
