package org.example.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FileChannelTest {

    public static final int LEN = 1024*1024;

    public static void main(String[] args) throws IOException {
        File source = new File("./lib/nio/source.txt");
        File destination1 = new File("./lib/nio/destination1.txt");
        File destination2 = new File("./lib/nio/destination2.txt");
        transFile1(source, destination1);
        transFile2(source, destination2);
    }

    public static void transFile1(File source, File destination) throws IOException {
        if(!destination.exists()) {
            destination.createNewFile();
        }

        long startTime = System.currentTimeMillis();
        try (
                BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(source.toPath()));
                BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(destination.toPath()))
                ) {
            byte[] bytes = new byte[LEN];
            int len = 0;
            while((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("transFile1 exec time: " + (endTime - startTime) + "ms");
    }

    public static void transFile2(File source, File destination) throws IOException {
        if(!destination.exists()) {
            destination.createNewFile();
        }

        long startTime = System.currentTimeMillis();
        try (
                FileInputStream fileInputStream = new FileInputStream(source);
                FileOutputStream fileOutputStream = new FileOutputStream(destination);
                FileChannel readChannel = fileInputStream.getChannel();
                FileChannel writeChannel = fileOutputStream.getChannel()
                ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while(readChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                writeChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("transFile2 exec time: " + (endTime - startTime) + "ms");
    }
}
