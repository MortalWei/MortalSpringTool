package org.mortal.mtool.common.exceptions;

import lombok.Getter;
import org.mortal.mtool.common.basic.IResponseEnum;

import java.text.MessageFormat;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 14:44
 * @description 异常基类
 */
@Getter
public class BaseException extends RuntimeException {
    private final String code;
    private final String message;

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BaseException(IResponseEnum responseEnum, Object... args) {
        super(MessageFormat.format(responseEnum.getMessage(), args));
        this.code = responseEnum.getCode();
        this.message = MessageFormat.format(responseEnum.getMessage(), args);
    }

    public BaseException(IResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.getMessage(), cause);
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BaseException(IResponseEnum responseEnum, Throwable cause, Object args) {
        super(MessageFormat.format(responseEnum.getMessage(), args), cause);
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
