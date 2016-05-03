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
public class RegularEx22 {
    public static void main(String[] args) {

        String str = "";
        String str1="";
        str1 = "(\\d+、)?(\\s+)?(（?)?([A-Z]+)?(）?)?(\\s+)?(（*)?([A-Z]+)?(）*)?(\\S+)?(（*)?([A-Z]+)?(）*)?(\\S+)?(?=（)(（)([A-Z]+)?(?=）)(）)(\\S+)?(?= A、)( A、)?(.*)?(?= B、)( B、)?(.*)?";
        str ="5、我馆购买CNKI中国知网数据库包含的子库有（　ABD ）。 A、中国期刊全文数据库                    B、中国年鉴网络出版总库 C、中国重要报纸全文数据库                D、中国重要会议全文数据库 ";
        str="17、我馆自习室的管理规定有（ ABCDEF　）。 A、自习室严禁占座位。空位半小时以上，无论桌上是否有书籍，他人都有权使用该座位。 B、保持室内安静，不得大声喧哗。 C、自习室节假日都开放。 D、暑假有自习室开放，寒假自习室都不开放。 E、每学期末，放在自习室内的个人书籍及物品必须全部带走，图书馆会对自习室进行全面清理维护。 F、读者在自习室内自习，必须做到人走书走。未遵守自习室管理规定的读者造成的书籍、文具等个人物品丢失，责任自负。 ";
        str ="29、联机公共目录查询系统，英文缩写为（ A） ，是图书馆自动化系统面向用户的窗口。 A、OPAC B、IFLAC、OCLCD、NSTL";
        str1 = "(\\d+、)?(（\\s)?([A-Z]+)?(）\\s)?(\\S+)?(（\\s)?([A-Z]+)?(）\\s)?(\\S+)?(（\\s)?([A-Z]+)?(）\\s)?(\\S+)?(?=（ )(（ )([A-Z]+)?(?=） )(） )(\\S+)?(?= A、)( A、)?(.*)?(?= B、)( B、)?(.*)?";
        Pattern pattern = Pattern.compile(str1);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            int groupCount = matcher.groupCount();
            for (int i = 1; i <= groupCount; i++) {
                String s = matcher.group(i);
                System.out.println(s);
            }
        }
    }
}
