package test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/19 9:32
 * @version: 1.0
 ***********************/
public class MDCTest {


    private static Logger logger = LoggerFactory.getLogger(MDCTest.class);


    public static void main(String[] args) {
        MyThread t1 = new MyThread(String.valueOf(SnowIdWorkerUtil.getInstance().getNextId()));
        MyThread t2 = new MyThread(String.valueOf(SnowIdWorkerUtil.getInstance(1, 2).getNextId()));
        t1.start();
        t2.start();
    }


    public static class MyThread extends Thread {
        private String mdcId;

        public MyThread() {
        }

        public MyThread(String mdcId) {
            this.mdcId = mdcId;
        }

        public MyThread(String mdcId, ThreadGroup group, Runnable target, String name,
                        long stackSize) {
            super(group, target, name, stackSize);
            this.mdcId = mdcId;
        }

        @Override
        public void run() {
//            Optional.of("当前执行:" + (i++)).ifPresent(System.out::println);
            Optional.of("当前执行:" + (mdcId)).ifPresent(System.out::println);
            MDC.put("mdc_trace_id", mdcId);
            logger.info("开始任务....");
//            for (int i = 0; i < 100; i++) {
//                if (i == 10) {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
////                        int tmp = 1 / 0;
//                    } catch (Exception e) {
//                        logger.error("[{}]线程{}运行发生异常!", MDC.get("username"), Thread.currentThread().getName(), e);
//                    }
//                }
//            }
            super.run();
        }
    }
}
