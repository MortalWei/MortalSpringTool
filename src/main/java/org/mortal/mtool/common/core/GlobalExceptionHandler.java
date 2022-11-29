package org.mortal.mtool.common.core;

import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.entity.ER;
import org.mortal.mtool.common.exceptions.BaseException;
import org.mortal.mtool.common.exceptions.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ER businessExceptionHandler(BusinessException exception) {
        log.error("businessExceptionHandler.class->{}", exception.getCaption(), exception);
        return new ER(exception.getResponseEnum(), exception.getMessage());
    }

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ER baseExceptionHandler(BaseException exception) {
        log.error("BaseException.class->{}", exception.getCaption(), exception);
        return new ER(exception.getResponseEnum(), exception.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public ER bindExceptionHandler(BindException exception) {
        log.error("BindException.class->{}", exception.getMessage(), exception);
        return wrapperBindingResult(exception.getBindingResult());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ER methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException.class->{}", exception.getMessage(), exception);
        return wrapperBindingResult(exception.getBindingResult());
    }

//    @ExceptionHandler(value = ValidationException.class)
//    public ER validationExceptionHandler(ValidationException exception) {
//        log.error("ValidationException.class->{}", exception.getMessage(), exception);
//        //BREnum.UNKNOWN_ERROR.throwUnknown(e);
//        return new ER(new BaseException(BREnum.UNKNOWN_ERROR, exception.getMessage()));
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
        return new ER(BREnum.UNKNOWN_ERROR, exception.getMessage());
    }

    /**
     * 组装参数校验异常
     *
     * @param bindingResult 绑定结果
     * @return
     */
    private ER wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
//            if (error instanceof FieldError) {
//                msg.append(((FieldError) error).getField()).append(": ");
//            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }

        return new ER(BREnum.PARAM_VALIDATE_ERROR, msg.substring(2));
    }
}
