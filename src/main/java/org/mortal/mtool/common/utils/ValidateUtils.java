package org.mortal.mtool.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.mortal.mtool.common.entity.ValidationResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 10:12
 * @description 校验工具类
 */
public class ValidateUtils {

    private ValidateUtils() {
    }

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 校验实体，返回实体属性校验结果
     *
     * @param data 待校验的实体数据
     * @param <T>  实体类
     * @return {@link ValidationResult} 自定义校验结果
     */
    public static <T> ValidationResult validate(T data) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(data);
        return wrapperValidationResult(constraintViolations);
    }

    /**
     * 校验指定属性
     *
     * @param data         待校验的实体数据
     * @param propertyName 实体属性名称
     * @param <T>          实体类
     * @return {@link ValidationResult} 自定义校验结果
     */
    public static <T> ValidationResult validate(T data, String propertyName) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(data, propertyName, Default.class);
        return wrapperValidationResult(constraintViolations);
    }

    /**
     * 组装自定义校验结果
     *
     * @param constraintViolations 校验结果
     * @param <T>                  待校验的实体类
     * @return {@link ValidationResult} 自定义校验结果
     */
    private static <T> ValidationResult wrapperValidationResult(Set<ConstraintViolation<T>> constraintViolations) {
        ValidationResult validationResult = new ValidationResult();
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            validationResult.setHasErrors(true);
            Map<String, String> errorMsgMap = new HashMap<>();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                errorMsgMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
            validationResult.setErrorMsg(errorMsgMap);
        }
        return validationResult;
    }

}
