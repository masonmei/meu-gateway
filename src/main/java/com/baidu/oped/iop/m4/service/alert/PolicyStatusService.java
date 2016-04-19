package com.baidu.oped.iop.m4.service.alert;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;

import com.baidu.oped.iop.m4.mvc.dto.alert.DatetimeRangeDto;
import com.baidu.oped.iop.m4.mvc.dto.alert.MergedPolicyBlockStatus;
import com.baidu.oped.iop.m4.mvc.dto.alert.MergedPolicyStatus;
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
@FeignClient(name = "alertPolicyStatusClient", url = "{project.policy-status-url}")
public interface PolicyStatusService {
    String POLICY_NAME = "policyName";

    String ALERT_POLICY_STATUS_MAPPING_PREFIX = "products/{productName}/apps/{appName}/policies/{policyName}/status";
    String ALERT_POLICY_BLOCK_STATUS_MAPPING_PREFIX =
            "products/{productName}/apps/{appName}/policies/{policyName}/blockStatus";
    String ALERT_POLICY_BLOCK_MAPPING_PREFIX = "products/{productName}/apps/{appName}/policies/{policyName}/block";
    String ALERT_POLICY_UNBLOCK_MAPPING_PREFIX = "products/{productName}/apps/{appName}/policies/{policyName}/unblock";

    @RequestLine(V1_GET + ALERT_POLICY_STATUS_MAPPING_PREFIX)
    BaseResponse<List<MergedPolicyStatus>> policyStatus(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @Param(POLICY_NAME) String policyName,
            @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_GET + ALERT_POLICY_BLOCK_STATUS_MAPPING_PREFIX)
    BaseResponse<List<MergedPolicyBlockStatus>> policyBlockStatus(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @Param(POLICY_NAME) String policyName,
            @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_POST + ALERT_POLICY_BLOCK_MAPPING_PREFIX)
    BaseResponse<Void> blockPolicies(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(POLICY_NAME) String policyName, DatetimeRangeDto dto);

    @RequestLine(V1_POST + ALERT_POLICY_UNBLOCK_MAPPING_PREFIX)
    BaseResponse<Void> unblockPolicies(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(POLICY_NAME) String policyName);
}
