package org.example.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamTest {

    /**
     * 实现类-FileInputStream，节点流
     */
    @Test
    public void testFileInputStream() throws IOException {
        //常见的初始化FileInputStream的方式：路径字符串、File类实例
        //FileInputStream fis = new FileInputStream("./lib/input/hello.txt");
        FileInputStream fis = new FileInputStream(new File("./lib/input/hello.txt"));

        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fis.read(bytes)) > 0) {
            System.out.println("len: " + len);
            System.out.println(new String(bytes,0, len));
        }
        fis.close();
    }

}
