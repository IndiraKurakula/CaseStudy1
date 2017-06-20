package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.transaction.annotation.Transactional;

import beans.UserDetails;
import util.SessionUtil;

public class RolesDao

{

	//SessionFactory sessionFactory2 = new  Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 
	SessionFactory sessionFactory;
	@SuppressWarnings("deprecation")
	@Transactional

	public void save(UserDetails userdetails, String userid, String newrole) {

		System.out.println("inside user DAO");
		System.out.println("Entered user id is" + userid);
		System.out.println("Entered new role is" + newrole);
		int uservalue = 0;

		if (newrole.equals("Lead User")) {
			uservalue = 1;
		} else if (newrole.equals("Super User")) {
			uservalue = 2;
		}
		System.out.println("User details brought:" + userdetails.getAssociateID() + "," + userdetails.getUserRole()
				+ "," + userdetails.getUserValue());

		UserDetails userdetails1 = new UserDetails();
		userdetails1.setAssociateID(Integer.parseInt(userid));
		userdetails1.setUserRole(newrole);
		userdetails1.setUserValue(uservalue);

		sessionFactory = SessionUtil.getSessionFactory();	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(userdetails1);

		/*
		 * Integer userid1 = Integer.parseInt(userid); Query query =
		 * session.createSQLQuery(
		 * "UPDATE user_details SET UserRole=:role WHERE AssociateID=:id");
		 * query.setParameter("role", newrole); query.setParameter("id",
		 * userid); query.executeUpdate();
		 */
		System.out.println("successfully saved");
		tx.commit();
		session.close();
	}

	public String validateUserDetails(String userid, String role)
	{
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String userresult="not existing";
		
		String hql1 = "from UserDetails ud where ud.AssociateID=:userId and ud.UserRole=:role";
		Query query = session.createQuery(hql1);
		query.setString("userId", userid);
		query.setString("role", role);
		List<UserDetails> user = query.list();
		/*int usersize=user.size();
		System.out.println("user size::"+usersize);*/
		
		if(user.size()>0)
		{
			UserDetails user1=user.get(0);
		System.out.println("userdetials from db::::"+user1.getAssociateID()+","+user1.getUserRole()+","+user1.getUserValue());
		userresult="existing";
		}
		tx.commit();
		session.close();
		return userresult;
	}
}