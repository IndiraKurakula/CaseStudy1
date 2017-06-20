package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import beans.AssociateDetails;
import beans.ClarityHours;
import controllers.ClarityHoursController;
import util.SessionUtil;

public class AssociateDao

{
	static ClarityHoursController clarityHoursController = new ClarityHoursController();
//	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	SessionFactory sessionFactory;
	// Configuration cfg = new Configuration();

	// Configuration cfg1 = new Configuration();
	
	
	
	//To List all the Associate details in the Super User Clarity Details page
	public List<AssociateDetails> listAssociates() {

		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("To get the details from the db");
		String hql = "FROM AssociateDetails";
		Query query = session.createQuery(hql);
		System.out.println("fetched associate details from db");
		List<AssociateDetails> results = query.list();
		System.out.println(results.size());
		t.commit();// transaction is commited
		//session.flush();
		session.close();
		
		return results;
	}
	
	public List<String> listProjects() {

		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("To get the details from the db");
		String hql = "select DISTINCT ProjectName FROM AssociateDetails";
		Query query = session.createQuery(hql);
		System.out.println("fetched associate details from db");
		List<String> results = query.list();
		System.out.println(results);
		t.commit();// transaction is commited
		//session.flush();
		session.close();

		return results;
	}
	
public List<AssociateDetails> listAssociatesBySearch(int id, String proj, String month) {
	
	System.out.println("*****///Search Begins Here///*******");
	
	ClarityDetailsDao clarityDetailsDao=new ClarityDetailsDao();
	
	
	
	System.out.println(id+"  "+proj+"  "+month);

	//month=getmonth();
	
	System.out.println("Month from system "+month);

	List<AssociateDetails> results = null ;
	if(id==0){
		if(!proj.equals("select")){
			
				System.out.println("in if if else ladder");
				results = clarityDetailsDao.listAssociatesByProject(proj);
		
		}
	}
	else{
		if(!proj.equals("select")){
		
				System.out.println("in else if else ladder");
				results = clarityDetailsDao.listAssociatesByIdandProj(id, proj);
		
		}
		else{
			results = clarityDetailsDao.listAssociatesById(id);
		}
		
	}
	//results = clarityDetailsDao.listAssociatesByProject(proj);
	/*if(()&&(proj!="select")){
		results = clarityDetailsDao.listAssociatesByProject(proj);
	}
	 else if((id!=0)&&(proj!="select")){
		//getAssociatesByIdANDProj(id,proj);
	} */
	 /* else if((id!=0)&&(proj=="select")&&(month!="select")){
			//getAssociatesByIdANDMonth(id,month);
		} 
	 else if((id==0)&&(proj!="select")&&(month!="select")){
			//getAssociatesByProjANDMonth(proj,month);
		} 
	 else if((id!=0)&&(proj=="select")&&(month=="select")){
		 //getAssociatesBySearch(id);
		} 
	else if((id==0)&&(proj!="select")&&(month=="select")){
		List<AssociateDetails> results = null ;
	results = clarityDetailsDao.listAssociatesByProject(proj);
		} 
	 else if((id==0)&&(proj=="select")&&(month!="select")){
		 //MonthSearch(month);
		} 
	 else if((id==0)&&(proj=="select")&&(month=="select")){
		// getAllAssociates();
		}*/
	

		
	return results;
	}
	
public List<AssociateDetails> listAssociatesBySearch(String id, String proj, String month) {

System.out.println("*****///Search Begins Here///*******");
	
	ClarityDetailsDao clarityDetailsDao=new ClarityDetailsDao();
	
	
	
	System.out.println(id+"  "+proj+"  "+month);

	//month=getmonth();
	
	System.out.println("Month from system "+month);

	List<AssociateDetails> results = null ;
System.out.println("SYSOUT: After list declared in dao");
	


if((id.equals(null))||(id.equals(""))){ 
	
	System.out.println("SYSOUT: inside first if");
	
		if(!proj.equals("select")){
			
			System.out.println("SYSOUT: inside second if");
//			if(month.equals("select")){
				
				System.out.println("SYSOUT: in if loop");
				results = clarityDetailsDao.listAssociatesByProject(proj);
			/*}*/
		}
	}
	else{
		if(!proj.equals("select")){
			
				System.out.println("in if else ladder");
				results = clarityDetailsDao.listAssociatesByNameandProj(id, proj);

		}
		else{
			System.out.println("SYSOUT: in else loop");
			results = clarityDetailsDao.listAssociatesByName(id);
		}
		
	}


System.out.println(results);
	return results;
		 
	 /* else if((id!=0)&&(proj=="select")&&(month!="select")){
			//getAssociatesByIdANDMonth(id,month);
		} 
	 else if((id==0)&&(proj!="select")&&(month!="select")){
			//getAssociatesByProjANDMonth(proj,month);
		} 
	 else if((id!=0)&&(proj=="select")&&(month=="select")){
		 //getAssociatesBySearch(id);
		} 
	else if((id==0)&&(proj!="select")&&(month=="select")){
		List<AssociateDetails> results = null ;
	results = clarityDetailsDao.listAssociatesByProject(proj);
		} 
	 else if((id==0)&&(proj=="select")&&(month!="select")){
		 //MonthSearch(month);
		} 
	 else if((id==0)&&(proj=="select")&&(month=="select")){
		// getAllAssociates();
		}*/
	

		
	
}

public static String getmonth() {
    Calendar now = Calendar.getInstance();
    // 
    System.out.println("Current Year is : " + now.get(Calendar.YEAR));
    // month start from 0 to 11
    System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
    System.out.println("Current Date is : " + now.get(Calendar.DATE));
    String month=Integer.toString((now.get(Calendar.MONTH) + 1));
    return month;
  }

public static String getyear() {
    Calendar now = Calendar.getInstance();
    // 
    System.out.println("Current Year is : " + now.get(Calendar.YEAR));
    // month start from 0 to 11
    System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
    System.out.println("Current Date is : " + now.get(Calendar.DATE));
    String year=Integer.toString(now.get(Calendar.YEAR));
    return year;
  }


public String validateAssociateIDsuper(String userid) {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    Session session = cfg.buildSessionFactory().openSession();
    Transaction tx = session.beginTransaction();


    Integer userid1 = Integer.parseInt(userid);
    Query query1 = session.createSQLQuery("SELECT AssociateId from user_details ud where ud.UserRole LIKE '%super%'");

    List<Integer> associateids = query1.list();
    System.out.println("size of list" + associateids.size());

    System.out.println("hai out forloop");
    for (Integer associatedetails1 : associateids) {
                    System.out.println("hai in forloop");
                    System.out.println("ass id: " + associatedetails1);
    }
    String result = null;
    if (associateids.contains(userid1)) {
                    result = "super";

    } else {
                    result = "notsuper";
    }
    System.out.println("super user or not" + result);
    tx.commit();
    session.close();
    return result;

}



/*	public List<AssociateDetails> listAssociatesByProject(String name) {

		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("SYSOUT: To get the details from the db in list associates by serach dao");
		 String hql = "FROM AssociateDetails"; 
		String hql = "FROM AssociateDetails A  WHERE A.ProjectName LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		System.out.println("SYSOUT: vfghymju");
		List<AssociateDetails> results = query.list();
		System.out.println(results.size());
		System.out.println(name);
		t.commit();// transaction is commited
		session.close();

		return results;
	}

	public List<AssociateDetails> listAssociatesByMonth(String name) {

		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("SYSOUT: To get the details from the db in list associates by serach dao----" + name);
		 String hql = "FROM AssociateDetails"; 
		
		 * String hql =
		 * "select A.AssociateID,A.HCSCID,A.AssociateName,A.ProjectName,A.Location,A.ClarityAccess,A.HCSCEmailID,A.PhoneNumber,A.HCSCJoiningDate,A.LastClarityUpdateDate,A.Rate FROM AssociateDetails AS A INNER JOIN ClarityHours AS H WHERE H.Month LIKE :name"
		 * ;
		  // String hql = "select
			// A.AssociateID,A.HCSCID,A.AssociateName,A.ProjectName,A.Location,A.ClarityAccess,A.HCSCEmailID,A.PhoneNumber,A.HCSCJoiningDate,A.LastClarityUpdateDate,A.Rate
			// FROM AssociateDetails AS A ,ClarityHours AS H WHERE
			// A.AssociateID=H.AssociateID AND H.Month LIKE :name";
		
		 * String hql =
		 * " FROM AssociateDetails A ,ClarityHours H WHERE A.AssociateID=H.AssociateID AND H.Month=:name"
		 * ;
		 
		String hql1 = "FROM ClarityHours WHERE Month=:name";
		Query q1 = session.createQuery(hql1);
		q1.setParameter("name", name);
		List<ClarityHours> clar = q1.list();
		int count = clar.size();
		List<AssociateDetails> results = null;

		String hql = "From AssociateDetails";
		Query q2 = session.createQuery(hql);

		List<AssociateDetails> results1 = q2.list();
		int listcount = results1.size();
		List<AssociateDetails> results2 = null;
		String hql2 = null;
		Query query = null;
		for (int i = 0; i < count; i++) {

			hql2 = "From AssociateDetails where AssociateID=:NUM";

			query = session.createQuery(hql2);
			query.setParameter("NUM", clar.get(i).getAssociateID());
			System.out.println(clar.get(i).getAssociateID());

			results = query.list();

			results1.addAll(results);

		}

		System.out.println("SYSOUT: fetched associate details from db in list associates by search dao");

		System.out.println(query.list());

		System.out.println(results.size());
		System.out.println(results);
		t.commit();// transaction is commited
		session.close();
		System.out.println("sysout: ------------METHOD OVER");
		return results1.subList(listcount - 1, results1.size() - 1);
	}
	
	
	public List<AssociateDetails> listAssociatesBySearch(String name) {

		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("SYSOUT: To get the details from the db in list associates by serach dao");
		 String hql = "FROM AssociateDetails"; 
		String hql = "FROM AssociateDetails A WHERE A.AssociateName LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		System.out.println("SYSOUT: fetched associate details from db in list associates by serach dao");
		List<AssociateDetails> results = query.list();
		System.out.println(results.size());
		t.commit();// transaction is commited
		session.close();

		return results;
	}

	public List<AssociateDetails> listAssociatesByAllValues(int aid, String aproj, String amonth) {

		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("-------------------search by all values-----------------------");
		 Integer userid1 = Integer.parseInt(aid); 
		System.out.println(aid);
		String hql1 = "FROM ClarityHours WHERE Month=:amonth AND AssociateID=:aid";

		Query q1 = session.createQuery(hql1);
		q1.setParameter("amonth", amonth);
		q1.setParameter("aid", aid);
		List<ClarityHours> clar = q1.list();

		List<AssociateDetails> results = null;

		if (clar.size() != 0) {
			String hql2 = "From AssociateDetails AS A where A.AssociateID =:aid AND A.ProjectName =:aproj";
			Query query = session.createQuery(hql2);

			query.setParameter("aid", aid);
			query.setParameter("aproj", aproj);
			System.out.println("AID" + aid);

			results = query.list();

			System.out.println("AID2" + aid);
			// results1.addAll(results);

			System.out.println("AID3" + aid);

		}

		System.out.println(results.size());
		System.out.println(results);
		t.commit();// transaction is commited
		session.close();
		System.out.println("sysout: ------------METHOD OVER");
		return results;

	}
	*/
	//to search clarity details using emp id 
	public AssociateDetails[] associatesById(String search) {

		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("To get the details from the db");
		System.out.println("id===="+search);
	
	
			
	SQLQuery query = null;
		Integer userid1 = Integer.parseInt(search);
		query = session.createSQLQuery("CALL autoPopulateGeneralLeadUser(:associatid)");

		 query.addEntity(AssociateDetails.class);
		
		
		query.setParameter("associatid", userid1);
		System.out.println("fetched associate details from db"+query.list().get(0));
		
		AssociateDetails associatedetails[]=new AssociateDetails[query.list().size()];

		/*for (AssociateDetails[] ad : list) {
			System.out.println("user name from general----" + ad.getAssociateName());
			associatedetails = ad;
		}
*/
		for(int i=0;i<associatedetails.length;i++){
			associatedetails[i]=(AssociateDetails) query.list().get(i);
			System.out.println("asso deta"+associatedetails[i]);
		}
		t.commit();// transaction is commited
	//	session.flush();
		session.close();
		return associatedetails;
		//return results;
	}
	
	//To edit the details in the clarity Details page
		
	public List<AssociateDetails> edit(String id) {

	
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();

		String hql = "FROM AssociateDetails where id="+id;
		Query query = session.createQuery(hql);
		List<AssociateDetails> results = query.list();
		System.out.println(results.size());
		t.commit();// transaction is commited
	//	session.flush();
		session.close();
		
		return results;
	}
	
	
	
	public Boolean update(AssociateDetails associatedetails) {
		

		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();
		// creating session object
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		session.update(associatedetails);// persisting the object
		t.commit();// transaction is commited
	//	session.flush();
		session.close();

		System.out.println("successfully saved");
		return true;
	}
	public String validateAssociateID(String userid) {
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		/*
		 * String hql = "select l.AssociateID from AssociateDetails l"; Query
		 * query = session.createQuery(hql); List<Integer> eids = query.list();
		 * List<Integer> associateids = query.list(); System.out.println(
		 * "size of list"+associateids.size());
		 * 
		 * 
		 * System.out.println("hai out forloop"); for (Integer associatedetails1
		 * : associateids) { System.out.println("hai in forloop");
		 * System.out.println("ass id: "+associatedetails1); }
		 */

		Integer userid1 = Integer.parseInt(userid);
		Query query1 = session.createSQLQuery("CALL validateAssociateID()");

		List<Integer> associateids = query1.list();
		System.out.println("size of list" + associateids.size());

		System.out.println("hai out forloop");
		for (Integer associatedetails1 : associateids) {
			System.out.println("hai in forloop");
			System.out.println("ass id: " + associatedetails1);
		}
		String result = null;
		if (associateids.contains(userid1)) {
			result = "valid";

		} else {
			result = "invalid";
		}
		System.out.println("list has or not::" + result);
		tx.commit();
	//	session.flush();
		session.close();
		return result;

	}

	public LinkedHashMap<AssociateDetails, ClarityHours> autoPopulateSuperUser(String userid) {
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("After of autoPopulateSuperUser");
		LinkedHashMap<AssociateDetails, ClarityHours> viewresult = new LinkedHashMap<AssociateDetails, ClarityHours>();

		SQLQuery query = null;
		Integer userid1 = Integer.parseInt(userid);
		query = session.createSQLQuery("CALL autoPopulateSuperUser(:associatid)");
		query.addEntity(AssociateDetails.class);
		query.addEntity(ClarityHours.class);
		query.setParameter("associatid", userid1);
		List list = query.list();

		AssociateDetails ad = new AssociateDetails();
		ClarityHours ch = new ClarityHours();

		for (int i = 0; i < list.size(); i++) {
			ad = (AssociateDetails) (((Object[]) list.get(i))[0]);
			ch = (ClarityHours) (((Object[]) list.get(i))[1]);
			viewresult.put(ad, ch);
		}
		System.out.println("AssociateName:---" + ad.getAssociateName());
		System.out.println("HCSCID:---" + ad.getHCSCID());
		System.out.println("AssociateID:---" + ad.getAssociateID());
		tx.commit();
		//session.flush();
		session.close();
		return viewresult;
	}

	public AssociateDetails autoPopulateGeneralLeadUser(String userid) {
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SQLQuery query = null;
		Integer userid1 = Integer.parseInt(userid);
		query = session.createSQLQuery("CALL autoPopulateGeneralLeadUser(:associatid)");
		query.addEntity(AssociateDetails.class);
		query.setParameter("associatid", userid1);
		List<AssociateDetails> list = query.list();

		AssociateDetails associatedetails = null;

		for (AssociateDetails ad : list) {
			System.out.println("user name from general----" + ad.getAssociateName());
			associatedetails = ad;
		}
		tx.commit();
		
		session.close();
		return associatedetails;
	}
	
	public int updateAssociateDetailsForID(AssociateDetails associate)
	{sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SQLQuery query = null;
		//Integer userid1 = Integer.parseInt(userid);
		query = session.createSQLQuery("CALL updateAssociateDetailsByID(:associatid,:hcscid,:associatename"+
		":projectname,:location,:clarityaccess,"+":hcscemailid,:phonenumber,:hcscjoiningdate,:lastclarityupdatedate)");
		query.addEntity(AssociateDetails.class);
		//query.setParameter("associatid", userid1);
		
	tx.commit();
		
		session.close();
		return 0;
	}
	
	/*Insertion trail*/
//	public void registerEmployee() {
//
//		
//		Configuration cfg1 = new Configuration();
//		cfg1.configure("hibernate.cfg.xml");
//		Session session = cfg1.buildSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//		AssociateDetails ad= new AssociateDetails(561934,"142","hai","HCSCC","Chennai","y4es","hhhdf4g@gmail","00064464","2016-01-02","2016-01-01",42, "6253", null, null, 0, null, 0, 0, null);
//		 session.save(ad);
//		tx.commit();
//		session.close();
//	}

/*	public List<AssociateDetails> listAssociatesByIdandProj(String aid, String aproj) {

		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		int id = Integer.parseInt(aid);

		System.out.println("------------------search by id and proj---------------");
		 String hql = "FROM AssociateDetails"; 
		String hql = "FROM AssociateDetails A  WHERE A.AssociateID =:aid AND A.ProjectName =:aproj";
		Query query = session.createQuery(hql);
		query.setParameter("aid", id);

		query.setParameter("aproj", aproj);
		System.out.println("SYSOUT: vfghymju");
		List<AssociateDetails> results = query.list();

		t.commit();// transaction is commited
		session.close();

		return results;
	}

	public List<AssociateDetails> listAssociatesByIdANDMonth(Integer aid, String amonth) {

		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("-------------------search by all values-----------------------");
		 Integer userid1 = Integer.parseInt(aid); 
		System.out.println(aid);
		String hql1 = "FROM ClarityHours WHERE Month=:amonth AND AssociateID=:aid";

		Query q1 = session.createQuery(hql1);
		q1.setParameter("amonth", amonth);
		q1.setParameter("aid", aid);
		List<ClarityHours> clar = q1.list();

		List<AssociateDetails> results = null;

		if (clar.size() != 0) {
			String hql2 = "From AssociateDetails AS A where A.AssociateID =:aid";
			Query query = session.createQuery(hql2);

			query.setParameter("aid", aid);

			System.out.println("AID" + aid);

			results = query.list();

			System.out.println("AID2" + aid);
			// results1.addAll(results);

			System.out.println("AID3" + aid);

		}

		System.out.println(results.size());
		System.out.println(results);
		t.commit();// transaction is commited
		session.close();
		System.out.println("sysout: ------------METHOD OVER");
		return results;
	}

	public List<AssociateDetails> listAssociatesByProjANDMonth(String aproj, String amonth) {

		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("--------------search by month & proj--------------------");

		String hql1 = "FROM ClarityHours WHERE Month=:amonth";
		Query q1 = session.createQuery(hql1);
		q1.setParameter("amonth", amonth);

		List<ClarityHours> clar = q1.list();
		int count = clar.size();
		List<AssociateDetails> results = null;

		String hql = "From AssociateDetails";
		Query q2 = session.createQuery(hql);

		List<AssociateDetails> results1 = q2.list();
		int listcount = results1.size();
		List<AssociateDetails> results2 = null;
		String hql2 = null;
		Query query = null;
		for (int i = 0; i < count; i++) {

			hql2 = "From AssociateDetails where AssociateID=:NUM AND ProjectName=:aproj";

			query = session.createQuery(hql2);
			query.setParameter("NUM", clar.get(i).getAssociateID());
			query.setParameter("aproj", aproj);

			results = query.list();

			results1.addAll(results);

		}

		System.out.println("SYSOUT: fetched associate details from db in list associates by search dao");

		System.out.println(query.list());

		System.out.println(results.size());
		System.out.println(results);
		t.commit();// transaction is commited
		session.close();
		System.out.println("sysout: ------------METHOD OVER");
		return results1.subList(listcount - 1, results1.size() - 1);

	}

	public List<AssociateDetails> listAssociatesById(String aid) {
		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		int id = Integer.parseInt(aid);

		System.out.println("------------------search by id --------------");
		 String hql = "FROM AssociateDetails"; 
		String hql = "FROM AssociateDetails A  WHERE A.AssociateID =:aid ";
		Query query = session.createQuery(hql);
		query.setParameter("aid", id);

		System.out.println("SYSOUT: vfghymju");
		List<AssociateDetails> results = query.list();

		System.out.println(results);
		t.commit();// transaction is commited
		session.close();

		return results;
	}

	public List<AssociateDetails> listAssociatesByNameandProj(String aid, String aproj) {
		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();

		System.out.println("------------------search by name and proj---------------");
		 String hql = "FROM AssociateDetails"; 
		String hql = "FROM AssociateDetails A  WHERE A.AssociateName LIKE :aid AND A.ProjectName =:aproj";
		Query query = session.createQuery(hql);
		query.setParameter("aid", "%" + aid + "%");

		query.setParameter("aproj", aproj);
		System.out.println("SYSOUT: vfghymju");
		List<AssociateDetails> results = query.list();

		t.commit();// transaction is commited
		session.close();

		return results;
	}

	public List<AssociateDetails> listAssociatesByNameandMonth(String aid, String amonth) {

		
		// creating seession factory object
		//SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = sessionFactory.openSession();

		// creating transaction object
		org.hibernate.Transaction t = session.beginTransaction();
		System.out.println("----------search by name and month-----------------" + amonth);

		String hql1 = "From AssociateDetails where AssociateName LIKE :NAME";
		Query q1 = session.createQuery(hql1);
		q1.setParameter("NAME", "%" + aid + "%");
		List<AssociateDetails> clar = q1.list();
		int count = clar.size();

		System.out.println(count);

		List<ClarityHours> clarity = null;
		String hql5 = "FROM ClarityHours";
		Query query5 = session.createQuery(hql5);
		clarity = query5.list();
		int count5 = clarity.size();
		List<ClarityHours> clarity2 = null;
		clarity2 = query5.list();

		List<ClarityHours> results = null;
		String hql2 = null;
		Query query = null;

		for (int i = 0; i < count; i++) {
			System.out.println(clar.get(i).getAssociateID());
			hql2 = "From ClarityHours WHERE Month=:amonth AND AssociateID=:NUM";

			query = session.createQuery(hql2);
			query.setParameter("amonth", amonth);
			query.setParameter("NUM", clar.get(i).getAssociateID());
			System.out.println(clar.get(i).getAssociateID());

			results = query.list();
			// System.out.println(results.get(0).getAssociateID());
			clarity.addAll(results);

		}

		String hql4 = "From AssociateDetails";
		Query q2 = session.createQuery(hql4);

		List<AssociateDetails> results3 = q2.list();
		int listcount = results3.size();

		List<AssociateDetails> results4 = q2.list();

		clarity.removeAll(clarity2);
		// int count2=clarity.size()-count5;

		List<AssociateDetails> results1 = null;
		String hql = null;
		Query query1 = null;
		for (int i = 0; i < clarity.size(); i++) {
			System.out.println(i + "---------------------");
			hql = "From AssociateDetails WHERE AssociateID=:NUM";

			query1 = session.createQuery(hql);
			query1.setParameter("NUM", clarity.get(i).getAssociateID());
			// System.out.println(results.get(i).getAssociateID());

			results1 = query1.list();

			results3.addAll(results1);

		}

		results3.removeAll(results4);

		t.commit();// transaction is commited
		session.close();
		System.out.println("sysout: ------------METHOD OVER");
		return results3;
	}

	*/
	
	//public static JSONObject getTimings(String Month,String Year,String AssociateID)
	public static JSONObject getTimings(String Month,String Year,String AssociateID)
	{
		ClarityHoursDao clarityhoursdao = new ClarityHoursDao();
			// HashMap<Object,Object> hoursFetched= new
		ClarityHoursController clarityHoursController=new ClarityHoursController();
			// LinkedHashMap<Object,Object>();
			JSONObject jsonHoursFetchedObj = new JSONObject();
			JSONArray jsonHoursFetchedArray = new JSONArray();
			int hrFetched = 0;
			String year = Year;
			String month = Month;
			System.out.println("year=" + year + "--month=" + month);
			String associateIdVal = AssociateID;
			int associateId = Integer.parseInt(associateIdVal);
			String datesClarity = "";
			int numberOfDays = clarityHoursController.identifyNumberofDays(year, month);
			
			for (int i = 1; i <= numberOfDays; i++) {
				JSONObject times = new JSONObject();
				datesClarity = year + "/" + month + "/" + i;
				hrFetched = clarityhoursdao.fetchClarityHoursInClarityDetails(associateId, datesClarity);
				times.put(datesClarity, hrFetched);
				jsonHoursFetchedArray.add(times);
			}//Addding 
			System.out.println("Number of days--"+numberOfDays);
			System.out.println(jsonHoursFetchedObj + "JSON Obj");
			jsonHoursFetchedObj.put("times",jsonHoursFetchedArray);
			System.out.println(jsonHoursFetchedObj.toJSONString() +"Check");
			return jsonHoursFetchedObj;
		}
	
	public String validateAssociateIDforUpload(String userid) {
		//Configuration cfg = new Configuration();
		//cfg.configure("hibernate.cfg.xml");
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
			
		System.out.println("SYSOUT: Inside validateAssociateIDforUpload");
		Integer userid1 = Integer.parseInt(userid);
		
		Query query = session.createSQLQuery("select u.AssociateID from user_details AS u where u.UserRole LIKE :name");
		query.setParameter("name","%"+"PMO"+"%");

		List<Integer> associateids = query.list();
		System.out.println("size of list" + associateids.size());

		System.out.println("hai out forloop");
		for ( Integer associatedetails1 : associateids) {
			System.out.println("hai in forloop");
			System.out.println("ass id: " + associatedetails1);
		}
		String result = null;
		if (associateids.contains(userid1)) {
			result = "valid";

		} else {
			result = "invalid";
		}
		System.out.println("list has or not::" + result);
		tx.commit();
		session.close();
		return result;

	}
	
	
	
	/*public static int fetchClarityHours1(int associateId, String dateReceived) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int clarityHours=0;
		String hql = "FROM clarity_hours A  WHERE A.AssociateId =:associateId AND A.Date =:dateReceived";
		Query query = session.createQuery(hql);
//		query = session.createQuery(hql);
		List<ClarityHours> clar = query.list();
		//query.setParameter("NUM", clar.get());
		//System.out.println(clar.get(i).getAssociateID());

		//results = query.list();
		tx.commit();
		session.close();
		return clarityHours;

	}*/
	
	
	}


	
	
	

