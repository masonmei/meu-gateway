package com.baidu.oped.iop.m4.service.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_CALLBACK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverCallbackDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.common.MergedReceiverCallback;
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
@FeignClient(name = "confCallbackClient", url = "${project.conf-man-url}")
public interface CallbackService {

    String CALLBACK = "callback";
    String CALLBACK_PATH = "/{" + CALLBACK + "}";

    @RequestLine(V1_POST + CONF_CALLBACK_MAPPING_PREFIX)
    BaseResponse<MergedReceiverCallback> create(@Param(PRODUCT_NAME) String productName,
            ReceiverCallbackDto callbackDto);

    @RequestLine(V1_DELETE + CONF_CALLBACK_MAPPING_PREFIX + CALLBACK_PATH)
    BaseResponse<MergedReceiverCallback> delete(@Param(PRODUCT_NAME) String productName,
            @Param(CALLBACK) String callback);

    @RequestLine(V1_GET + CONF_CALLBACK_MAPPING_PREFIX + CALLBACK_PATH)
    BaseResponse<MergedReceiverCallback> find(@Param(PRODUCT_NAME) String productName,
            @Param(CALLBACK) String callback);

    @RequestLine(V1_GET + CONF_CALLBACK_MAPPING_PREFIX)
    BaseResponse<PageData<MergedReceiverCallback>> search(@Param(PRODUCT_NAME) String productName,
            @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_PUT + CONF_CALLBACK_MAPPING_PREFIX + CALLBACK_PATH)
    BaseResponse<MergedReceiverCallback> update(@Param(PRODUCT_NAME) String productName,
            @Param(CALLBACK) String callback, ReceiverCallbackDto callbackDto);

}
