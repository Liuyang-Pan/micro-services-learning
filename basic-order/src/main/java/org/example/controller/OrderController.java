package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.clients.UserClient;
import org.example.config.PatternProperties;
import org.example.entity.OrderInfo;
import org.example.entity.ReturnOrder;
import org.example.entity.User;
import org.example.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/18 11:22
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@RefreshScope //Nacos配置热更新 @Value在哪里就添加到哪里才可以实现热更新
public class OrderController {

    @Value("${pattern.dateformat}")
    private String dateFormat;

    private final OrderService orderService;

    private final RestTemplate restTemplate;

    private final PatternProperties patternProperties;

    @Autowired
    private UserClient userClient;

    @GetMapping("/getOrder/{id}")
    public ReturnOrder getOrder(@PathVariable String id) {
        OrderInfo orderInfo = orderService.getBaseMapper().selectById(id);
        //采用RestTemplate调用远程服务
//        String url = "http://basic-user/user/getUser/" + orderInfo.getUserId();
//        User forObject = restTemplate.getForObject(url, User.class);
        //采用OpenFeign调用远程服务
        User forObject = userClient.findById(String.valueOf(orderInfo.getUserId()));
        ReturnOrder target = new ReturnOrder();
        target.setUser(forObject);
        BeanUtils.copyProperties(orderInfo, target);
        return target;
    }

    @GetMapping("/now")
    public String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }
}
