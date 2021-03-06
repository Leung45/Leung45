package com.czl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author CZL
 * @date 2020 09 22
 */
public class DissertationCheck {

    /**
     * 程序入口
     * @param args args数组按照索引依次是[原文文件] [抄袭版论文的文件] [答案文件]的路径
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //界面
        menu();

        //获取文件位置
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);

        //调用查重方法
        DissertationCheckerImpl dissertationChecker = new DissertationCheckerImpl();
        double result = dissertationChecker.getCheckedResult(file1,file2);

        //获取当前系统时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        //输出查重结果
        System.out.println();
        System.out.println(">>论文查重结果："+result+"  查询时间："+dateTime.format(formatter));

        //保存论文查重结果文件
        saveResult(result,args[2]);
    }

    /**
     * 界面函数
     */
    public static void menu(){
        System.out.println("-------------------------------------------------------");
        System.out.println("                     >>论文查重<<                     ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

    /**
     * 获取文件位置
     * @return 文件路径集合
     */
    public static List<String> getFilePath(){
        List<String> filePathList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println(">>请输入论文原文文件的绝对路径(文件路径不能包括空格)：");
        String filePath1 = scanner.next();
        System.out.println(">>请输入查重文件的绝对路径(文件路径不能包括空格)：");
        String filePath2 = scanner.next();

        filePathList.add(filePath1);
        filePathList.add(filePath2);

        return filePathList;
    }

    /**
     * 保存论文查重结果文件
     * @param result 查重结果
     * @param resultFilePath 查重结果文件路径
     * @throws IOException
     */
    public static void saveResult(double result,String resultFilePath) throws IOException{

        //新建文件
        File resultFile = new File(resultFilePath);
        if(!resultFile.exists()) resultFile.createNewFile();

        //文件设置为可续写，字符集采用utf-8
        OutputStreamWriter save = new OutputStreamWriter(new FileOutputStream(resultFilePath,true),"utf-8");

        //获取当前系统时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        //保存查重结果
        save.write("论文查重结果："+result+"  查询时间："+dateTime.format(formatter)+"\r\n");
        //刷新输出流，缓冲的输出字节被写出
        save.flush();
        //关闭输出流，释放相关资源
        save.close();

        //获取项目路径
        String projectPath = new File("").getCanonicalPath();

        System.out.println("论文查重结果保存在："+projectPath+File.separator+"src"+File.separator+"testResultFolder"+File.separator+"checkResult.txt");
    }

}
