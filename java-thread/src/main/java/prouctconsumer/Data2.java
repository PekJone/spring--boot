package prouctconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-26  14:59
 */
public class Data2 {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public  void increment() throws InterruptedException{
//        condition.await(); // 等待
//        condition.signalAll();  //唤醒全部
        lock.lock();
        try{
            if (number!=0){
                //等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我+1完毕了
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void decrement()throws  InterruptedException{
        lock.lock();
        try {
            if(number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我-1完毕了
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}