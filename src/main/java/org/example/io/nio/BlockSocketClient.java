package org.example.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockSocketClient {

    public static void main(String[] args) throws IOException {
        try (
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8888));
                ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            for(int i = 0; i < 10; i++) {
                byteBuffer.put("hello, Server!".getBytes(StandardCharsets.UTF_8));
                //注意这里要切换为读模式，才能往channel中写入
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    socketChannel.write(byteBuffer);
                }
                byteBuffer.clear();
            }
        }
    }
}
