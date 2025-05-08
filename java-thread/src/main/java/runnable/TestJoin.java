package runnable;



/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-25  15:58
 *
 * 插队
 */
public class TestJoin implements Runnable{
    @Override
    public void run() {

        for(int i=0;i<100;i++){
            System.out.println("线程VIP来了");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //主线程
        for (int i=0;i<1000;i++){
            if(i==200){
                thread.join();
            }
            System.out.println("main"+1);
        }
    }
}
