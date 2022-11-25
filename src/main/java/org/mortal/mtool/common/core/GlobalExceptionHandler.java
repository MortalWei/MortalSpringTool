package org.mortal.mtool.common.core;

import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.entity.ER;
import org.mortal.mtool.common.exceptions.BaseException;
import org.mortal.mtool.common.exceptions.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 17:17
 * @description 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ER businessExceptionHandler(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return new ER(exception);
    }

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ER baseExceptionHandler(BaseException exception) {
        log.error(exception.getMessage(), exception);
        return new ER(exception);
    }
}
