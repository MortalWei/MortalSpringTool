package org.mortal.mtool.common.advanced.executer;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 15:39
 * @description 异步代理
 */
@Component
public class AsyncAgent {
    private static AsyncQueue asyncQueue = new AsyncQueue();

    @PostConstruct
    public static void start() {
        asyncQueue.startQueue();
    }

    public static void post(Object object) {
        if (object != null) {
            asyncQueue.post(object);
        }
    }

    public static Object take() {
        return asyncQueue.take();
    }

}
