package com.baidu.oped.iop.m4.mvc.rest.alert;

import static com.baidu.oped.iop.m4.service.alert.PolicyStatusService.ALERT_POLICY_BLOCK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.service.alert.PolicyStatusService.ALERT_POLICY_BLOCK_STATUS_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.service.alert.PolicyStatusService.ALERT_POLICY_STATUS_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.service.alert.PolicyStatusService.ALERT_POLICY_UNBLOCK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.service.alert.PolicyStatusService.POLICY_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import com.baidu.oped.iop.m4.mvc.dto.alert.DatetimeRangeDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.MergedPolicyBlockStatus;
import com.baidu.oped.iop.m4.mvc.dto.alert.MergedPolicyStatus;
import com.baidu.oped.iop.m4.service.alert.PolicyStatusService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mason
 */
@RestController
@RequestMapping(API_V1)
public class PolicyStatusController {
    private static final Logger LOG = LoggerFactory.getLogger(PolicyStatusController.class);

    @Autowired
    private PolicyStatusService policyStatusService;

    @RequestMapping(value = ALERT_POLICY_STATUS_MAPPING_PREFIX, method = RequestMethod.GET)
    List<MergedPolicyStatus> policyStatus(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(POLICY_NAME) String policyName,
            @RequestParam MultiValueMap<String, String> queryMap) {
        return policyStatusService.policyStatus(productName, appName, policyName, queryMap)
                .getData();
    }

    @RequestMapping(value = ALERT_POLICY_BLOCK_STATUS_MAPPING_PREFIX, method = RequestMethod.GET)
    List<MergedPolicyBlockStatus> policyBlockStatus(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(APP_NAME) String appName, @PathVariable(POLICY_NAME) String policyName,
            @RequestParam MultiValueMap<String, String> queryMap) {
        return policyStatusService.policyBlockStatus(productName, appName, policyName, queryMap)
                .getData();
    }

    @RequestMapping(value = ALERT_POLICY_BLOCK_MAPPING_PREFIX, method = RequestMethod.POST)
    Void blockPolicies(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(POLICY_NAME) String policyName, DatetimeRangeDto dto) {
        return policyStatusService.blockPolicies(productName, appName, policyName, dto)
                .getData();
    }

    @RequestMapping(value = ALERT_POLICY_UNBLOCK_MAPPING_PREFIX, method = RequestMethod.POST)
    Void unblockPolicies(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(APP_NAME) String appName,
            @PathVariable(POLICY_NAME) String policyName) {
        return policyStatusService.unblockPolicies(productName, appName, policyName)
                .getData();
    }
}
