package org.itstep.util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.itstep.model.Group;
import org.itstep.model.Lesson;
import org.itstep.model.Student;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {

	private final SessionFactory sessionFactory = configureSessionFactory();

	/**
	 * Create SessionFactory
	 * 
	 * @return {@link SessionFactory}
	 * @throws HibernateException
	 */
	private SessionFactory configureSessionFactory() throws HibernateException {

		Configuration configuration = new Configuration();
		
		configuration.addAnnotatedClass(Group.class);
		configuration.addAnnotatedClass(Lesson.class);
		configuration.addAnnotatedClass(Subject.class);
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Teacher.class);

		return configuration.buildSessionFactory();

	}

	/**
	 * Get SessionFactory
	 * 
	 * @return {@link SessionFactory}
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}