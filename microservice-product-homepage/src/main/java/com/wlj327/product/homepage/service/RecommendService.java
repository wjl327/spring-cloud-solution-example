package com.wlj327.product.homepage.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("microservice-recommend-server")
public interface RecommendService {

    @RequestMapping(value = "/recommend/findSimilarProductById", method = RequestMethod.GET)
    List<String> findSimilarProductById(@RequestParam("productId") String productId);

}