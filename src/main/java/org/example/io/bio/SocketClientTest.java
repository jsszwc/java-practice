package org.example.io.bio;

import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class SocketClientTest {

    public static void main(String[] args) throws InterruptedException {
        int clientNumber = 20;

        CountDownLatch countDownLatch = new CountDownLatch(clientNumber);
        for (int i = 0; i < clientNumber; i++) {
            new Thread(new SocketClient("socket-" + i, countDownLatch)).start();
            countDownLatch.countDown();
        }

        synchronized (SocketClientTest.class) {
            SocketClientTest.class.wait();
        }
    }
}

class SocketClient implements Runnable {

    private final String clientName;

    private final CountDownLatch countDownLatch;

    private static final Log logger = LogFactory.getLog(SocketClient.class);

    public SocketClient(String clientName, CountDownLatch countDownLatch) {
        this.clientName = clientName;
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        countDownLatch.await();
        try(
                Socket socket = new Socket("localhost", 8888);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            logger.info("客户端启动成功，客户端名称[" + clientName + "]");
            writer.println("客户端[" + clientName + "]发送请求");
            logger.info("客户端[" + clientName + "]已发送请求");

            String msg = reader.readLine();
            logger.info("客户端[" + clientName + "]收到响应：" + msg);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
