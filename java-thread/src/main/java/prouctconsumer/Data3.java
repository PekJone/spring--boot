package prouctconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-26  15:15
 */
public class Data3 {
    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    private  int number =1 ;  //1A 2B  3C

    public void printA(){
        lock.lock();
        try {
            //业务 判断  执行 通知
            while (number!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"AAAAAAAAAA");
            number=2;
            condition2.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            //业务 判断  执行 通知
            while (number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"BBBBBBBBBBB");
            number=3;
            condition3.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            //业务 判断  执行 通知
            while (number!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"CCCCCCCCCCCC");
            number=1;
            condition1.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}
