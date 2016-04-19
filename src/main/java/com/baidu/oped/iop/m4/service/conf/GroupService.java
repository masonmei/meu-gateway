package com.baidu.oped.iop.m4.service.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_GROUP_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverGroupDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.common.MergedReceiverGroup;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author mason
 */
@FeignClient(name = "confGroupClient", url = "${project.conf-man-url}")
@RequestMapping(CONF_GROUP_MAPPING_PREFIX)
public interface GroupService {

    String GROUP = "group";
    String GROUP_PATH = "/{" + GROUP + "}";

    @RequestLine(V1_POST + CONF_GROUP_MAPPING_PREFIX)
    BaseResponse<MergedReceiverGroup> create(@Param(PRODUCT_NAME) String productName, ReceiverGroupDto groupDto);

    @RequestLine(V1_DELETE + CONF_GROUP_MAPPING_PREFIX + GROUP_PATH)
    BaseResponse<MergedReceiverGroup> delete(@Param(PRODUCT_NAME) String productName, @Param(GROUP) String callback);

    @RequestLine(V1_GET + CONF_GROUP_MAPPING_PREFIX + GROUP_PATH)
    BaseResponse<MergedReceiverGroup> find(@Param(PRODUCT_NAME) String productName, @Param(GROUP) String callback);

    @RequestLine(V1_GET + CONF_GROUP_MAPPING_PREFIX)
    BaseResponse<PageData<MergedReceiverGroup>> search(@Param(PRODUCT_NAME) String productName,
            @QueryMap Map<String, List<String>> queryMap);

    @RequestLine(V1_PUT + CONF_GROUP_MAPPING_PREFIX + GROUP_PATH)
    BaseResponse<MergedReceiverGroup> update(@Param(PRODUCT_NAME) String productName, @Param(GROUP) String callback,
            ReceiverGroupDto groupDto);

}
