package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 16798
 * @description 针对表【order】的数据库操作Mapper
 * @createDate 2022-11-18 11:34:37
 * @Entity org.example.entity.Order
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderInfo> {

}




