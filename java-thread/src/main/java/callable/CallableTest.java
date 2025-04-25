package callable;

import org.apache.commons.io.FileUtils;
import thread.TestThread;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-25  11:16
 */
public class CallableTest implements Callable<Boolean> {
    private String url;
    private String name;

    CallableTest(String url,String name){
        this.name = name;
        this.url = url;
    }

    @Override
    public Boolean call() {
        WebDownLoader web = new WebDownLoader();
        web.downLoader(url,name);
        System.out.println("下载了文件名为："+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableTest thread = new CallableTest("https://ad-delivery.net/px.gif?ch=2","user");
        CallableTest thread2 = new CallableTest("https://ad-delivery.net/px.gif?ch=2","user2");
        CallableTest thread3 = new CallableTest("https://ad-delivery.net/px.gif?ch=2","user3");

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Boolean> future1 = executorService.submit(thread);
        Future<Boolean> future2 = executorService.submit(thread2);
        Future<Boolean> future3 = executorService.submit(thread3);

        boolean r1  = future1.get();
        boolean r2  = future1.get();
        boolean r3  = future1.get();
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        executorService.shutdown();
    }
}
class WebDownLoader{
    //下载方法
    public void downLoader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            throw new RuntimeException("io异常，downLoader方法出现问题");
        }
    }
}