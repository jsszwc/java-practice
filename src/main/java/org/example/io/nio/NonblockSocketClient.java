package org.example.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NonblockSocketClient {

    public static void main(String[] args) throws IOException {
        try (
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8889));
                FileChannel fileChannel = FileChannel.open(Paths.get("./lib/nio/source.txt"), StandardOpenOption.READ)
        ) {
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(fileChannel.read(buffer) != -1) {
                buffer.flip();

            }
        }

    }
}
