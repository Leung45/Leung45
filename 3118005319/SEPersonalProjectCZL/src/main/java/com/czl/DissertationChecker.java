package com.czl;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class DissertationChecker {

    public static double getCheckedResult(File file1,File file2) throws IOException{
        StringBuilder strBuilder1 = new StringBuilder();
        StringBuilder strBuilder2 = new StringBuilder();
        strBuilder1 = readFileData(strBuilder1,file1);
        strBuilder2 = readFileData(strBuilder2,file2);

        //切词，获得切词后的List
        List<String> list1 = divideText(strBuilder1);
        List<String> list2 = divideText(strBuilder2);

        return cosAlgoCheck(list1,list2);
    }

    public static StringBuilder readFileData(StringBuilder strBuilder,File file){
        BufferedReader buffReader = null;//使用BufferedReader提高读取效率
        try{
            buffReader = new BufferedReader(new FileReader(file));
            String str = null;
            while((str = buffReader.readLine())!=null){//逐行读取
                strBuilder.append(System.lineSeparator()+str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {//必须释放资源
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

    public static List<String> divideText(StringBuilder strBuilder) throws IOException{
        List<String> list = new ArrayList<>();

        String totalStr = strBuilder.toString();//转为String处理
        StringReader strReader = new StringReader(totalStr);

        IKSegmenter ikSegmenter = new IKSegmenter(strReader,true);//true代表？
        Lexeme lexeme = null;
        while ((lexeme = ikSegmenter.next())!=null){
            String dividedStr = lexeme.getLexemeText();
            list.add(dividedStr);
        }
        return list;
    }

    //使用余弦算法查重
    public static double cosAlgoCheck(List<String> list1, List<String> list2) {
        if ( list1==null || list2==null || list1.size()==0 || list2.size()==0 ) {
            return 0;
        }

        Map<String, Integer> map1 = calSegFreq(list1);
        Map<String, Integer> map2 = calSegFreq(list2);

        Set<String> segmentSet = new HashSet<>();
        segmentSet.addAll(list1);
        segmentSet.addAll(list2);

        Integer ab = new Integer(0);
        Integer aa = new Integer(0);
        Integer bb = new Integer(0);


        for (String segment : segmentSet) {
            Integer tempInteger1 = map1.get(segment);
            int x1 = 0;
            if (null != tempInteger1) {
                x1 = tempInteger1.intValue();
            }
            Integer tempInteger2 = map2.get(segment);
            int x2 = 0;
            if (null != tempInteger2) {
                x2 = tempInteger2.intValue();
            }

            ab=ab+x1 * x2;
            aa=aa+x1 * x1;
            bb=bb+x2 * x2;
        }

        double aaa = Math.sqrt(aa.doubleValue());
        double bbb = Math.sqrt(bb.doubleValue());

        BigDecimal aabb = BigDecimal.valueOf(aaa).multiply(BigDecimal.valueOf(bbb));
        double cos = BigDecimal.valueOf(ab).divide(aabb, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
        return cos;
    }

    private static Map<String, Integer> calSegFreq(List<String> wordList) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : wordList) {
            Integer integer = freq.get(word);
            if (null == integer) {
                Integer integerNew = 0;
                freq.put(word, integerNew+1);
            }else {
                freq.put(word,integer+1);
            }
        }
        return freq;
    }
}
