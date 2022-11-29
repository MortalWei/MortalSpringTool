package org.mortal.mtool.common.advanced.executer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2021/11/18 17:00
 * @description 线程池
 */
//@Configuration
@Slf4j
public class MonitorThreadPool {
    /*
     * corePoolSize ： 线程池维护线程的最少数量
     *
     * maximumPoolSize ：线程池维护线程的最大数量
     *
     * keepAliveTime ： 线程池维护线程所允许的空闲时间
     *
     * unit ： 线程池维护线程所允许的空闲时间的单位
     *
     * workQueue ： 线程池所使用的缓冲队列
     */
    private static int corePoolSize = 20;// 线程池维护线程的最少数量
    private static int maximumPoolSize = 40;// 线程池维护线程的最大数量
    private static int keepAliveTime = 30;// 线程池维护线程所允许的空闲时间,多出corePoolSize之外的线程的允许发呆时间
    private static int queueSize = 100; // 队列大小
    private static ThreadPoolExecutor threadPool;

    /**
     * 从配置文件读取配置
     **/
    private final static String core_pool_size = "10";// CommonUtil.getSysconfigString("core_pool_size", "10");

    /**
     * 从配置文件读取配置
     **/
    private final static String keep_alive_time = "0";// CommonUtil.getSysconfigString("keep_alive_time", "0");

    /**
     * 从配置文件读取配置
     **/
    private final static String maximum_pool_size = "50";// ""CommonUtil.getSysconfigString("maximum_pool_size", "50");

    /**
     * 从配置文件读取配置
     **/
    private final static String queue_size = "2";// CommonUtil.getSysconfigString("queue_size", "2");

    // 先进先出阻塞队列
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueSize);

    public static MonitorThreadPool getInstance() {
        return MonitorPool.POOL.getThreadPool();
    }

    protected enum MonitorPool {
        POOL;

        protected MonitorThreadPool threadPool;

        protected BlockingQueue<Runnable> queue;

        MonitorPool() {
            this.queue = new ArrayBlockingQueue<>(queueSize);
            this.threadPool = new MonitorThreadPool(queue);
            logPool();
        }

        protected MonitorThreadPool getThreadPool() {
            return this.threadPool;
        }
    }

    public MonitorThreadPool(BlockingQueue<Runnable> queue) {
        // 设置线程池为配置值
        corePoolSize = StringUtils.isBlank(core_pool_size) ? corePoolSize : Integer.parseInt(core_pool_size);
        maximumPoolSize = StringUtils.isBlank(maximum_pool_size) ? maximumPoolSize
                : Integer.parseInt(maximum_pool_size);
        keepAliveTime = StringUtils.isBlank(keep_alive_time) ? keepAliveTime : Integer.parseInt(keep_alive_time);
        queueSize = StringUtils.isBlank(queue_size) ? maximumPoolSize : Integer.parseInt(queue_size);
        // 构造一个线程池
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue,
                new NamedThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
//        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue,
//                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void shutdown() {
        threadPool.shutdownNow();
    }

    public void execute(Runnable a) {
        threadPool.execute(a);
    }

    public static void logPool() {
        log.info("线程池 queue size：{}, core pool size: {}, max pool size: {}, pool size: {}, keepAlive: {}, 活动线程数: {}, task数量: {}",
                threadPool.getQueue().size(),
                threadPool.getCorePoolSize(),
                threadPool.getMaximumPoolSize(),
                threadPool.getPoolSize(),
                threadPool.getKeepAliveTime(TimeUnit.MILLISECONDS),
                threadPool.getActiveCount(),
                threadPool.getTaskCount());
    }

    static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "MonitorThreadPool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
