package org.example.io;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    /**
     * 测试文件名相关方法
     * @throws IOException
     */
    @Test
    public void testFileNameRelationMethod() throws IOException {
        //test.txt在物理操作系统中可以不存在，不影响下面方法使用
        File file = new File("test.txt");
        //文件名称
        System.out.println(file.getName());
        //路径名：构造时使用的路径
        System.out.println(file.getPath());
        //得到绝对路径
        System.out.println(file.getAbsolutePath());
        //父目录。构造File对象时的路径中没有父目录则返回null
        System.out.println(file.getParent());

        //返回使用文件的绝对路径构造的新File对象
        File newFile = file.getAbsoluteFile();
        System.out.println(newFile);
        System.out.println(newFile.getName());
        System.out.println(newFile.getPath());
        System.out.println(newFile.getAbsolutePath());
        System.out.println(newFile.getParent());
    }

    /**
     * 文件检测相关方法
     */
    @Test
    public void testFileDetectionRelationMethod() throws IOException {
        File file = new File("test.txt");
        //文件是否存在
        System.out.println(file.exists());
        //是否绝对路径构造的File对象
        System.out.println(file.isAbsolute());
        //是否是文件
        System.out.println(file.isFile());
        //是否是目录
        System.out.println(file.isDirectory());
        //是否绝对路径
        System.out.println(file.isAbsolute());
        //文件或目录是否可读、可写
        System.out.println(file.canRead());
        System.out.println(file.canWrite());

        //在文件系统中创建文件
        file.createNewFile();
        System.out.println("-------------------");

        //文件是否存在
        System.out.println(file.exists());
        //是否绝对路径构造的File对象
        System.out.println(file.isAbsolute());
        //是否是文件
        System.out.println(file.isFile());
        //是否是目录
        System.out.println(file.isDirectory());
        //是否绝对路径
        System.out.println(file.isAbsolute());
        //文件或目录是否可读、可写
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
    }

    /**
     * 文件信息
     */
    @Test
    public void testFileInfoRelationMethod() {
        File file = new File("test.txt");
        //最后修改时间-毫秒时间戳
        System.out.println(file.lastModified());
        //文件内容长度
        System.out.println(file.length());
    }

    /**
     * 文件操作
     */
    @Test
    public void testFileOperationRelationMethod() throws IOException {
        File file = new File("test.txt");
        if(! file.exists()) {
            //创建文件
            file.createNewFile();
        }
        //删除文件
        file.delete();
        //在默认的临时文件目录中创建临时文件
        File tempFile = File.createTempFile("aaaa", "bbbb");
        System.out.println(tempFile.getAbsoluteFile());
        //在指定目录中创建临时文件
        File tempFile1 = File.createTempFile("aaaa", "bbbb", new File("."));
        System.out.println(tempFile1.getAbsoluteFile());
    }

    /**
     * 目录操作
     */
    @Test
    public void testFileDirectoryRelationMethod() {
        File file = new File("./testDir/t1");
//        //创建目录。不能一次性创建多个嵌套目录，即创建/root/a/b/c/中目录c时，如果目录a和目录b不存在则无法创建成功
//        file.mkdir();
        //创建目录，可以处理嵌套目录的情况
        file.mkdirs();

        File curDir = new File(".");
        //当前目录下的所有文件名和路径名
        String[] list = curDir.list();
        System.out.println(list.length);
        for(String fileName : list) {
            System.out.println(fileName);
        }
        //和list方法类似，只是返回类型不一样
        File[] files = curDir.listFiles();
        System.out.println(files.length);

        //列出所有根路径
        File[] rootFiles = File.listRoots();
        for(File file1 : rootFiles) {
            System.out.println(file1);
        }
    }

    /**
     * FilenameFilter接口使用
     * 用于筛选返回的文件名
     */
    @Test
    public void testFilenameFilterInterface() {
        File file = new File(".");
        String[] list = file.list();
        for(String fileName : list) {
            System.out.println(fileName);
        }

        System.out.println("------------");
        //文件名不带c
        String[] list1 = file.list((dir, name) -> !name.contains("c"));
        for(String fileName : list1) {
            System.out.println(fileName);
        }
    }

    /**
     * FileFilter接口
     */
    @Test
    public void testFileFilterInterface() {
        File file = new File(".");
        File[] files = file.listFiles();
        for(File tempFile : files) {
            System.out.println(tempFile);
        }

        System.out.println("------------");
        //文件名不带c
        File[] files1 = file.listFiles(pathname -> !pathname.getName().contains("c"));
        for(File tempFile : files1) {
            System.out.println(tempFile);
        }
    }
}
