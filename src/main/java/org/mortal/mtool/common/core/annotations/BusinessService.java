package org.mortal.mtool.common.core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 15:39
 * @description 业务处理服务
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessService {
    String name() default "business";
}
