package org.example;

import org.feign.clients.UserClient;
import org.feign.config.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * clients basePackages basePackageClasses可以指定当Feign包名不一致时引用指定的包下面的Client加载到Spring容器中
 */
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class, basePackages = "org.feign.clients")
@SpringBootApplication
public class BasicOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicOrderApplication.class, args);
    }

    /**
     * 创建RestTemplate对象并注入Spring容器中
     * LoadBalanced 开启负载均衡 通过Ribbon组件实现负载均衡 默认采用ZoneAvoidanceRule（区域服务器轮询方式）
     *
     * @return RestTemplate对象
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 采用RandomRule随机规则进行负载均衡，还可以通过配置文件配置，此配置会使此服务集群都采用此规则
     * 配置文件配置的方式是单个服务方式
     *
     * @return RandomRule对象
     */
//    @Bean
//    public IRule rule() {
//        return new RandomRule();
//    }
}