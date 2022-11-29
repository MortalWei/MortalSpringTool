package org.mortal.mtool.common.services;

import org.mortal.mtool.common.entity.AccessLog;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 18:32
 * @description 日志处理
 */
public interface IAccessLogService {
    Integer newlyModel(AccessLog data);
}
