package com.baidu.oped.iop.m4.service.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_POLICY_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.alert.PolicyDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.alert.MergedPolicy;
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
@FeignClient(name = "confPolicyClient", url = "${project.conf-man-url}")
public interface PolicyService {

    String POLICY = "policyName";
    String POLICY_PATH = "/{" + POLICY + "}";

    @RequestLine(V1_POST + CONF_POLICY_MAPPING_PREFIX)
    BaseResponse<MergedPolicy> create(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            PolicyDto policyDto);

    @RequestLine(V1_DELETE + CONF_POLICY_MAPPING_PREFIX + POLICY_PATH)
    BaseResponse<MergedPolicy> delete(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(POLICY) String policyName);

    @RequestLine(V1_GET + CONF_POLICY_MAPPING_PREFIX + POLICY_PATH)
    BaseResponse<MergedPolicy> find(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(POLICY) String policyName);

    @RequestLine(V1_GET + CONF_POLICY_MAPPING_PREFIX)
    BaseResponse<PageData<MergedPolicy>> search(@Param(PRODUCT_NAME) String productName,
            @Param(APP_NAME) String appName, @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_PUT + CONF_POLICY_MAPPING_PREFIX + POLICY_PATH)
    BaseResponse<MergedPolicy> update(@Param(PRODUCT_NAME) String productName, @Param(APP_NAME) String appName,
            @Param(POLICY) String policyName, PolicyDto policyDto);

}
