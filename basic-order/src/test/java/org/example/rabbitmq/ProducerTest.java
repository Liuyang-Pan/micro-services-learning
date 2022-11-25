package org.example.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * purpose:生产者测试
 *
 * @author Pan Liuyang
 * 2022/11/23 16:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 一个生产者一个消费者
     */
    @Test
    public void simpleQueueProducer() {
        //简单队列生产消息
        rabbitTemplate.convertAndSend("simple.queue", "test");
    }

    /**
     * 一个生产者多个消费者
     */
    @Test
    public void workQueueProducer() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            //简单队列生产消息
            rabbitTemplate.convertAndSend("simple.queue", "第" + i + "条消息");
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    /**
     * 采用消息发布者与订阅者模式,可一次生产多个消费者消费
     * 增加了交换机，向交换机发送消息，推送到多个队列中，路由只做转发到多个队列，若找不到会丢失
     */
    @Test
    public void fanoutProducerOne() {
        //交换机名称
        String exchangeName = "fanout";
        //发送消息内容
        String message = "Hello " + exchangeName + " exchange";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    /**
     * 采用route绑定的方式发送
     */
    @Test
    public void directProducerOne() {
        //交换机名称
        String exchangeName = "direct";
        //发送消息内容
        String message = "Hello " + exchangeName + " exchange";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    /**
     * 采用topic绑定的方式发送
     */
    @Test
    public void topicProducerOne() {
        //交换机名称
        String exchangeName = "topic";
        //发送消息内容
        String message = "Hello " + exchangeName + " exchange！";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message + "This is china weather.");
    }

    /**
     * Spring Amqp默认采用的Java的序列化，内容存在问题，指定MessageConverter为Jackson2JsonMessageConverter就能发送JSON数据了
     */
    @Test
    public void objectSerializableQueueProducer() {
        Map<String, String> map = new HashMap<>();
        map.put("queueName", "object.queue");
        map.put("exchangeName", "Default");
        map.put("队列中文名", "对象队列");
        map.put("交换机中文名", "默认");
        //简单队列生产消息
        rabbitTemplate.convertAndSend("object.queue", map);
    }
}
