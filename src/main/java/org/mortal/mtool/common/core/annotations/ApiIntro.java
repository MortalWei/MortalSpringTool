package org.mortal.mtool.common.core.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 16:18
 * @description API说明
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@BaseIntro
public @interface ApiIntro {
    @AliasFor(annotation = BaseIntro.class)
    String value() default "";

    @AliasFor(annotation = BaseIntro.class)
    String[] tags() default {""};
}
