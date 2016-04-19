package com.baidu.oped.iop.m4.mvc.rest.conf;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.CONF_CALLBACK_MAPPING_PREFIX;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.PRODUCT_NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.common.ReceiverCallbackDto;
import com.baidu.oped.iop.m4.mvc.dto.conf.common.MergedReceiverCallback;
import com.baidu.oped.iop.m4.service.conf.CallbackService;
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
@RequestMapping(API_V1 + CONF_CALLBACK_MAPPING_PREFIX)
public class CallbackController {
    private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

    private static final String CALLBACK = "callbackName";
    private static final String CALLBACK_PATH = "{" + CALLBACK + "}";

    @Autowired
    private CallbackService callbackService;

    @RequestMapping(method = GET)
    public PageData<MergedReceiverCallback> search(@PathVariable(PRODUCT_NAME) String productName,
            @RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search Callbacks with productName: {}", productName);

        return callbackService.search(productName, queryMap)
                .getData();
    }

    @RequestMapping(value = CALLBACK_PATH, method = GET)
    public MergedReceiverCallback find(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(CALLBACK) String callbackName) {
        LOG.debug("Find Callback with productName: {} and CallbackName: {}", productName, callbackName);

        return callbackService.find(productName, callbackName)
                .getData();
    }

    @RequestMapping(method = POST)
    public MergedReceiverCallback create(@PathVariable(PRODUCT_NAME) String productName,
            @RequestBody ReceiverCallbackDto dto) {
        LOG.debug("Create Callback for productName: {} with CallbackDto: {}", productName, dto);

        return callbackService.create(productName, dto)
                .getData();
    }

    @RequestMapping(value = CALLBACK_PATH, method = PUT)
    public MergedReceiverCallback update(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(CALLBACK) String callbackName, @RequestBody ReceiverCallbackDto dto) {
        LOG.debug("Update Callback {}.{} with CallbackDto: {}", productName, callbackName, dto);

        return callbackService.update(productName, callbackName, dto)
                .getData();
    }

    @RequestMapping(value = CALLBACK_PATH, method = DELETE)
    public MergedReceiverCallback delete(@PathVariable(PRODUCT_NAME) String productName,
            @PathVariable(CALLBACK) String callbackName) {
        LOG.debug("Delete Callback {}.{} ", productName, callbackName);

        return callbackService.delete(productName, callbackName)
                .getData();
    }

}
