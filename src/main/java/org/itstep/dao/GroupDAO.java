package org.itstep.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.itstep.model.Group;
import org.itstep.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAO{

	@Autowired
	HibernateUtil hiber;

	public Group save(Group group) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.saveOrUpdate(group);

		session.getTransaction().commit();

		session.close();

		if (getOne(group.getName()) != null) {
			return group;
		}

		return null;
	}

	public Group getOne(String name) {

		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Group group = session.get(Group.class, name);

		session.getTransaction().commit();

		session.close();

		return group;
	}

	public void delete(Group group) {
		
		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		session.delete(group);

		session.getTransaction().commit();

		session.close();
	}
	
	public List<Group> findAllByCourse(String course){
		
		Session session = hiber.getSessionFactory().openSession();

		session.getTransaction().begin();

		Query query = session.createNativeQuery("SELECT * FROM GROUPS WHERE COURSE=:cour", Group.class);

		query.setParameter("cour", course);
		
		List<Group> groups = query.getResultList();
		
		session.getTransaction().commit();

		session.close();
		
		return groups;
	}
}
