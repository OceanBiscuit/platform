package com.management.platform;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringBoot方式启动类
 *
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication(exclude = {WebAutoConfiguration.class})
@ComponentScan(value = "com.management.platform.*")
@MapperScan(value = "com.management.platform.modular.*.mapper")
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class PlatformApplication {

    private final static Logger logger = LoggerFactory.getLogger(PlatformApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
        logger.info(PlatformApplication.class.getSimpleName() + " is success!");
    }

}
