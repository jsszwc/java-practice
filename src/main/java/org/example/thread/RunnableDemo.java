package org.example.thread;

public class RunnableDemo implements Runnable {

    private int counter = 0; //非线程安全

    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            counter++;
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束，counter: " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new RunnableDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        Thread t4 = new Thread(new RunnableDemo());
        Thread t5 = new Thread(new RunnableDemo());
        t4.start();
        t5.start();
        t4.join();
        t5.join();

        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t6...");
            }
        });
        t6.start();
        t6.join();

        Thread t7 = new Thread(() -> {
            System.out.println("t7...");
        });
        t7.start();
        t7.join();

        System.out.println("主线程执行完毕");
    }
}

/*
赛力斯企业平台：
首页：10.37.86.33:8080/login
账号：admin
密码：123456
周期数据发送地址：10.37.86.5:25020
事件数据发送地址：10.37.84.20:25010

doris数据库：10.37.85.10.8031
doris账号：root
doris密码：无
数据库名称：icv_emp_sls

访问上述服务器地址需登录aTrust，具体账号和使用方式见https://tn919ojncs.feishu.cn/sheets/RAtDsaunkhgsAgtesC3c8N4gnW5?sheet=SOG5gr中的VPN账号标签页
属地测试平台、国家测试平台参见长安企业平台测试邮件中所使用的属地1、属地2信息。
注：邮件发送验证码由于赛力斯方面的问题，目前验证码固定为1234
 */