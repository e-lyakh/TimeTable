package org.itstep.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.itstep.model.Teacher;
import org.itstep.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAO {

	@Autowired
	HibernateUtil hiber;

	public Teacher save(Teacher teacher) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.saveOrUpdate(teacher);

		session.getTransaction().commit();

		session.close();

		if (getOne(teacher.getLogin()) != null) {
			return teacher;
		}

		return null;
	}

	public Teacher getOne(String login) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Teacher teacher = session.get(Teacher.class, login);

		session.getTransaction().commit();

		session.close();

		return teacher;
	}

	public void delete(Teacher teacher) {
		
		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.delete(teacher);

		session.getTransaction().commit();

		session.close();
	}

	public List<Teacher> findAllBySubject(String subName) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Query query = session.createNativeQuery("SELECT * FROM TEACHERS WHERE SUBJECT_SUB_NAME=:sub_name", Teacher.class);
		
		query.setParameter("sub_name", subName);
		
		List<Teacher> teachers = query.getResultList();
				
		session.getTransaction().commit();

		session.close();

		return teachers;
	}
}
