package org.example.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * NIO同步阻塞服务端
 */
public class BlockSocketServer {

    public static void main(String[] args) throws IOException {
        try (
                ServerSocketChannel serverChannel = ServerSocketChannel.open();
                ) {
            serverChannel.bind(new InetSocketAddress(8888));
            while(true) {
                SocketChannel clientChannel = serverChannel.accept();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while(clientChannel.read(byteBuffer) != -1) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    System.out.println("收到消息: " + new String(bytes));
                    byteBuffer.clear();
                }
                clientChannel.close();
            }
        }
    }
}
