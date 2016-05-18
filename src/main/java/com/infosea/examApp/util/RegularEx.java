/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosea.examApp.util;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author infosea
 * 试题中正确答案可以用括号，其他地方不要用括号
 */
@Repository
public class RegularEx {
//                                           题号      题干    括号  答案    括号   题干   括号    答案   括号   题干   括号  答案   括号   题干   括号       答案        括号               题干          A答案             B答案后面的内容
//    public static final String str1 =  "(\\d+、)?(\\s?)?(（?)?(\\S+)?(）?)?(\\S+)?(（?)?(\\S+)?(）?)?(\\S*)?(（?)?(\\S+)?(）?)?(\\S+)?(?=（)(（)(\\S+)?(?=）)(）)(\\S+)?(?=A、)(A、)?(.*)?(?=B、)(B、)?(.*)?";
    public static final String str1 =  "(\\d+、)?(（\\s)?([A-Z]+)?(）\\s)?(\\S+)?(（\\s)?([A-Z]+)?(）\\s)?(\\S+)?(（\\s)?([A-Z]+)?(）\\s)?(\\S+)?(?=（ )(（ )([A-Z]+)?(?=） )(） )(\\S+)?(?= A、)( A、)?(.*)?(?= B、)( B、)?(.*)?";
    public static final String str2 = "(\\d+、)?(\\S+\\s?)?(（)(\\s?\\S+\\s?)?(?=）)(）)?(\\s?\\S*\\s?)?(?=A、)(A、)?(.*)?(?=B、)(B、)?(.*)?";

    public static final String regex_dig = "\\d+、";
    public static Pattern pattern;
    public static Pattern pattern1 = Pattern.compile(str1);
    public static Pattern pattern_dig = Pattern.compile(regex_dig);
    public static Matcher coreMatcher;
    public static Matcher digMatcher;
    StringBuilder questionBuilder = new StringBuilder();
    StringBuilder optionBuilder = new StringBuilder();

    public boolean parseUniformString(String str) {
        Matcher matcher = pattern1.matcher(str);
        if (matcher.matches()) {
            coreMatcher = matcher;
            return true;
        } else {
            return false;
        }
    }

    public boolean parseString(String str) {
        if (parseUniformString(str)) {
        }  else {
            return false;
        }
        return true;
    }


    public Map<String, String> getQuestionFromUniformStringByRegex(String str) {
        Map<String, String> map = new HashMap<>();
        //将所有的空格去掉
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
           str = matcher.replaceAll("");
        }
        str =str.replaceAll(" ","");
        str = str.replaceAll("　","");
         pattern = Pattern.compile("　");
         matcher = pattern.matcher(str);
        while (matcher.find()) {
            str = matcher.replaceAll("");
        }
        //将非中文括号换成 中文括号
        StringBuffer values = new StringBuffer();
        pattern = Pattern.compile("\\(+");
        matcher = pattern.matcher(str);
        while (matcher.find()) {
            str = matcher.replaceAll("（");
        }
        pattern = Pattern.compile("\\)+");
        matcher = pattern.matcher(str);
        while (matcher.find()) {
           str = matcher.replaceAll("）");
        }

        //将所有的在str最前面的数字的后面的。号转换成、
        pattern = Pattern.compile("^(?:\\d+(\\.))");
        matcher = pattern.matcher(str);
        while (matcher.find()) {
            String mstr = matcher.group();
            matcher = matcher.appendReplacement(values,  mstr.substring(0,mstr.length()-1)+"、" );
        }
        matcher.appendTail(values);
        str = values.toString();
        values.setLength(0);
        //将所有的 非（字母+）的括号 转换成 【】括号
        //将所有的 （字母+）的括号 后面加上空格

        //当正确答案不是 [A-D] 时 也就是多选题的答案已[E-Z]时匹配会出错
        //或者题干中包括 （后面接A B C D中的一个时匹配会出错
        pattern = Pattern.compile("(（)(?=[A-D])");
        matcher =pattern.matcher(str);
        while (matcher.find()) {
            String mstr = matcher.group();
            matcher = matcher.appendReplacement(values,  mstr + " ");
        }
        matcher.appendTail(values);
        str = values.toString();
        values.setLength(0);

        //题干中包括 后面接 [A-Z]中的一个接）时匹配会出错
        pattern = Pattern.compile("(?<=[A-F])(）)");
        matcher =pattern.matcher(str);
        while (matcher.find()) {
            String mstr = matcher.group();
            matcher = matcher.appendReplacement(values,  mstr + " ");
        }
        matcher.appendTail(values);
       str = values.toString();
        values.setLength(0);
        //在所有的选项前面加空格
        pattern = Pattern.compile("([A-Z]、)");
        matcher = pattern.matcher(str);
        while (matcher.find()) {
            String mstr = matcher.group();
            matcher = matcher.appendReplacement(values, " " + mstr );
        }
        matcher.appendTail(values);
        str = values.toString();
        values.setLength(0);

        //将所有的 带超链接 去掉
        //将所有的 带超链接 去掉

        if (parseString(str)) {
            int optionStartIdx = 0;
            StringBuilder questionBuilder = new StringBuilder();
            StringBuilder optionBuilder = new StringBuilder();
            String qid = "";
            String question = "";
            String option = "";
            String answer = "";
            int groupCount = coreMatcher.groupCount();
            for (int i = 1; i <= groupCount; i++) {
                String s = coreMatcher.group(i);
                if (s == null || s.equals("")) continue;
                if (equalsABCD(s.trim())) {
                    answer = s;
                } else {
                    if (s.equals(" A、")) {
                        optionStartIdx = i;
                    }
                    if (optionStartIdx == 0) {
                        questionBuilder.append(s);
                    } else {
                        optionBuilder.append(s);
                    }
                }
            }
            map.put("answer", answer);
            map.put("question", questionBuilder.toString());
            map.put("option", optionBuilder.toString().substring(1));
        } else {
            System.out.println(str);
        }
        return map;
    }

    public static boolean equalsABCD(String s) {
        return s.equals("A") || s.equals("B") || s.equals("C") || s.equals("D") || s.matches("[A-Z]+");
    }

    public static void main(String[] args) {
        String str = "";
        str="29、联39、联49、联59、联69、联79、联89、联";
          RegularEx regularEx = new RegularEx();
        regularEx.getQuestionFromUniformStringByRegex(str);

    }

    public static List<String> findDigital(String outString) {
        List<String> list_dig = new ArrayList<>();
        digMatcher = pattern_dig.matcher(outString);
        while (digMatcher.find()) {
            String mstr = digMatcher.group();
            System.out.println(mstr);
            list_dig.add(mstr);
        }
        return list_dig;
    }
}
