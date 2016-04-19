package com.baidu.oped.iop.m4.service.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_ACTION_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.alert.ActionDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.alert.MergedAction;
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
@FeignClient(name = "confActionClient", url = "${project.conf-man-url}")
public interface ActionService {
    String ACTION_NAME = "{actionName}";
    String ACTION_NAME_PATH = "/{" + ACTION_NAME + "}";

    @RequestLine(V1_POST + CONF_ACTION_MAPPING_PREFIX)
    BaseResponse<MergedAction> create(@Param(PRODUCT_NAME) String productName, ActionDto actionDto);

    @RequestLine(V1_DELETE + CONF_ACTION_MAPPING_PREFIX + ACTION_NAME_PATH)
    BaseResponse<MergedAction> delete(@Param(PRODUCT_NAME) String productName, @Param(ACTION_NAME) String policyName);

    @RequestLine(V1_GET + CONF_ACTION_MAPPING_PREFIX + ACTION_NAME_PATH)
    BaseResponse<MergedAction> find(@Param(PRODUCT_NAME) String productName, @Param(ACTION_NAME) String policyName);

    @RequestLine(V1_GET + CONF_ACTION_MAPPING_PREFIX)
    BaseResponse<PageData<MergedAction>> search(@Param(PRODUCT_NAME) String productName,
            @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_PUT + CONF_ACTION_MAPPING_PREFIX + ACTION_NAME_PATH)
    BaseResponse<MergedAction> update(@Param(PRODUCT_NAME) String productName, @Param(ACTION_NAME) String policyName,
            ActionDto actionDto);


}
