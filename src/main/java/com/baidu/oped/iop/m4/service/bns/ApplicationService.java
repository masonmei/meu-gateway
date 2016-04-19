package com.baidu.oped.iop.m4.service.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.MergedApp;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * Application Client for bns application.
 *
 * @author mason
 */
@FeignClient(name = "bnsApplicationClient", url = "${project.service-man-url}")
public interface ApplicationService {

    String BNS_APPS_MAPPING_PREFIX = "products/{productName}/apps";
    String APP_NAME = "appName";
    String APP_NAME_PATH = "/{" + APP_NAME + "}";

    /**
     * Search application operation.
     *
     * @param productName product name
     * @param queryMap    query parameter map
     * @return application page
     */
    @RequestLine(V1_GET + BNS_APPS_MAPPING_PREFIX)
    BaseResponse<PageData<MergedApp>> search(@Param(PRODUCT_NAME) String productName,
            @QueryMap Map<String, List<String>> queryMap);

    /**
     * Create application operation.
     *
     * @param productName product name
     * @param app         application
     * @return create application
     */
    @RequestLine(V1_POST + BNS_APPS_MAPPING_PREFIX)
    BaseResponse<MergedApp> create(@Param(PRODUCT_NAME) String productName, MergedApp app);

    /**
     * Find application name operation.
     *
     * @param productName product name
     * @param appName     application name
     * @return application
     */
    @RequestLine(V1_GET + BNS_APPS_MAPPING_PREFIX + APP_NAME_PATH)
    BaseResponse<MergedApp> find(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName);

    /**
     * Update application operation.
     *
     * @param productName product name
     * @param appName     application name
     * @param app         application
     * @return updated application
     */
    @RequestLine(V1_PUT + BNS_APPS_MAPPING_PREFIX + APP_NAME_PATH)
    BaseResponse<MergedApp> update(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName, MergedApp app);

    /**
     * Delete application operation.
     *
     * @param productName product name
     * @param appName     application name
     * @return deleted application
     */
    @RequestLine(V1_DELETE + BNS_APPS_MAPPING_PREFIX + APP_NAME_PATH)
    BaseResponse<MergedApp> delete(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName);
}
