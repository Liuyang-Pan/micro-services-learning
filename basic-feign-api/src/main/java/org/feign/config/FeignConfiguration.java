package org.feign.config;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;

/**
 * purpose:此配置可加放在某一个Feign接口上或者放在启动配置上
 * 调用某服务日志示例: @FeignClient(value = "basic-user", configuration = FeignConfiguration.class)
 * 全局日志示例: @EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
 *
 * @author Pan Liuyang
 * 2022/11/21 11:53
 */
public class FeignConfiguration {

    @Bean
    public Level logLevel() {
        return Level.BASIC;
    }
}
