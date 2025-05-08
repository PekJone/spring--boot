package callable;

import java.util.concurrent.CountDownLatch;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-26  17:20
 */
public class CountDownBatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"go out");
                countDownLatch.countDown(); //数量减1
            },String.valueOf(i)).start();
        }
        countDownLatch.await(); //等待计数器归零 然后再向下执行
        System.out.println("close door");
    }
}
