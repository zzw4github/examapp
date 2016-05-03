package com.infosea.examApp.util;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by infosea on 2016/4/25.
 */
public class PoiUtil {
    public void testReadByExtractor() throws Exception {
        InputStream is = new FileInputStream("湖南人文科技学院图书馆新生入馆教育考试题库.doc");
        WordExtractor extractor = new WordExtractor(is);
        //输出word文档所有的文本
     //   System.out.println(extractor.getText());
//        System.out.println(extractor.getTextFromPieces());
        //输出页眉的内容
     //   System.out.println("页眉：" + extractor.getHeaderText());
        //输出页脚的内容
     //   System.out.println("页脚：" + extractor.getFooterText());
        //输出当前word文档的元数据信息，包括作者、文档的修改时间等。
    //    System.out.println(extractor.getMetadataTextExtractor().getText());
        //获取各个段落的文本
        String paraTexts[] = extractor.getParagraphText();
        for (int i=0; i<paraTexts.length; i++) {
            System.out.println("Paragraph " + (i+1) + " : " + paraTexts[i]);
        }
        //输出当前word的一些信息
       // printInfo(extractor.getSummaryInformation());
        //输出当前word的一些信息
      //  this.printInfo(extractor.getDocSummaryInformation());
        this.closeStream(is);
    }

    /**
     * 输出SummaryInfomation
     * @param info
     */
    private void printInfo(SummaryInformation info) {
        //作者
        System.out.println(info.getAuthor());
        //字符统计
        System.out.println(info.getCharCount());
        //页数
        System.out.println(info.getPageCount());
        //标题
        System.out.println(info.getTitle());
        //主题
        System.out.println(info.getSubject());
    }

    /**
     * 输出DocumentSummaryInfomation
     * @param info
     */
    private void printInfo(DocumentSummaryInformation info) {
        //分类
        System.out.println(info.getCategory());
        //公司
        System.out.println(info.getCompany());
    }

    /**
     * 关闭输入流
     * @param is
     */
    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
public  static  void  main(String[] args){
    PoiUtil poiUtil = new PoiUtil();
    try {
        poiUtil.testReadByExtractor();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}