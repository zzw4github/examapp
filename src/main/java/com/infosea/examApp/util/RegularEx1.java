/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosea.examApp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author infosea
 */
public class RegularEx1 {
    //    匹配 多余4个选项 ，括号在前面，在后面，多个括号 正确答案前后                       可以带括号或者不带括号
//    public static final String str5 =  "(\\d+、)?(\\s*)?(（?)?(\\s+\\S+\\s+)?(）?)?(\\S+\\s?\\S*)*(（)(\\s*+\\S+\\s*+)?(?=）)(）)?(\\s?\\S*\\s?)?(（?)?(\\s+\\S+\\s+)?(）?)?(\\s?\\S+\\s?)?(（?)?(\\s+\\S+\\s+)?(）?)?(\\s?\\S+\\s?)?(?=A、)(A、)?(.*)?(?=B、)(B、)?(.*)?";
    public static final String str5 = "(\\d+、)?(\\S+\\s?)?(（)(\\s?\\S+\\s?)?(?=）)(）)?(\\s?\\S*\\s?)?(?=A、)(A、)?(.*)?(?=B、)(B、)?(.*)?";

    public static String str6 = "\\d+、";
    public static Pattern pattern;
    public static Pattern pattern1 = Pattern.compile(str6);
    public static Pattern pattern2 = Pattern.compile(str5);
    public static Matcher coreMatcher;
    StringBuilder questionBuilder = new StringBuilder();
    StringBuilder optionBuilder = new StringBuilder();

    public boolean parse4OptionAnd1pairbracket(String str) {
        Matcher matcher = pattern1.matcher(str);
        if (matcher.matches()) {
            coreMatcher = matcher;
            return true;
        } else {
            return false;
        }
    }

    public static List<String> findDigital(String str) {
        Matcher matcher = pattern1.matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }

        return list;
    }

    public boolean parse4OptionAnd3pairbracket(String str) {
        Matcher matcher = pattern2.matcher(str);
        if (matcher.matches()) {
            coreMatcher = matcher;
            return true;
        } else {
            return false;
        }
    }

    public boolean parseString(String str) {
        if (parse4OptionAnd3pairbracket(str)) {

        } else if (parse4OptionAnd1pairbracket(str)) {

        } else {
            return false;
        }
        return true;
    }

    // 将问题的 题干 和 选项 分别存储
    public Map<String, String> getQuestionFromRegex(String str) {
        Map<String, String> map = new HashMap<>();
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
                    if (s.equals("A、")) {
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
            map.put("option", optionBuilder.toString());
        } else {
            System.out.println(str);
        }
        return map;
    }

    /*

     */
    public Map<String, String> getQuestionFromUniformRegex(String str) {
        Map<String, String> map = new HashMap<>();
        //将所有的空格去掉
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            matcher.replaceAll("");
        }
        //将非中文括号换成 中文括号
        StringBuffer values = new StringBuffer();
        pattern = Pattern.compile("\\(+");
        matcher = pattern.matcher(str);
        while (matcher.find()) {
            matcher.replaceAll("（");
        }
        pattern = Pattern.compile("\\)+");
        matcher = pattern.matcher(str);
        while (matcher.find()) {
            matcher.replaceAll("）");
        }
        //将所有的 非（字母+）的括号 转换成 【】括号
        //将所有的 （字母+）的括号 前面加上空格
        pattern = Pattern.compile("(（)(?=[A-Z])");
        while (matcher.find()) {
            String mstr = matcher.group();
            matcher = matcher.appendReplacement(values, " " + mstr + " ");
        }
        matcher.appendTail(values);
        pattern = Pattern.compile("(?<=[A-Z])(）)");
        while (matcher.find()) {
            String mstr = matcher.group();
            matcher = matcher.appendReplacement(values, " " + mstr + " ");
        }
        //将所有的在str最前面的数字的后面的。号转换成、
        pattern = Pattern.compile("^(?<=\\d+)(\\.)");
        matcher = pattern.matcher(str);
        while (matcher.find()) {
            matcher.replaceAll("、");
        }
        matcher.appendTail(values);
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
                    if (s.equals("A、")) {
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
            map.put("option", optionBuilder.toString());
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
        String str1 = "(\\S+)*(\\S)(\\s+\\S\\s+)(\\S)?(\\s*\\S*\\s*)?(A、)?(.*)?(?=B、)(B、)?(.*)?(?=C、)(C、)?(.*)?(?=D、)(D、)?(.*)?";
        String str2 = "(\\S+)*(\\S)(\\s+\\S\\s+)(\\S)?(\\s*\\S*\\s*)?(\\S)(\\s+\\S\\s+)(\\S)?(\\s*\\S*\\s*)?(\\S)(\\s+\\S\\s+)(\\S)?(\\s*\\S*\\s?)?(A、)?(.*)?(?=B、)(B、)?(.*)?(?=C、)(C、)?(.*)?(?=D、)(D、)?(.*)?";
        str2 = "(（?)?(\\s+\\S\\s+)?(）?)?(\\S+)*(\\S)(\\s+\\S\\s+)(\\S)?(\\s*\\S*\\s*)?(\\S)(\\s+\\S\\s+)(\\S)?(\\s*\\S*\\s*)?(\\S)(\\s+\\S\\s+)(\\S)?(\\s*\\S*\\s?)?(A、)?(.*)?(?=B、)(B、)?(.*)?(?=C、)(C、)?(.*)?(?=D、)(D、)?(.*)?";
        str = " （ ABC ）我校图书馆现已开放（ ABC ）个借阅区， （ ABC ）个自习室；（ ABC ）个阅览室。 A、8；8；4      B、9；8；3       C、10；9；4       D、8；8；3   E、8；8；3";

        str = "我校图书馆网站的内网网址是（A）。A、http://10.1.1.1/tsg              B、http://www.huhst.edu.cn/tsg C、http://www.huhst.edu.com      D、http://www. huhst.edu.cn ";
        RegularEx1 regularEx1 = new RegularEx1();
        if (regularEx1.parseString(str)) {
            Map<String, String> map = regularEx1.getQuestionFromRegex(str);
        }
    }
}
