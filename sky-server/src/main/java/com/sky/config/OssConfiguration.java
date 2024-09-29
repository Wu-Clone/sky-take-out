package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：用于创建AliOssUtil对象
 */
// @Configuration 表示该类包含一个或多个 @Bean 方法，
// Spring 会在启动时执行这些方法，并将返回的对象注册为 Spring 容器中的Bean。
@Configuration
@Slf4j
public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean
    // 定义了一个Bean，aliOssUtil 方法的返回值 AliOssUtil 实例会被注册到 Spring IoC 容器中。
    // 这个实例在整个应用程序中可以通过自动注入（@Autowired）或直接获取。
    // 这是 Spring Boot 提供的一个条件注解，意思是只有当 Spring 容器中没有同类型的 Bean 时，才会创建该 Bean。
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("开始创建Ali云文件上传文具类对象：{}", aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint()
                , aliOssProperties.getAccessKeyId()
                , aliOssProperties.getAccessKeySecret()
                , aliOssProperties.getBucketName());
    }
}
