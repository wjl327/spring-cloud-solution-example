package com.wlj327.product.homepage.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wlj327.product.homepage.service.ProductService;
import com.wlj327.product.homepage.service.RecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/homepage")
public class HomepageController {

    private Logger logger = LoggerFactory.getLogger(HomepageController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private RecommendService recommendService;

    @RequestMapping(value = "/findProductDetailById", method = RequestMethod.GET)
    public Map<String, Object> findProductDetailById(@RequestParam String productId) {
        Map<String, Object> result = Maps.newHashMap();
        Map<String, Object> productResult = productService.findProductById(productId);
        if((Integer)productResult.get("retcode") == 0) {
            result.put("detail", productResult.get("data"));
            result.put("recommends", getRecommendProducts(productId));
        } else {
            logger.error("");
        }
        return buildResult(0, "ok", result);
    }

    private List<Map<String, Object>> getRecommendProducts(String productId) {
        List<String> recommendIds = recommendService.findSimilarProductById(productId);
        Map<String, Object> productResult = productService.findProductListByIds(recommendIds);
        if((Integer)productResult.get("retcode") == 0) {
            return (List<Map<String, Object>>)productResult.get("data");
        }
        return Lists.newArrayList();
    }

    private Map<String, Object> buildResult(Integer retcode, String retMsg, Object retData) {
        Map<String, Object> result = new HashMap<>();
        result.put("retcode", retcode);
        result.put("retmsg", retMsg);
        result.put("data", retData);
        return result;
    }
}
