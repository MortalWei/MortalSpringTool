package org.mortal.mtool.common.basic;

import org.mortal.mtool.common.Assert;
import org.mortal.mtool.common.exceptions.BaseException;
import org.mortal.mtool.common.exceptions.BusinessException;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/25 14:53
 * @description 业务异常断言
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {
    @Override
    default BaseException newException() {
        return new BusinessException(this);
    }

    @Override
    default BaseException newException(Object... args) {
        return new BusinessException(this, args);
    }

    @Override
    default BaseException newException(Throwable cause, Object... args) {
        return new BusinessException(this, cause, args);
    }
}
