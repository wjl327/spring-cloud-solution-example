package com.wlj327.product.homepage.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("microservice-product-server")
public interface ProductService {

    @RequestMapping(value = "/product/findProductById", method = RequestMethod.GET)
    Map<String, Object> findProductById(@RequestParam("productId") String productId);

    @RequestMapping(value = "/product/findProductListByIds", method = RequestMethod.GET)
    Map<String, Object> findProductListByIds(@RequestParam("productIds") List<String> productIds);

}
