package org.mortal.mtool.common.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.exceptions.BusinessException;
import org.springframework.stereotype.Component;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 16:14
 * @description 外部服务AOP
 */
@Component
@Aspect
@Slf4j
public class ExternalAspect {
    @Pointcut(value = "@within(org.mortal.mtool.common.core.annotations.ExternalService)")
    public void externalPoint() {

    }

    @Around(value = "externalPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("ExternalAspect entry");

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
//            cause = new BaseException("999999", "未知异常", e);
//            throw cause;
            BREnum.UNKNOWN_ERROR.throwUnknown(e);
        } finally {
            if (cause != null) {
                log.info("ExternalAspect Something's wrong");
//                log.info("ExternalAspect Something's wrong", cause);
            }
            log.info("ExternalAspect Object to String ->{}", result.toString());
        }

        return result;
    }
}
