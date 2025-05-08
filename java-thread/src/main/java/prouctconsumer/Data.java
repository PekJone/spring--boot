package prouctconsumer;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-26  11:01
 */

public class Data {
    private int number = 0;
    public synchronized void increment() throws InterruptedException{
        if (number!=0){
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我+1完毕了
        this.notifyAll();
    }

    public synchronized void decrement()throws  InterruptedException{
        if(number==0){
             this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        //通知其他线程，我-1完毕了
        this.notifyAll();
    }
}
