package com.baidu.oped.iop.m4.mvc.rest.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_POLICY_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.alert.PolicyDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.alert.MergedPolicy;
import com.baidu.oped.iop.m4.service.conf.PolicyService;
import com.baidu.oped.iop.m4.utils.PageData;

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
@RequestMapping(value = API_V1 + CONF_POLICY_MAPPING_PREFIX)
public class PolicyController {
    private static final String POLICY = "policyName";
    private static final String POLICY_PATH = "{" + POLICY + "}";

    @Autowired
    private PolicyService policyService;

    @RequestMapping(method = GET)
    PageData<MergedPolicy> search(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @RequestParam MultiValueMap<String, String> queryMap) {
        return policyService.search(productName, appName, queryMap)
                .getData();
    }

    @RequestMapping(value = POLICY_PATH, method = GET)
    MergedPolicy find(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(POLICY) String policyName) {
        return policyService.find(productName, appName, policyName)
                .getData();
    }

    @RequestMapping(method = POST)
    MergedPolicy create(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @RequestBody PolicyDto dto) {
        return policyService.create(productName, appName, dto)
                .getData();
    }

    @RequestMapping(value = POLICY_PATH, method = PUT)
    MergedPolicy update(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(POLICY) String policyName, @RequestBody PolicyDto dto) {
        return policyService.update(productName, appName, policyName, dto)
                .getData();
    }

    @RequestMapping(value = POLICY_PATH, method = DELETE)
    MergedPolicy delete(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(POLICY) String policyName) {
        return policyService.delete(productName, appName, policyName)
                .getData();
    }
}