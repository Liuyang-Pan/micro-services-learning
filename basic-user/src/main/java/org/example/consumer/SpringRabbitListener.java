package org.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/23 13:21
 */
@Slf4j
@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void simpleQueueConsumer(String msg) {
//        log.info("simpleQueueConsumer监听到消息：{}", msg);
//    }

    /**
     * 监听simple.queue队列的消息
     * 消息消费了之后队列中就不存在消息了
     *
     * @param msg 获取到的消息
     * @throws InterruptedException 异常信息
     */
    @RabbitListener(queues = "simple.queue")
    public void workQueueConsumerOne(String msg) throws InterruptedException {
        log.info("workQueueConsumerOne监听到消息：{}", msg);
        TimeUnit.MILLISECONDS.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void workQueueConsumerTwo(String msg) throws InterruptedException {
        log.info("workQueueConsumerTwo监听到消息：{}", msg);
        TimeUnit.MILLISECONDS.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue.one")
    public void fanoutConsumerOne(String msg) throws InterruptedException {
        log.info("fanoutConsumerOne监听到消息：{}", msg);
    }

    @RabbitListener(queues = "fanout.queue.two")
    public void fanoutConsumerTwo(String msg) throws InterruptedException {
        log.info("fanoutConsumerTwo监听到消息：{}", msg);
    }

    /**
     * RabbitMQ的route队列模型，可为队列绑定一个Key，当消息包含这个key时就会到被绑定的队列去
     *
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue.one"),
            exchange = @Exchange(name = "direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void routingDirectConsumerOne(String msg) {
        log.info("routingDirectConsumerOne监听到消息：{}", msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue.two"),
            exchange = @Exchange(name = "direct", type = ExchangeTypes.DIRECT),
            key = {"red", "green"}
    ))
    public void routingDirectConsumerTwo(String msg) {
        log.info("routingDirectConsumerTwo监听到消息：{}", msg);
    }

    /**
     * RabbitMQ的Topics消息模型 可以指定key key格式为单词.单词.单词
     * 发送消息时可以使用通配符 #代表一个或多个单词；*代表一个单词
     *
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue.one"),
            exchange = @Exchange(name = "topic", type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    public void topicConsumerOne(String msg) {
        log.info("topicConsumerOne监听到消息：{}", msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue.two"),
            exchange = @Exchange(name = "topic", type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void topicConsumerTwo(String msg) {
        log.info("topicConsumerTwo监听到消息：{}", msg);
    }

    @RabbitListener(queues = "object.queue")
    public void objectSerializableConsumer(String msg) {
        log.info("objectSerializableConsumer监听到消息：{}", msg);
    }
}
