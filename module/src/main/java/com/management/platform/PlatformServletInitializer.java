package com.management.platform;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Guns Web程序启动类
 *
 * @author fengshuonan
 * @date 2017-05-21 9:43
 */
public class PlatformServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PlatformApplication.class);
    }
}
