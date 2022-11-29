package org.mortal.mtool.common.advanced.executer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/29 15:40
 * @description 异步队列
 */
public class AsyncQueue {
    private BlockingQueue<Object> asyncQueue;

    public void post(Object object) {
//        try {
        asyncQueue.add(object);
//        } catch (Exception e) {
//            throw e;
//        }
    }

    public Object take() {
        try {
            return asyncQueue.take();
        } catch (InterruptedException ignored) {

        }
        return null;
    }

    public void startQueue() {
        asyncQueue = new ArrayBlockingQueue<>(200);
    }
}
