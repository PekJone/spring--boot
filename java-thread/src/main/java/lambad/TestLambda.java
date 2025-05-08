package lambad;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-25  15:05
 */
//lambda推导
public class TestLambda {

    //静态内部类
    static class Like2 implements ILike{
        @Override
        public void Lambda() {
            System.out.println("I like lambda2");
        }
    }
    public static void main(String[] args) {
        ILike like = new Like();
        like.Lambda();
        like = new Like2();
        like.Lambda();


        //局部内部类
        class Like3 implements ILike{
            @Override
            public void Lambda() {
                System.out.println("I like lambda3");
            }
        }
        like = new Like3();
        like.Lambda();
        //匿名内部类
        like = new ILike() {
            @Override
            public void Lambda() {
                System.out.println("I like lambda4");
            }
        };
        like.Lambda();
        //用lambda简化
        like = ()->{
            System.out.println("I like lambda5");
        };
        like.Lambda();
    }
}

interface ILike{
    void Lambda();
}

class Like implements ILike{
    @Override
    public void Lambda() {
        System.out.println("I like lambda");
        List<String> list = new Vector<>();
        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        List<String> list2 = new CopyOnWriteArrayList<>();
        Set<String> string = new HashSet<>();
        string.add("wag");
        Map<String,String>  map = new HashMap<>();
        Map<String,String>  map1 = Collections.synchronizedMap(new HashMap<>());
        Map<String,String>  map2 = new ConcurrentHashMap<>();
    }
}
