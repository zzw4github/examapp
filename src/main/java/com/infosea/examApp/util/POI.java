package com.infosea.examApp.util;

import com.infosea.examApp.pojo.Question;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/25.
 */
public class POI {
    int flagCheck = 0;
    int flagChoose = 0;
    int flagChooseMore = 0;
    int x = 0;
    int count = 0;
// 将 doc 文件 按题型 放到list中
    public Map<String, List<String>> testReadByExtractor(File file) throws Exception {
        Map<String, List<String>> questionMap = new HashMap<>();
        List<String> listChoose = new ArrayList<String>();
        List<String> listChooseMore = new ArrayList<String>();
        List<String> listCheck = new ArrayList<String>();
        InputStream is = new FileInputStream(file);
        WordExtractor extractor = new WordExtractor(is);
        String paraTexts[] = extractor.getParagraphText();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < paraTexts.length; i++) {
            if (i <= 3) continue;
            String s = paraTexts[i];
            if (s.contains("多选一，每题2分")) {
                flagChoose = 1;
            } else if (paraTexts[i].contains("判断题（二选一，每题1分")) {
                flagCheck = 1;
                flagChoose = -1;
            } else if (paraTexts[i].contains("每题3分，多选少选不给分")) {
                flagChooseMore = 1;
                flagCheck = -1;
            } else {
                if (flagChoose > 0) {
                    flagChoose++;
                }
                if (flagCheck > 0) {
                    flagCheck++;
                }
                if (flagChooseMore > 0) {
                    flagChooseMore++;
                }
                if (s == null || s.trim().equals("")) continue;
                s = s.replace("\r\n", " ");

                String s_judge = s.trim().substring(0, 3);
                if (!s_judge.substring(2).equals("、"))
                    s_judge = s.trim().substring(0, 2);
                if (s_judge.matches("\\d*、")) {
                    x++;
                    if (x == 1) {
                    } else {
                        if (flagChoose > 1 || (flagCheck == 2)) {
                            putIntoList(listChoose, stringBuilder);
                        } else if ((flagCheck > 2) || (flagChooseMore == 2)) {
                            putIntoList(listCheck, stringBuilder);
                        } else if (flagChooseMore > 2) {
                            putIntoList(listChooseMore, stringBuilder);
                        }
                        stringBuilder.setLength(0);
                    }
                }
                stringBuilder.append(s);
            }
        }
        putIntoList(listChooseMore, stringBuilder);
        stringBuilder.setLength(0);
        this.closeStream(is);
        questionMap.put("choose", listChoose);
        questionMap.put("check", listCheck);
        questionMap.put("choosemore", listChooseMore);
        return questionMap;
    }

    public void putIntoList(List<String> questionlist, StringBuilder questionString) {
        String outString = questionString.toString();
        List<String> l = RegularEx.findDigital(outString);
        int i = 0;
        String[] strs = outString.split("\\d+、");
        for (String ss : strs) {
            if (ss.trim().equals("")) continue;
            else
            //没有统一格式
                questionlist.add(l.get(i++) + ss);
        }
    }

    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        POI poi = new POI();
        try {
            Map<String, List<String>> questionMap = poi.testReadByExtractor(new File("湖南人文科技学院图书馆新生入馆教育考试题库.doc"));
            for (Map.Entry<String, List<String>> entry : questionMap.entrySet()) {
                String kind = entry.getKey();
                List<String> val = entry.getValue();
                System.out.println("kind" + kind + " size " + val.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}