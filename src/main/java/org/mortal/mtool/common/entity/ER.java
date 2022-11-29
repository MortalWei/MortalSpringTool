package org.mortal.mtool.common.entity;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.mortal.mtool.common.basic.IResponseEnum;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 14:34
 * @description 异常响应结果
 */
@Getter
public class ER {
    private final String code;
    private final String msg;
    private final String errorMsg;

    public ER(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.errorMsg = StringUtils.EMPTY;
    }

    public ER(IResponseEnum responseEnum, String errorMsg) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
        this.errorMsg = errorMsg;
    }
}
