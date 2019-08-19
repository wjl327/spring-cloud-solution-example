package com.wjl327.repertory.server.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/repertory")
public class RepertoryController {

    private final Map<String, Integer> productStock = new ConcurrentHashMap<>();

    public RepertoryController() {
        initData();
    }

    private void initData() {
        Random rand = new Random();
        for (int idx = 1; idx < 10; idx++) {
            productStock.put(String.format("0x000%d", idx), rand.nextInt(10000));
        }
    }

    //http://localhost:32003/repertory/findListByProductIds?productIds=0x0001,0x0002,0x0003
    @RequestMapping(value = "/findListByProductIds", method = RequestMethod.GET)
    public Map<String, Object> findListByProductIds(@RequestParam List<String> productIds) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> resultData = new HashMap<>();
        for(String id : productIds) {
            resultData.put(id, productStock.get(id));
        }
        result.put("retcode", 0);
        result.put("retmsg", "ok");
        result.put("data", resultData);
        return result;
    }

}
