package test.log;

import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/***********************
 * @Description: 雪花ID生成器 <BR>
 * @author: zhao.song
 * @since: 2021/2/19 9:55
 * @version: 1.0
 ***********************/
public final class SnowIdWorkerUtil {

    private static Logger logger = LoggerFactory.getLogger(SnowIdWorkerUtil.class);

    private long machineId;
    private long dataCenterId;

    private SnowIdWorkerUtil() {
        this(0l, 0l);
    }

    private SnowIdWorkerUtil(long machineId, long dataCenterId) {
        this.machineId = machineId;
        this.dataCenterId = dataCenterId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public void setDataCenterId(long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    /**
     * 成员类，SnowFlakeUtil的实例对象的保存域
     */
    private static class IdGenHolder {
        private static SnowIdWorkerUtil instance = new SnowIdWorkerUtil();
    }

    /**
     * 外部调用获取SnowFlakeUtil的实例对象，确保不可变
     */
    public static SnowIdWorkerUtil getInstance() {
        return IdGenHolder.instance;
    }

    /**
     * 外部调用获取SnowFlakeUtil的实例对象，确保不可变
     */
    public static SnowIdWorkerUtil getInstance(long machineId, long dataCenterId) {
        SnowIdWorkerUtil tmpInstance = IdGenHolder.instance;
        tmpInstance.setMachineId(machineId);
        tmpInstance.setDataCenterId(dataCenterId);
        return tmpInstance;
    }


    public synchronized long getNextId() {
        return IdUtil.createSnowflake(machineId, dataCenterId).nextId();
    }

    private static ExecutorService service0 = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS
            , new ArrayBlockingQueue<>(50), new MyThreadFactory());

    private static ExecutorService service1 = Executors.newSingleThreadExecutor();

    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
//            Thread t = new MDCTest.MyThread(String.valueOf(SnowIdWorkerUtil.getInstance().getNextId()), group, r,
//                    namePrefix + threadNumber.getAndIncrement(),
//                    0);
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0){
                @Override
                public void run() {
                    String mdcId = "APPNAME" + String.valueOf(SnowIdWorkerUtil.getInstance().getNextId());
                    Optional.of("当前执行:" + (mdcId)).ifPresent(System.out::println);
                    MDC.put("mdc_trace_id", mdcId);
                    logger.info("开始任务...");
                    super.run();
                }
            };
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public static void main(String[] args) {
//        System.out.println(SnowIdWorkerUtil.getInstance().getNextId());
//        System.out.println(SnowIdWorkerUtil.getInstance(1,2).getNextId());
        service0.execute(() -> System.out.println("running: 1, mdcid:" + MDC.get("mdc_trace_id")));
        service0.execute(() -> System.out.println("running: 2, mdcid:" + MDC.get("mdc_trace_id")));
//        service1.submit(() -> System.out.println("running: 1"));
    }

}
