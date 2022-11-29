package org.mortal.mtool.common.advanced.executer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.entity.AccessLog;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2021/11/18 17:25
 * @description
 */
@Lazy(value = false)
@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncConsumer implements Runnable {
    private Thread asyncThread;

    private boolean isRunning = true;

    public void init() {
        if (asyncThread == null) {
            asyncThread = new Thread(this, "AsyncThreadConsumer-Thread");
        }
    }

    @PostConstruct
    public void start() {
        init();
        asyncThread.start();
        log.info("======处理异步队列线程启动完成======");
    }

    @Override
    public void run() {
        Object object = null;
        while (isRunning) {
            try {
                object = AsyncAgent.take();
                if (object != null) {
                    if (object instanceof AccessLog) {
//                        MonitorThreadPool.getInstance().execute(new IFaceLogTask((AccessLog) object, loggerService));
                    }
                    log.info("线程池");
                }
                log.info("线程池");
            } catch (Exception e) {
                log.error("处理异步队列数据发生错误,ex:{},data:{}", e.getMessage(), object);
            }
        }
    }
}
