package org.mortal.mtool.common.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.mortal.mtool.common.core.Constants;
import org.mortal.mtool.common.utils.CommonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 15:57
 * @description 测试日志实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessLog implements Serializable {

    /**
     * 主键，唯一标识
     */
    private Long uid;

    /**
     * 服务ID，客户端生成
     */
    private Long serviceId;

    /**
     * 请求ID，后端生成
     */
    private Long requestId;

    /**
     * 是否主入口，0.否 1.是
     */
    private Byte isMain;

    /**
     * 路径
     */
    private String path;

    /**
     * 请求类型
     */
    private String methodType;

    /**
     * 接口方法名称
     */
    private String method;

    /**
     * 接口方法说明
     */
    private String methodIntro;

    /**
     * http状态码
     */
    private Integer httpStatus;

    /**
     * 响应码
     */
    private String resCode;

    /**
     * 响应码说明
     */
    private String resCodeIntro;

    /**
     * 请求开始时间
     */
    private Date startTime;

    /**
     * 请求结束时间
     */
    private Date endTime;

    /**
     * 耗时，单位毫秒ms
     */
    private Integer timeConsuming;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 响应数据
     */
    private String resData;

    /**
     * 异常信息
     */
    private String exception;

    public static AccessLog ok(String requestString, String responseString, long startTime, long endTime) {
        AccessLog log = getDefault(requestString, responseString, startTime, endTime);

        log.setIsMain((byte) 1);
        log.setMethod(CommonUtils.getMDC(Constants.ACCESS_METHOD, "default"));
        log.setMethodIntro(CommonUtils.getMDC(Constants.ACCESS_METHOD_INTRO, "default"));
        log.setResCode(CommonUtils.getMDC(Constants.MDC_STATUS_CODE));

        if (StringUtils.isNotEmpty(responseString)) {
            JSONObject object = JSON.parseObject(responseString);
            String status = object.getString("code");
            String msg = object.getString("msg");
            log.setResCode(status);
            log.setResCodeIntro(msg);
            log.setException(CommonUtils.getMDC(Constants.MDC_EXCEPTION_MSG, StringUtils.EMPTY));
        }
        return log;
    }

    private static AccessLog getDefault(String requestString, String responseString, long startTime, long endTime) {
        return AccessLog.builder()
                .requestId(Long.parseLong(CommonUtils.getMDC(Constants.MDC_REQUEST_ID, "0")))
                .startTime(CommonUtils.getDate(startTime))
                .endTime(CommonUtils.getDate(endTime))
                .timeConsuming((int) (endTime - startTime))
                .param(requestString)
                .resData(responseString)
                .build();
    }
}
