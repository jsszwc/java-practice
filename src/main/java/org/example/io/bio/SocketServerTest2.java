package org.example.io.bio;

import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 对比传统IO和NIO复制一个文件的速度。所谓复制即读取源文件并写入到目标文件
 */
public class SocketServerTest2 {

    public static void main(String[] args) throws IOException {
        new SocketServer().start(8888);
    }

    public static class SocketServer {

        private static final Log logger = LogFactory.getLog(SocketServer.class);

        public void start(int port) throws IOException {
            ServerSocket serverSocket = new ServerSocket(port);
            logger.info("服务端启动成功，端口[" + port + "]");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new SocketHandler(socket)).start();
            }
        }
    }

    public static class SocketHandler implements Runnable {

        private static final Log logger = LogFactory.getLog(SocketHandler.class);

        private final Socket socket;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        @SneakyThrows(IOException.class)
        @Override
        public void run() {
            try(
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                int sourcePort = socket.getPort();
                String hostAddress = socket.getInetAddress().getHostAddress();

                String msg = reader.readLine();
                logger.info("客户端IP[" + hostAddress + "]-端口[" + sourcePort + "]发来信息：" + msg);
                writer.println("服务端已收到请求");
            } finally {
                socket.close();
            }
        }

    }
}