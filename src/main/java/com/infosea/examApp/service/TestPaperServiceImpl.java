package com.infosea.examApp.service;

import com.infosea.examApp.dao.NativeSQLDao;
import com.infosea.examApp.dao.TestPaperDao;
import com.infosea.examApp.pojo.TestPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by infosea on 2016/4/25.
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {
    @Autowired
    TestPaperDao testPaperDao;
    @Autowired
    NativeSQLDao nativeSQLDao;
    @Override
    @Transactional
    public TestPaper findTestPaperById(long id) {
        return testPaperDao.findTestPaperById(id);
    }

    @Override
    public List<TestPaper> findAllTestPaper() {
        return testPaperDao.findAllTestPaper();
    }

    @Override
    public Serializable save(TestPaper testPaper) {
        return testPaperDao.save(testPaper);
    }

    @Override
    public TestPaper findTestPaperByTestPaperIdandUserId(long eid, long uid) {
        return testPaperDao.findTestPaperByTestPaperIdandUserId(eid,uid);
    }

    @Override
    public void del(TestPaper testPaper) {
         testPaperDao.del(testPaper);
    }

    @Override
    public void update(TestPaper testPaper) {
        testPaperDao.update(testPaper);
    }

    /*
   *@description 根据试题id范围 随机生成生成指定书目的试题id
   * @param begin 试题起始ID
   * @param end   试题结束ID
   * @param size  生成试题ID的书目
   * @pageSize    将试题ID分成多少部分 每个部分取对应的个数
    */
    public String produceRandomNumberString(int begin, int end, int size, int pageSize) {
        /*
        begin id起始  81
        end   id结束  115
        pageSize 每页id 5
        size 需要的id数。  10
          */

        Set<Integer> idSet = new HashSet<>();
        StringBuffer stringBuffer = new StringBuffer();
        int pageCount = 0; //页数
        int addSuccess = 1;
        int totalCount = end - begin + 1; //总记录数 70
        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize; // 总页数 14
        } else {
            pageCount = totalCount / pageSize + 1;
        }

        int commonRealPageSize = size / pageCount;
        int remainCount = 0;
        int remainPage = 0;

        Random random = new Random();
        if (size % pageCount != 0) {
            remainCount = size % pageCount; //每页取30/14 个 2*14 =28  剩余要取的id数  2 ；
//          remainPage = random.nextInt(pageCount) + 1;  //从哪一页取剩余没取的书目 ，也就是 那一页多 3 个 id
            remainPage = random.nextInt(pageCount - 1) + 1;  // 不从最后一页取，因为最后一页 id数比其他页少

        }
        for (int j = 1; j <= pageCount; j++) { //pageCount 14
            int begin1 = begin + (j - 1) * pageSize; // j=14时   pageSize = 5 begin=11 begin1 = 13*5+11 = 76
            int end1 = begin + j * pageSize > end ? end : begin + j * pageSize;                   // j=14时   pageSize = 5  begin=11 end1 = 151
            int b = 0;
            for (int i = 0; ; i++) {
                int l = random.nextInt(end1 - begin1) + begin1;
                idSet.add(l);                      // i=0 idset.size =1
                if (idSet.size() == addSuccess) {
                    b++;                                   //1
                    addSuccess++;                          //addsSuccess =2
                    stringBuffer.append(l).append(",");
                    if (b == commonRealPageSize && j != remainPage) {
                        break;
                    }
                    if (b > commonRealPageSize && b < commonRealPageSize + remainCount && j == remainPage) {

                    }
                    if (b == commonRealPageSize + remainCount) {
                        break;
                    }
                } else {

                }

            }
        }
        return stringBuffer.toString();
    }

    public String produceRandomNumberString(int begin, int end, int count) {
        return produceRandomNumberString(begin, end, count, 5);
    }


      //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
    @Rollback(true) //这里设置为false，就让事务不回滚
    public void produceTestPaper() throws Exception {
        List<Integer> chooseIdList = nativeSQLDao.getIds("select id from question where questiontype_id=2 order by id asc");
        List<Integer> chooseMoreIdList = nativeSQLDao.getIds("select id from question where questiontype_id=3 order by id asc");
        List<Integer> checkIdList = nativeSQLDao.getIds("select id from question where questiontype_id=1 order by id asc");
        int j = 0;
        String chooseIdStr = "";
        String chooseMoreStr = "";
        String checkIdStr = "";
        for (int i = 0; i < 10000; i++) {
            chooseIdStr = produceRandomNumberString(chooseIdList.get(0), chooseIdList.get(chooseIdList.size() - 1), 30);
            chooseMoreStr = produceRandomNumberString(chooseMoreIdList.get(0), chooseMoreIdList.get(chooseMoreIdList.size() - 1), 10);
            checkIdStr = produceRandomNumberString(checkIdList.get(0), checkIdList.get(checkIdList.size() - 1), 10);
            TestPaper testPaper = new TestPaper();
            testPaper.setName("试卷 ");//+NumberFormat.formatInteger(++j)
            testPaper.setSglc_ids(chooseIdStr);
            testPaper.setMulc_ids(chooseMoreStr);
            testPaper.setTof_ids(checkIdStr);
            testPaperDao.save(testPaper);
            if (i % 20 == 0)
                testPaperDao.flush();
        }
        testPaperDao.flush();
    }
}
