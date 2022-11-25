package org.mortal.mtool.common;

//import org.mortal.mtool.common.exceptions.BaseException;

import org.mortal.mtool.common.exceptions.BaseException;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/24 11:00
 * @description 断言验证
 */
public interface Assert {
    BaseException newException();

    BaseException newException(Object... args);

    BaseException newException(Throwable cause, Object... args);

    /**
     * 断言对象是否为null
     *
     * @param obj 待判断的对象
     */
    default void assertNotNull(Object obj) {
        if (obj == null) {
            throw newException();
        }
    }

    /**
     * 断言对象是否为null
     *
     * @param obj  待判断的对象
     * @param args message占位符参数列表
     */
    default void assertNotNull(Object obj, Object... args) throws BaseException {
        if (obj == null) {
            throw newException(args);
        }
    }
}
