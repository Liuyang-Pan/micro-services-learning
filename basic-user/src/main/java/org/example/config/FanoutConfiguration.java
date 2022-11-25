package org.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * purpose:RabbitMQ绑定消息列队交换机
 *
 * @author Pan Liuyang
 * 2022/11/23 17:33
 */
@Configuration
public class FanoutConfiguration {

    /**
     * 交换机fanout
     *
     * @return 配置到Spring容器
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout");
    }

    /**
     * 队列fanout.queue.one
     *
     * @return 配置到Spring容器
     */
    @Bean
    public Queue fanoutQueueOne() {
        return new Queue("fanout.queue.one");
    }

    /**
     * 队列fanout.queue.two
     *
     * @return 配置到Spring容器
     */
    @Bean
    public Queue fanoutQueueTwo() {
        return new Queue("fanout.queue.two");
    }

    /**
     * 将fanoutQueueOne绑定到fanoutExchange交换机上
     *
     * @param fanoutQueueOne 队列fanout.queue.one
     * @param fanoutExchange 交换机fanout
     * @return 配置到Spring容器
     */
    @Bean
    public Binding fanoutBindingOne(Queue fanoutQueueOne, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueOne).to(fanoutExchange);
    }

    /**
     * 将fanoutQueueTwo绑定到fanoutExchange交换机上
     *
     * @param fanoutQueueTwo 队列fanout.queue.one
     * @param fanoutExchange 交换机fanout
     * @return 配置到Spring容器
     */
    @Bean
    public Binding fanoutBindingTwo(Queue fanoutQueueTwo, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueueTwo).to(fanoutExchange);
    }
}
