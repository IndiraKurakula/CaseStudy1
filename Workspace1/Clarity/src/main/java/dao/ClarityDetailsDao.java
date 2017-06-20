package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.AssociateDetails;
import controllers.ClarityHoursController;
import util.SessionUtil;

public class ClarityDetailsDao {

	static ClarityHoursController clarityHoursController = new ClarityHoursController();
	
//	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	SessionFactory sessionFactory;
	
	//searching with project name
	public List<AssociateDetails> listAssociatesByProject(String name) {
		
		System.out.println("*****///INSIDE CLARITY DETAILS DAO listAssociatesByProject METHOD///*******");

		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		org.hibernate.Transaction t = session.beginTransaction();
		
		System.out.println("*****///SEARCH WITH PROJECT NAME="+name+"///*******");
		
		String hql = "FROM AssociateDetails A  WHERE A.ProjectName LIKE :name";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("name", "%" + name + "%");
		
		List<AssociateDetails> results = query.list();
		
		System.out.println(results.size());
		
		t.commit();// transaction is commited
		
		session.flush();
		
		session.close();
		
		return results;
	}
	
	//searching with associate name
	public List<AssociateDetails> listAssociatesByName(String name) {

		System.out.println("*****///INSIDE CLARITY DETAILS DAO listAssociatesByName METHOD///*******");
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		org.hibernate.Transaction t = session.beginTransaction();
		
		System.out.println("*****///SEARCH WITH ASSOCIATE NAME="+name+"///*******");

		String hql = "FROM AssociateDetails A WHERE A.AssociateName LIKE :name";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("name", "%" + name + "%");
		
		List<AssociateDetails> results = query.list();
		
		System.out.println(results.size());
		
		t.commit();// transaction is commited
		
		session.flush();
		
		session.close();

		return results;
	}
	
	//searching with associate id
	public List<AssociateDetails> listAssociatesById(int aid) {
		
		System.out.println("*****///INSIDE CLARITY DETAILS DAO listAssociatesById METHOD///*******");
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		org.hibernate.Transaction t = session.beginTransaction();
		
		System.out.println("*****///SEARCH WITH ASSOCIATE NAME="+aid+"///*******");

		String hql = "FROM AssociateDetails A  WHERE A.AssociateID =:aid ";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("aid", aid);

		List<AssociateDetails> results = query.list();

		System.out.println(results);
		
		t.commit();// transaction is commited
		
		session.flush();
		
		session.close();

		return results;
	}
	
	//searching with associate id and proj
	public List<AssociateDetails> listAssociatesByIdandProj(int aid, String aproj) {

		System.out.println("*****///INSIDE CLARITY DETAILS DAO listAssociatesByIdandProj METHOD///*******");
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		org.hibernate.Transaction t = session.beginTransaction();
		
		System.out.println("*****///SEARCH WITH ASSOCIATE ID="+aid+" AND PROJ NAME="+aproj+"///*******");
		
		String hql = "FROM AssociateDetails A  WHERE A.AssociateID =:aid AND A.ProjectName =:aproj";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("aid", aid);

		query.setParameter("aproj", aproj);

		List<AssociateDetails> results = query.list();

		t.commit();// transaction is commited
		
		session.flush();
		
		session.close();

		return results;
	}
	
	//searching with associate name and proj
	public List<AssociateDetails> listAssociatesByNameandProj(String aid, String aproj) {
		
		System.out.println("*****///INSIDE CLARITY DETAILS DAO listAssociatesByNameandProj METHOD///*******");
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		org.hibernate.Transaction t = session.beginTransaction();
		
		System.out.println("*****///SEARCH WITH ASSOCIATE NAME="+aid+" AND PROJ NAME="+aproj+"///*******");

		String hql = "FROM AssociateDetails A  WHERE A.AssociateName LIKE :aid AND A.ProjectName =:aproj"; 
		
		Query query = session.createQuery(hql);
		
		query.setParameter("aid", "%" + aid + "%");

		query.setParameter("aproj", aproj);

		List<AssociateDetails> results = query.list();

		t.commit();// transaction is commited
		
		session.flush();
		
		session.close();

		return results;
	}

}
