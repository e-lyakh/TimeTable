package org.itstep.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.itstep.model.Student;
import org.itstep.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {

	@Autowired
	HibernateUtil hiber;

	public Student save(Student student) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.saveOrUpdate(student);

		session.getTransaction().commit();

		session.close();

		if (getOne(student.getLogin()) != null) {
			return student;
		}

		return null;
	}

	public Student getOne(String login) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Student student = session.get(Student.class, login);

		session.getTransaction().commit();

		session.close();

		return student;
	}

	public void delete(Student student) {
		
		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.delete(student);

		session.getTransaction().commit();

		session.close();
	}
	
	public List<Student> findAllByGroup(String groupName) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Query query = session.createNativeQuery("SELECT * FROM STUDENTS WHERE GROUP_NAME=:name", Student.class);
		
		query.setParameter("name", groupName);
		
		List<Student> students = query.getResultList();
				
		session.getTransaction().commit();

		session.close();

		return students;
	}
}
