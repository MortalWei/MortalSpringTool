package org.mortal.mtool.common.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 11:01
 * @description 自定义校验结果集
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ValidationResult {
    private boolean hasErrors;
    private Map<String, String> errorMsg;

    public String getMessage() {
        if (errorMsg == null || errorMsg.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuilder builder = new StringBuilder();
//        errorMsg.forEach((key, value) -> builder.append(MessageFormat.format("{0}:{1}", key, value) + System.lineSeparator()));
        errorMsg.forEach((key, value) -> {
            builder.append(", ");
            builder.append(value);
        });
        return builder.substring(2);
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "hasErrors" + hasErrors
                + ", errorMsg=" + errorMsg
                + "}";
    }
}
