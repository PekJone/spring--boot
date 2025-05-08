package pctest;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-26  8:46
 */
public class TestPC {
    public static void main(String[] args) {
        SynCcontainer synCcontainer = new SynCcontainer();

        new Product(synCcontainer).start();
        new Consumer(synCcontainer).start();
    }
}

class Product extends Thread{
    SynCcontainer synCcontainer;

    public Product(SynCcontainer container){
        this.synCcontainer = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了"+i+"只鸡");
            synCcontainer.push(new Chicken(i));
        }
    }
}

class Consumer extends Thread{
    SynCcontainer synCcontainer;

    public Consumer(SynCcontainer container){
        this.synCcontainer = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了"+synCcontainer.pop().id+"只鸡");
        }
    }
}

class Chicken{
    int id ;
    public Chicken(int id){
        this.id = id ;
    }
}

class SynCcontainer{
    Chicken[] chickens = new Chicken[10];
    int count=0 ;
    public synchronized void push(Chicken chicken){
        if (count==chickens.length){

        }else {
            chickens[count] = chicken;
            count++;
        }
    }

    public synchronized Chicken pop(){
        if(count==0){

        }
            count--;
            Chicken chicken = chickens[count];


        return chicken;
    }


}