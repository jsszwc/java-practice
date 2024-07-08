package org.example.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OutputStreamTest {

    /**
     * 实现类-FileOutputStream，节点流
     */
    @Test
    public void testFileOutputStream() {
        try (
                FileOutputStream fos = new FileOutputStream("./lib/output/1.txt");
//                FileOutputStream fos = new FileOutputStream(new File("./lib/output/1.txt"));
                FileInputStream fis = new FileInputStream("./lib/input/hello.txt");
                ) {
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = fis.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现类-PrintStream
     */
    @Test
    public void testPrintStream() {
        try(
//                PrintStream ps = new PrintStream(new FileOutputStream("test.txt"))
                PrintStream ps = new PrintStream(Files.newOutputStream(Paths.get("./lib/output/test.txt")))
                ) {
            ps.println("普通字符串");
            ps.println("测试");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
