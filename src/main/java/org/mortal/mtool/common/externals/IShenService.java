package org.mortal.mtool.common.externals;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 16:08
 * @description 测试外部服务
 */
public interface IShenService {
    /**
     * 正常的方法
     *
     * @return
     */
    String regular();

    /**
     * 异常的方法
     *
     * @return
     */
    String anomaly();
}
