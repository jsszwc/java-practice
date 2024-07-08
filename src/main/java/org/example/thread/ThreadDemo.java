package org.example.thread;

public class ThreadDemo extends Thread {
    private int counter = 0;

    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            counter++;
        }
        System.out.println(getName() + " 运行结束，counter: " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo t1 = new ThreadDemo();
        ThreadDemo t2 = new ThreadDemo();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("主线程执行结束");
    }
}
