package runnable;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-25  10:54
 */
public class Race implements Runnable{
    public static  String winner;


    @Override
    public void run() {
        for (int i=0;i<=100;i++){
            if(Thread.currentThread().getName().equals("兔子") && i%10==0){
                try {
                    Thread.sleep(5);
                    System.out.println("我累了 休息一会");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            boolean flag = gameOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"步");
        }
    }

    public boolean gameOver(int step){
        if(winner !=null){
            return true;
        }else{
            if(step>=100){
                winner =Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }
}
