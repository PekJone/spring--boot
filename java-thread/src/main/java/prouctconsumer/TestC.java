package prouctconsumer;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-26  15:15
 * A 执行玩 调用 b  B执行完调用C  c执行玩调用A
 */
public class TestC {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{for (int i = 0; i < 10; i++) {
                data3.printA();
            }},"A").start();;
        new Thread(()->{for (int i = 0; i < 10; i++) {
            data3.printB();
        }},"B").start();;
        new Thread(()->{for (int i = 0; i < 10; i++) {
            data3.printC();
        }},"C").start();;
    }
}
