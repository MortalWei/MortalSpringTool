package org.mortal.mtool.common.exceptions;

import org.mortal.mtool.common.basic.IResponseEnum;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 14:52
 * @description 业务处理异常
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BusinessException(IResponseEnum responseEnum) {
        super(responseEnum);
    }

    public BusinessException(IResponseEnum responseEnum, Object... args) {
        super(responseEnum, args);
    }

    public BusinessException(IResponseEnum responseEnum, Throwable cause) {
        super(responseEnum, cause);
    }

    public BusinessException(IResponseEnum responseEnum, Throwable cause, Object args) {
        super(responseEnum, cause, args);
    }
}
