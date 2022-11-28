package org.mortal.mtool.common.core;

import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.entity.ER;
import org.mortal.mtool.common.exceptions.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 17:17
 * @description 统一异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
//    /**
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(value = BusinessException.class)
//    @ResponseBody
//    public ER businessExceptionHandler(BusinessException exception) {
////        Marker marker = MarkerFactory.getMarker("BusinessException");
////        log.error(marker, exception.getMessage(), exception);
//        log.error("businessExceptionHandler.class->{}", exception.getMessage(), exception);
//        return new ER(exception);
//    }
//
//    /**
//     * @param exception
//     * @return
//     */
//    @ExceptionHandler(value = BaseException.class)
//    @ResponseBody
//    public ER baseExceptionHandler(BaseException exception) {
//        log.error("BaseException.class->{}", exception.getMessage(), exception);
//        return new ER(exception);
//    }

    /**
     * 未知异常
     *
     * @param exception 异常信息
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ER exceptionHandler(Exception exception) {
        log.error("Exception.class->{}", exception.getMessage(), exception);
        return new ER(new BaseException("9999", "未知异常(统一处理)", exception));
    }
}
