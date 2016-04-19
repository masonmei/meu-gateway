package com.baidu.oped.iop.m4.mvc.rest.conf;


import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.APP_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_GROUP_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverGroupDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.common.MergedReceiverGroup;
import com.baidu.oped.iop.m4.service.conf.GroupService;
import com.baidu.oped.iop.m4.utils.PageData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(API_V1 + CONF_GROUP_MAPPING_PREFIX)
public class GroupController {
    private static final Logger LOG = LoggerFactory.getLogger(GroupController.class);

    private static final String GROUP = "groupName";
    private static final String GROUP_PATH = "{" + GROUP + "}";

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = GET)
    public PageData<MergedReceiverGroup> search(@PathVariable(PRODUCT_NAME) String productName,
            @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search Groups with productName: {}", productName);

        return groupService.search(productName, queryMap).getData();
    }

    @RequestMapping(value = GROUP_PATH, method = GET)
    public MergedReceiverGroup find(@PathVariable(PRODUCT_NAME) String productName, @PathVariable(GROUP) String groupName) {
        LOG.debug("Find Group with productName: {}, and GroupName: {}", productName, groupName);

        return groupService.find(productName, groupName).getData();
    }

    @RequestMapping(method = POST)
    public MergedReceiverGroup create(@PathVariable(PRODUCT_NAME) String productName, @RequestBody ReceiverGroupDto dto) {
        LOG.debug("Create Group for productName: {} with GroupDto: {}", productName, dto);

        return groupService.create(productName, dto).getData();
    }

    @RequestMapping(value = GROUP_PATH, method = PUT)
    public MergedReceiverGroup update(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(GROUP) String taskName, @RequestBody ReceiverGroupDto dto) {
        LOG.debug("Update Group {}.{} with GroupDto: {}", productName, taskName, dto);

        return groupService.update(productName, taskName, dto).getData();
    }

    @RequestMapping(value = GROUP_PATH, method = DELETE)
    public MergedReceiverGroup delete(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(GROUP) String groupName) {
        LOG.debug("Delete Group {}.{} ", productName, groupName);

        return groupService.delete(productName, groupName).getData();
    }

}
