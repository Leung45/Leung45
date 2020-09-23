package com.czl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DissertationCheck {
    public static void main(String[] args) throws IOException {
        //界面
        menu();

        //获取文件位置
        List<String> filePathList = getFilePath();
        File file1 = new File(filePathList.get(0));
        File file2 = new File(filePathList.get(1));

        double result = DissertationChecker.getCheckedResult(file1,file2);
        System.out.println(">>论文查重结果："+result);
        saveResult(result);
    }

    public static void menu(){
        System.out.println("-------------------------------------------------------");
        System.out.println("                     >>论文查重<<                     ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

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

    public static void saveResult(double result) throws IOException{
        //新建文件夹
        File resultFolder = new File("C:"+File.separator+"checkResultFolder");
        if(!resultFolder.exists()) resultFolder.mkdirs();

        //新建文件
        File resultFile = new File("C:"+File.separator+"checkResultFolder"+File.separator+"checkResult.txt");
        if(!resultFile.exists()) resultFile.createNewFile();

        //文件设置为可续写，字符集采用utf-8
        OutputStreamWriter save = new OutputStreamWriter(new FileOutputStream("C:"+File.separator+"checkResultFolder"+File.separator+"checkResult.txt",true),"utf-8");

        save.write("论文查重结果："+result+"\r\n");//查重结果保存
        save.flush();//刷新输出流，缓冲的输出字节被写出
        save.close();//关闭输出流，释放相关资源

        System.out.println("论文查重结果保存在：C:"+File.separator+"checkResultFolder"+File.separator+"checkResult.txt");
    }

}
