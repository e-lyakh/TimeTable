package org.itstep.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.itstep.model.Lesson;
import org.itstep.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LessonDAO {

	@Autowired
	HibernateUtil hiber;

	public Lesson save(Lesson lesson) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.saveOrUpdate(lesson);

		session.getTransaction().commit();

		session.close();

		if (getOne(lesson.getId()) != null) {
			return lesson;
		}

		return null;
	}

	public Lesson getOne(Integer lessonId) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Lesson lesson = session.get(Lesson.class, lessonId);

		session.getTransaction().commit();

		session.close();

		return lesson;
	}

	public void delete(Lesson lesson) {
		
		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.delete(lesson);

		session.getTransaction().commit();

		session.close();
	}
	
	public List<Lesson> findAllByStartTime(Long startPeriod, Long endPeriod) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Query query = session.createNativeQuery("SELECT * FROM LESSONS WHERE START_TIME>=:start_period AND START_TIME<=:end_period", Lesson.class);
		
		query.setParameter("start_period", startPeriod);
		
		query.setParameter("end_period", endPeriod);
		
		List<Lesson> lessons = query.getResultList();
				
		session.getTransaction().commit();

		session.close();

		return lessons;
	}
}
