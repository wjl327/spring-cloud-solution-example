package com.wjl327.product.server.controller;


import com.google.common.collect.Lists;
import com.wjl327.product.server.service.RepertoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private RepertoryService repertoryService;

    private Map<String, Map<String, Object>> repoProductMap = new ConcurrentHashMap<>();

    public ProductController() {
        initData();
    }

    //http://localhost:32001/product/findProductById?productId=0x0001
    @RequestMapping(value = "/findProductById", method = RequestMethod.GET)
    public Map<String, Object> findProductById(@RequestParam String productId) {
        Map<String, Object> product = repoProductMap.get(productId);
        if (product == null){
            buildResult(10000, String.format("Not found product(%s).", productId), product);
        }
        else {
            Map<String, Integer> stockMap = findStockMap(productId);
            product.put("stock", stockMap.get(productId));
        }
        return buildResult(0, "ok", product);
    }

    //http://localhost:32001/product/findProductListByIds?productIds=0x0001,0x0002,0x0003
    @RequestMapping(value = "/findProductListByIds", method = RequestMethod.GET)
    public Map<String, Object> findProductListByIds(@RequestParam List<String> productIds) {
        List<Map<String, Object>> products = Lists.newArrayList();
        for(String productId : productIds) {
            Map<String, Object> product = repoProductMap.get(productId);
            if (product != null){
                Map<String, Integer> stockMap = findStockMap(productId);
                product.put("stock", stockMap.get(productId));
                products.add(product);
            }
        }
        return buildResult(0, "ok", products);
    }

    private Map<String, Integer> findStockMap(String... productIds) {
        Map<String, Object> result = repertoryService.findListByProductIds(Arrays.asList(productIds));
        if ((Integer)result.get("retcode") == 0) {
            return (Map<String, Integer>)result.get("data");
        }
        return new HashMap<>();
    }

    private Map<String, Object> buildResult(Integer retcode, String retMsg, Object retData) {
        Map<String, Object> result = new HashMap<>();
        result.put("retcode", retcode);
        result.put("retmsg", retMsg);
        result.put("data", retData);
        return result;
    }

    private void initData() {
        Random rand = new Random();
        for (int idx = 1; idx < 10; idx++) {
            Map<String, Object> product = new HashMap<>();
            String id = String.format("0x000%d", idx);
            String name = String.format("科技%d号", idx);
            Integer price = rand.nextInt(20);
            product.put("id", id);
            product.put("name", name);
            product.put("price", price);
            repoProductMap.put(id, product);
        }
    }

}
