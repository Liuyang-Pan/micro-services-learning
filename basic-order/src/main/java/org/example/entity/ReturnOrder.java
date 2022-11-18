package org.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/18 12:41
 */
@Data
public class ReturnOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 用户ID
     */
    private Integer userId;

    private User user;
}
