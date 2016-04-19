package com.baidu.oped.iop.m4.custom.feign;

import feign.Client;
import feign.Request;
import feign.Response;

import java.io.IOException;

/**
 * @author mason
 */
public class CustomFeignClient implements Client {

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        return null;
    }
}
