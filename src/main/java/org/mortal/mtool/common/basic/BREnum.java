package org.mortal.mtool.common.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 15:59
 * @description 业务响应异常枚举类
 */
@Getter
@AllArgsConstructor
public enum BREnum implements BusinessExceptionAssert {
    SUCCESS("0200", "业务处理成功"), RC_ERROR("0201", "处理失败了"), UNAUTHORIZED("6001", "未找到授权信息");

    private final String code;
    private final String message;
}
