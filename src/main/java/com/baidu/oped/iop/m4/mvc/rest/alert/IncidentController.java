package com.baidu.oped.iop.m4.mvc.rest.alert;

import static com.baidu.oped.iop.m4.service.alert.IncidentService.ALERT_APP_POLICY_INCIDENTS_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.service.alert.IncidentService.ALERT_POLICY_INCIDENTS_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.service.alert.IncidentService.ALERT_PRODUCT_POLICY_INCIDENTS_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.baidu.oped.iop.m4.mvc.dto.alert.MergedIncident;
import com.baidu.oped.iop.m4.service.alert.IncidentService;
import com.baidu.oped.iop.m4.utils.PageData;

import feign.QueryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mason
 */
@RestController
@RequestMapping(API_V1)
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    /**
     * Search incidents of product.
     *
     * @param productName product name
     * @param queryMap    query params map
     * @return incidents
     */
    @RequestMapping(value = ALERT_PRODUCT_POLICY_INCIDENTS_MAPPING_PREFIX, method = GET)
    PageData<MergedIncident> searchProductIncidents(@PathVariable(PRODUCT_NAME) String productName,
            @QueryMap MultiValueMap<String, String> queryMap) {
        return incidentService.searchProductIncidents(productName, queryMap)
                .getData();
    }

    /**
     * Search incidents of application
     *
     * @param productName product name
     * @param appName     app name
     * @param queryMap    query params map
     * @return incidents
     */
    @RequestMapping(value = ALERT_APP_POLICY_INCIDENTS_MAPPING_PREFIX, method = GET)
    PageData<MergedIncident> searchAppIncidents(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @QueryMap MultiValueMap<String, String> queryMap) {

        return incidentService.searchAppIncidents(productName, appName, queryMap)
                .getData();
    }


    /**
     * Search incidents of policy
     *
     * @param productName product name
     * @param appName     app name
     * @param policyName  policy name
     * @param queryMap    query params map
     * @return incidents
     */
    @RequestMapping(value = ALERT_POLICY_INCIDENTS_MAPPING_PREFIX, method = GET)
    PageData<MergedIncident> searchPolicyIncidents(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable("policyName") String policyName,
            @RequestParam MultiValueMap<String, String> queryMap) {
        return incidentService.searchPolicyIncidents(productName, appName, policyName, queryMap)
                .getData();
    }
}
