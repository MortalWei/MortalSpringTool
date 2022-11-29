package org.mortal.mtool.common.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.exceptions.BaseException;
import org.mortal.mtool.common.exceptions.BusinessException;
import org.springframework.stereotype.Component;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 15:35
 * @description 业务处理AOP
 */
@Component
@Aspect
@Slf4j
public class BusinessAspect {
    @Pointcut(value = "@within(org.mortal.mtool.common.core.annotations.BusinessService)")
    public void basicPoint() {

    }

    @Around(value = "basicPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("BusinessAspect entry");


        Object result = null;
        Throwable cause = null;
        try {
            result = point.proceed();
        } catch (BusinessException e) {
            result = StringUtils.EMPTY;
            cause = e;
            throw e;
        } catch (Exception e) {
            result = StringUtils.EMPTY;
            cause = new BaseException(BREnum.UNKNOWN_ERROR, e);
            throw cause;
        } finally {
            if (cause != null) {
                log.info("BusinessAspect Something's wrong");
//                log.info("ExternalAspect Something's wrong", cause);
            }
            log.info("BusinessAspect Object to String ->{}", result.toString());
        }

        return result;
    }
}
