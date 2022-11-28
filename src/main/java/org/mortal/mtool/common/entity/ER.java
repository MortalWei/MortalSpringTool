package org.mortal.mtool.common.entity;

import lombok.Getter;
import org.mortal.mtool.common.basic.IResponseEnum;
import org.mortal.mtool.common.exceptions.BaseException;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 14:34
 * @description 异常响应结果
 */
@Getter
public class ER {
    private String code;
    private String msg;

    public ER(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ER(BaseException exception) {
        this.code = exception.getCode();
        this.msg = exception.getMessage();
    }

    public ER(IResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
    }
}
