package com.baidu.oped.iop.m4.mvc.controller;

import com.baidu.oped.iop.m4.configuration.properties.AppInfo;
import com.baidu.oped.iop.m4.configuration.properties.AppProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Application Info Controller.
 *
 * @author mason
 */
@Controller
public class SimpleController {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private AppProperties properties;

    @RequestMapping(value = {"application/info"}, method = RequestMethod.GET)
    @ResponseBody
    public AppInfo getApplicationInfo() {
        LOG.debug("invoking to get application info");
        return properties.getInfo();
    }

    @RequestMapping(value = "login/bdpassport")
    public String login(@RequestParam(value = "x-redirect-url",required = false) String url) {
        if (StringUtils.hasText(url)) {
            return "redirect:" + url;
        }
        if (StringUtils.hasText(properties.getHomePage())) {
            return "redirect:" + properties.getHomePage();
        }
        return "redirect:/";
    }

}
