package exceptions;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactory1 {
	
	
	Configuration config=new Configuration();

	
	
	SessionFactory  sessionFactory = config.configure("hibernate.cfg.xml").buildSessionFactory();
	//SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

}
