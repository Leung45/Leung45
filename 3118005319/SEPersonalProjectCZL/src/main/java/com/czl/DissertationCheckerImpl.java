package com.czl;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * @author CZL
 * @date 2020 09 22
 */
public class DissertationCheckerImpl implements DissertationChecker{

    /**
     * 获取查重结果
     * @param file1
     * @param file2
     * @return 查重率
     * @throws IOException
     */
    @Override
    public double getCheckedResult(File file1,File file2) throws IOException{
        //读取文件内容到StringBuilder
        StringBuilder strBuilder1 = new StringBuilder();
        StringBuilder strBuilder2 = new StringBuilder();

        //读取文件内容
        strBuilder1 = readFileData(strBuilder1,file1);
        strBuilder2 = readFileData(strBuilder2,file2);

        //切词，获得切词后的List
        List<String> list1 = divideText(strBuilder1);
        List<String> list2 = divideText(strBuilder2);

        //调用余弦算法查重
        return cosAlgoCheck(list1,list2);
    }

    /**
     * 读取文件内容
     * @param strBuilder
     * @param file
     * @return 存储文件内容的StringBuilder
     */
    public static StringBuilder readFileData(StringBuilder strBuilder,File file){
        //使用BufferedReader提高读取效率
        BufferedReader buffReader = null;
        try{
            buffReader = new BufferedReader(new FileReader(file));
            String str = null;

            //逐行读取
            while((str = buffReader.readLine())!=null){
                strBuilder.append(System.lineSeparator()+str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //必须释放资源
            if(buffReader!=null){
                try {
                    buffReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strBuilder;
    }

    /**
     * 切词方法--IK切词
     * @param strBuilder
     * @return 分词的集合
     * @throws IOException
     */
    public static List<String> divideText(StringBuilder strBuilder) throws IOException{
        //转为StringReader处理
        String totalStr = strBuilder.toString();
        StringReader strReader = new StringReader(totalStr);

        //分词结果存储在List中
        List<String> list = new ArrayList<>();

        //使用IK切词，true代表使用智能分词
        IKSegmenter ikSegmenter = new IKSegmenter(strReader,true);
        Lexeme lexeme = null;
        while ((lexeme = ikSegmenter.next())!=null){
            String dividedStr = lexeme.getLexemeText();
            list.add(dividedStr);
        }
        return list;
    }

    /**
     * 余弦算法
     * @param list1 文件
     * @param list2 文件
     * @return 查重率
     */
    public static double cosAlgoCheck(List<String> list1, List<String> list2) {
        //非空判断
        if ( list1==null || list2==null || list1.size()==0 || list2.size()==0 ) {
            return 0;
        }

        //计算出切词后的每个词在各自所在文章中的频数
        Map<String, Integer> map1 = calSegFreq(list1);
        Map<String, Integer> map2 = calSegFreq(list2);

        //利用Set的特点存取两个文章中所有词语
        Set<String> segmentSet = new HashSet<>();
        segmentSet.addAll(list1);
        segmentSet.addAll(list2);

        //A·A
        Integer AA = 0;
        //B·B
        Integer BB = 0;
        //A·B
        Integer AB = 0;

        for (String segment : segmentSet) {
            //计算出Set中每个词分别在两个文章切词集合中的频数
            Integer temp1 = map1.get(segment);
            int freq1 = 0;
            if (null != temp1) {
                freq1 = temp1.intValue();
            }
            Integer temp2 = map2.get(segment);
            int freq2 = 0;
            if (null != temp2) {
                freq2 = temp2.intValue();
            }

            //A·A
            AA = AA + freq1 * freq1;

            //B·B
            BB = BB + freq2 * freq2;

            //A·B
            AB = AB + freq1 * freq2;
        }

        //|A| = sqrt(A·A)
        double absA = Math.sqrt(AA.doubleValue());

        //|B| = sqrt(B·B)
        double absB = Math.sqrt(BB.doubleValue());

        //|A|*|B|
        BigDecimal absAB = BigDecimal.valueOf(absA).multiply(BigDecimal.valueOf(absB));

        //cosθ = (A·B)/(|A|*|B|)，结果保留小数点后两位
        double cos = BigDecimal.valueOf(AB).divide(absAB, 2, BigDecimal.ROUND_FLOOR).doubleValue();

        return cos;
    }

    /**
     * 计算分词的频数
     * @param list
     * @return 分词及其频数的键值对集合
     */
    private static Map<String, Integer> calSegFreq(List<String> list) {
        Map<String, Integer> segFreq = new HashMap<>();
        for (String segment : list) {
            Integer integer = segFreq.get(segment);
            if (null == integer) {
                //无则新建
                Integer integerNew = 0;
                segFreq.put(segment, integerNew+1);
            }else {
                //有则加一
                segFreq.put(segment,integer+1);
            }
        }
        return segFreq;
    }
}
