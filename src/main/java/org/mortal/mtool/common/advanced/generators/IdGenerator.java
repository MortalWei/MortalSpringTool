package org.mortal.mtool.common.advanced.generators;

import org.mortal.mtool.common.utils.SnowUtils;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 16:02
 * @description 唯一标识生成器
 */
//@Component
public class IdGenerator {
    private static SnowUtils snowUtil;

    //    @PostConstruct
//    public void init() {
//        snowUtil = new SnowUtils(10L, 12L);
//    }
    static {
        snowUtil = new SnowUtils(10L, 12L);
    }

    /**
     * 获取全局唯一Id
     *
     * @return
     */
    public static Long nextId() {
        if (snowUtil != null)
            return snowUtil.nextId();
        return System.currentTimeMillis();
    }
}
