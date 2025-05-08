package prouctconsumer;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-26  11:00
 */
public class TestA {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        },"B").start();
    }


}
