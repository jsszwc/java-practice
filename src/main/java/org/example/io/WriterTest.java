package org.example.io;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

public class WriterTest {

    /**
     * 实现类-FileWriter，节点流
     */
    @Test
    public void testFileWriter() {
        try (
//                FileWriter fw = new FileWriter(new File("./lib/output/2.txt"));
                FileWriter fw = new FileWriter("./lib/output/2.txt");
                ) {
            fw.write("最近经济情况不太好啊\n");
            fw.write("好好学习，天天向上");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现类-StringWriter
     * 以字符串作为写入的物理节点。因为String不能修改，底层实际使用的是StringBuffer
     */
    @Test
    public void testStringWriter() {
        try(
                StringWriter sw = new StringWriter(20)
        ) {
            sw.write("aaaaaa\n");
            sw.write("bbbbbb\n");
            sw.write("cccccc\n");
            System.out.println(sw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
