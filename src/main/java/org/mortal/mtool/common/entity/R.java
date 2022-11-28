package org.mortal.mtool.common.entity;

import lombok.Getter;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.basic.IResponseEnum;

/**
 * @param <T> 泛型
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 14:31
 * @description 响应结果
 */
@Getter
public class R<T> {
    private String code;
    private String msg;
    private T data;

    public R(T data) {
        this.code = BREnum.SUCCESS.getCode();
        this.msg = BREnum.SUCCESS.getMessage();
        this.data = data;
    }

    public R(IResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
    }

    public R(IResponseEnum responseEnum, T data) {
        this(responseEnum);
        this.data = data;
    }
}
