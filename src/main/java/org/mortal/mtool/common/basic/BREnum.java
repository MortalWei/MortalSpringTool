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
    SUCCESS("0200", "业务处理成功"),

    /*业务相关*/
    BUSINESS_UNFOUND_GUEST("0202", "未找到客人入住登记信息"),

    /*参数相关*/
    PARAM_RESOLVE_ERROR("0501", "数据解析失败"),
    PARAM_VALIDATE_ERROR("0502", "数据校验失败"),

    /*配置相关*/
    CONF_UNAUTHORIZED_ERROR("0601", "非易公安授权酒店"),
    CONF_AUTHORIZED_ERROR("0602", "未配置授权信息"),
    CONF_OTHER_ERROR("0603", "未配置其他信息"),
    CONF_DEVICE_ERROR("0604", "未找到设备信息"),
    CONF_EXPIRE_ERROR("0609", "厂商授权已过期"),

    /*未知情况*/
    UNKNOWN_ERROR("9999", "未知异常"),

    /*特殊状态*/
    FIXED_EPIDEMIC_ERROR("-27", "疫情信息不存在，请先上传信息");

    private final String code;
    private final String message;
}
