package org.mortal.mtool.common.exceptions;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
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
    private final String caption;
    private final String message;

    private final IResponseEnum responseEnum;

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
        this.caption = responseEnum.getMessage();
        this.message = StringUtils.EMPTY;
        this.responseEnum = responseEnum;
    }

    public BaseException(IResponseEnum responseEnum, Object... args) {
        super(StringUtils.join(args));
        this.code = responseEnum.getCode();
        this.caption = responseEnum.getMessage();
        this.message = StringUtils.join(args);
        this.responseEnum = responseEnum;
    }

    public BaseException(IResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.getMessage(), cause);
        this.code = responseEnum.getCode();
        this.caption = responseEnum.getMessage();
        this.message = StringUtils.EMPTY;
        this.responseEnum = responseEnum;
    }

    public BaseException(IResponseEnum responseEnum, Throwable cause, Object args) {
        super(MessageFormat.format(responseEnum.getMessage(), args), cause);
        this.code = responseEnum.getCode();
        this.caption = responseEnum.getMessage();
        this.message = StringUtils.EMPTY;
        this.responseEnum = responseEnum;
    }
}
