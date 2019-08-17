package com.wjl327.product.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable String name) {
        logger.info("Rpc call name :{}", name);
        return "Hello, " + name + ". My port is " + port;
    }

}
