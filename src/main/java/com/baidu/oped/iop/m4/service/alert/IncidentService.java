package com.baidu.oped.iop.m4.service.alert;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;

import com.baidu.oped.iop.m4.mvc.dto.alert.MergedIncident;
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
@FeignClient(name = "bnsApplicationClient", url = "${project.incident-url}")
public interface IncidentService {

    String ALERT_PRODUCT_POLICY_INCIDENTS_MAPPING_PREFIX = "products/{productName}/incidents";
    String ALERT_APP_POLICY_INCIDENTS_MAPPING_PREFIX = "products/{productName}/apps/{appName}/incidents";
    String ALERT_POLICY_INCIDENTS_MAPPING_PREFIX =
            "products/{productName}/apps/{appName}/policies/{policyName}/incidents";

    /**
     * Search incidents of product.
     *
     * @param productName product name
     * @param queryMap    query params map
     * @return incidents
     */
    @RequestLine(V1_GET + ALERT_PRODUCT_POLICY_INCIDENTS_MAPPING_PREFIX)
    BaseResponse<PageData<MergedIncident>> searchProductIncidents(@Param(PRODUCT_NAME) String productName,
            @QueryMap Map<String, List<String>> queryMap);

    /**
     * Search incidents of application
     *
     * @param productName product name
     * @param appName     app name
     * @param queryMap    query params map
     * @return incidents
     */
    @RequestLine(V1_GET + ALERT_APP_POLICY_INCIDENTS_MAPPING_PREFIX)
    BaseResponse<PageData<MergedIncident>> searchAppIncidents(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @QueryMap Map<String, List<String>> queryMap);


    /**
     * Search incidents of policy
     *
     * @param productName product name
     * @param appName     app name
     * @param policyName  policy name
     * @param queryMap    query params map
     * @return incidents
     */
    @RequestLine(V1_GET + ALERT_POLICY_INCIDENTS_MAPPING_PREFIX)
    BaseResponse<PageData<MergedIncident>> searchPolicyIncidents(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @Param("policyName") String policyName,
            @QueryMap Map<String, List<String>> queryMap);

}
