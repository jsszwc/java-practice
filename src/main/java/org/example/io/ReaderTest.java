package org.example.io;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

public class ReaderTest {

    /**
     * Reader实现类-FileReader类，节点流
     */
    @Test
    public void testFileReader() {
        //所有IO资源类都实现了AutoCloseable接口，可以使用自动关闭资源的try语句改写
        try(
//                FileReader fr = new FileReader("./lib/input/hello.txt")
                FileReader fr = new FileReader(new File("./lib/input/hello.txt"))
                ) {
            char[] buf = new char[1024];
            int len = 0;
            while((len = fr.read(buf)) > 0) {
                System.out.println("循环了一次");
                System.out.println("len: " + len);
                System.out.println(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现类-StringReader
     * 以字符串作为物理节点并读取内容
     */
    @Test
    public void testStringReader() {
        String src = "aaaaaaaaa\n" + "bbbbbbbbbbbbb\n" + "cccccccccccc\n" + "dddddddddd\n";

        char[] buffer = new char[16];
        int len = 0;
        try(
                StringReader sr = new StringReader(src);
                ) {
            while((len = sr.read(buffer)) > 0) {
                System.out.print(new String(buffer, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
