package com.management.platform.auth.annotion;

import java.lang.annotation.*;

/**
 * SESSION属性验证
 * 配合权限过滤使用使用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface CheckSession {
    /**
     * 是否检查Session
     * @return
     */
    boolean value() default true;

    /**
     * 检查的Session属性列表，这个先不用
     * @return
     */
    //Check[] checks() default {};
}
