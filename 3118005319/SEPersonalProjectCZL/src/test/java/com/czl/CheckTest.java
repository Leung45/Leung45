package com.czl;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

//测试样例
public class CheckTest {

    @Test
    public void testResources() throws IOException {

        //测试用例
        File file1 = new File("src"+File.separator+"testResources"+File.separator+"orig.txt");
        File file2 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_add.txt");
        File file3 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_del.txt");
        File file4 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_dis_1.txt");
        File file5 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_dis_10.txt");
        File file6 = new File("src"+File.separator+"testResources"+File.separator+"orig_0.8_dis_15.txt");

        DissertationCheckerImpl dissertationChecker = new DissertationCheckerImpl();

        double result1 = dissertationChecker.getCheckedResult(file1,file2);
        double result2 = dissertationChecker.getCheckedResult(file1,file3);
        double result3 = dissertationChecker.getCheckedResult(file1,file4);
        double result4 = dissertationChecker.getCheckedResult(file1,file5);
        double result5 = dissertationChecker.getCheckedResult(file1,file6);

        System.out.println("文件orig与orig_0.8_add的查重结果为："+result1);
        System.out.println("文件orig与orig_0.8_del的查重结果为："+result2);
        System.out.println("文件orig与orig_0.8_dis_1的查重结果为："+result3);
        System.out.println("文件orig与orig_0.8_dis_10的查重结果为："+result4);
        System.out.println("文件orig与orig_0.8_dis_15的查重结果为："+result5);

    }
}
