package org.mortal.mtool.common.services;

import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.entity.AccessLog;
import org.springframework.stereotype.Service;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 18:33
 * @description 日志处理
 */
@Service
@Slf4j
public class AccessLogServiceImpl implements IAccessLogService {
    @Override
    public Integer newlyModel(AccessLog data) {
        return null;
    }
}
