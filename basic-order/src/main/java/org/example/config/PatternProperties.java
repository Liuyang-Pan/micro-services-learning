package org.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/18 22:57
 */
@Data
@Component //注册为Spring的Bean
@ConfigurationProperties(prefix = "pattern") //此注解前缀+变量名与配置文件中结合起来一样就可以把配置文件的内容加载进来,Nacos也可以热更新此配置
public class PatternProperties {
    private String dateformat;
}
