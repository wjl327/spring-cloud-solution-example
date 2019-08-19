package com.wjl327.product.server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("microservice-repertory-server")
public interface RepertoryService {

    @RequestMapping(value = "/repertory/findListByProductIds", method = RequestMethod.GET)
    Map<String, Object> findListByProductIds(@RequestParam("productIds") List<String> productIds);

}
