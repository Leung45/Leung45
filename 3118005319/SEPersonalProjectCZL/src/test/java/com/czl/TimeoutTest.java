package com.czl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.File;
import java.io.IOException;

//超时测试
public class TimeoutTest {

    //测试确保程序单次论文查重中耗时低于5秒
    @Test
    @Timeout(5)
    public void testExample1() throws IOException {
        File file1 = new File("src"+File.separator+"testResources"+File.separator+"orig.txt");
        File file2 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_add.txt");

        DissertationCheckerImpl dissertationChecker = new DissertationCheckerImpl();
        double result1 = dissertationChecker.getCheckedResult(file1,file2);
        System.out.println("论文查重结果为："+result1);
    }

    @Test
    @Timeout(5)
    public void testExample2() throws IOException {
        File file1 = new File("src"+File.separator+"testResources"+File.separator+"orig.txt");
        File file3 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_del.txt");

        DissertationCheckerImpl dissertationChecker = new DissertationCheckerImpl();
        double result2 = dissertationChecker.getCheckedResult(file1,file3);
        System.out.println("论文查重结果为："+result2);
    }

    @Test
    @Timeout(5)
    public void testExample3() throws IOException {
        File file1 = new File("src"+File.separator+"testResources"+File.separator+"orig.txt");
        File file4 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_dis_1.txt");

        DissertationCheckerImpl dissertationChecker = new DissertationCheckerImpl();
        double result3 = dissertationChecker.getCheckedResult(file1,file4);
        System.out.println("论文查重结果为："+result3);
    }

    @Test
    @Timeout(5)
    public void testExample4() throws IOException {
        File file1 = new File("src"+File.separator+"testResources"+File.separator+"orig.txt");
        File file5 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_dis_10.txt");

        DissertationCheckerImpl dissertationChecker = new DissertationCheckerImpl();
        double result4 = dissertationChecker.getCheckedResult(file1,file5);
        System.out.println("论文查重结果为："+result4);
    }

    @Test
    @Timeout(5)
    public void testExample5() throws IOException {
        File file1 = new File("src"+File.separator+"testResources"+File.separator+"orig.txt");
        File file6 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_dis_15.txt");

        DissertationCheckerImpl dissertationChecker = new DissertationCheckerImpl();
        double result5 = dissertationChecker.getCheckedResult(file1,file6);
        System.out.println("论文查重结果为："+result5);
    }

}
