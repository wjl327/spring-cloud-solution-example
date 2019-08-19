package com.wjl327.recommend.server.controller;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/recommend")
public class RecommendController {


    private Logger logger = LoggerFactory.getLogger(RecommendController.class);

    private Map<String, Map<String, Object>> products = new ConcurrentHashMap<>();

    //http://localhost:32002/recommend/findSimilarProductById?productId=0x0001
    @RequestMapping(value = "/findSimilarProductById", method = RequestMethod.GET)
    public List<String> findSimilarProductById(@RequestParam String productId) {
        List<String> recommendIds = Lists.newArrayList();
        int originalId = Integer.parseInt(productId.substring(3));
        for (int i = 1; i <= 3; i++) {
            recommendIds.add(String.format("0x000%d", originalId + i));
        }
        return recommendIds;
    }

}
