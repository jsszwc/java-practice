package org.example.io;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

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

}
