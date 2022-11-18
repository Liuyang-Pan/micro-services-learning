package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.OrderInfo;
import org.example.service.OrderService;
import org.example.mapper.OrderMapper;
import org.springframework.stereotype.Component;

/**
* @author 16798
* @description 针对表【order】的数据库操作Service实现
* @createDate 2022-11-18 11:34:37
*/
@Component
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo>
    implements OrderService {

}




