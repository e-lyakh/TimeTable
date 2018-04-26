package org.itstep.dao;

import org.hibernate.Session;
import org.itstep.model.Subject;
import org.itstep.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDAO {
	
	@Autowired
	HibernateUtil hiber;

	public Subject save(Subject subject) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.saveOrUpdate(subject);

		session.getTransaction().commit();

		session.close();

		if (getOne(subject.getName()) != null) {
			return subject;
		}

		return null;
	}

	public Subject getOne(String name) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Subject subject = session.get(Subject.class, name);

		session.getTransaction().commit();

		session.close();

		return subject;
	}

	public void delete(Subject subject) {
		
		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.delete(subject);

		session.getTransaction().commit();

		session.close();
	}

}
