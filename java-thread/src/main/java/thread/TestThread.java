package thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-04-25  10:20
 * @Descripes 多线程下载
 */

public class TestThread extends Thread{
    private String url;
    private String name;

    TestThread(String url,String name){
        this.name = name;
        this.url = url;
    }

    @Override
    public void run() {
        WebDownLoader web = new WebDownLoader();
        web.downLoader(url,name);
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        TestThread thread = new TestThread("https://ad-delivery.net/px.gif?ch=2","user");
        TestThread thread2 = new TestThread("https://ad-delivery.net/px.gif?ch=2","user2");
        TestThread thread3 = new TestThread("https://ad-delivery.net/px.gif?ch=2","user3");
        thread.start();
        thread2.start();
        thread3.start();
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
