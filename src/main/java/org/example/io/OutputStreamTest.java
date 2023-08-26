package org.example.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

}
