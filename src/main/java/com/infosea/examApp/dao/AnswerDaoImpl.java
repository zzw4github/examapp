package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Answer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by infosea on 2016/5/19.
 */
@Repository
public class AnswerDaoImpl  implements AnswerDao{
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long save(Answer answer) {
        return (long)this.sessionFactory.getCurrentSession().save(answer);
    }

    @Override
    public Answer query(long userId, long testPaperId, long questionId) {
        return  (Answer)this.sessionFactory.getCurrentSession().createQuery("select answer from Answer answer where answer.user.id=:userId and answer.testPaper.id=:testPaperId and answer.question.id=:questionId")
                .setParameter("userId",userId)
                .setParameter("testPaperId",testPaperId)
                .setParameter("questionId",questionId)
                .uniqueResult();
    }
}
