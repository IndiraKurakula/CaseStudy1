package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import beans.ClarityHours;
import beans.FieldGlass;
import util.SessionUtil;

public class ClarityHoursDao {

	@SuppressWarnings("deprecation")
	//SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	SessionFactory sessionFactory;
	public void updateClarityHoursByID(String userid, String hcscid, LinkedHashMap<String, String> datehours,
			LinkedHashMap<String, String> backlogdatehours, String backlogmonth, String yearSelected, String monthSelected ) {

		
		int yearSel = Integer.parseInt(yearSelected);
		int monthSel = Integer.parseInt(monthSelected);
		
	
		Integer userid1 = Integer.parseInt(userid);
		Map<String, String> map = new LinkedHashMap<String, String>(datehours);
		String month = null;
		Map<String, String> backlogmap = new LinkedHashMap<String, String>(backlogdatehours);
		Set<String> keys = map.keySet();
		Set<String> keyblog = backlogmap.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();) {
			String key1 = (String) i.next();
			String value1 = (String) map.get(key1);
			saveUpdateClarityTimeSheetDetails(hcscid,userid1, key1, value1, month,yearSel,monthSel);
		}

		if (backlogdatehours.size() != 0 && backlogdatehours != null) {
			for (Iterator<String> j = keyblog.iterator(); j.hasNext();) {
				String key2 = (String) j.next();
				String value2 = (String) backlogmap.get(key2);
				String[] hoursBacklogMonth = key2.split(":");
				String date = hoursBacklogMonth[0];
				String backlgMonth = hoursBacklogMonth[1];
				saveUpdateClarityBacklogTimeSheetDetails(hcscid, userid1, date, value2, backlgMonth);
			}
		}
		
	}

	private void saveUpdateClarityTimeSheetDetails(String hcscid, Integer userid1, String key1,
			String value1, String backlogmonth, int year, int month) {
		
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int clarityIdRetrived = 0;
		ClarityHours clarityHours;
		clarityHours = new ClarityHours();
		clarityHours.setAssociateID(userid1);
		clarityHours.setHCSCID(hcscid);
		clarityHours.setDate(key1);
		clarityHours.setHours(Integer.parseInt(value1));
		clarityHours.setBacklogMonth(backlogmonth);
		clarityHours.setYear(year);
		clarityHours.setMonth(month);
		Criteria crit = session.createCriteria(ClarityHours.class);
		crit.setProjection(Projections.property("clarityhoursid"));
		crit.add(Restrictions.eq("AssociateID", clarityHours.getAssociateID()))
				.add(Restrictions.eq("Date", clarityHours.getDate())).add(Restrictions.isNull("BacklogMonth"));
		@SuppressWarnings("unchecked")
		List<Integer> clarityId = crit.list();
		Iterator<Integer> iterator = clarityId.iterator();
		while (iterator.hasNext()) {
			clarityIdRetrived = (Integer) iterator.next();
		}
		clarityHours.setClarityhoursid(clarityIdRetrived);
		session.saveOrUpdate(clarityHours);
		tx.commit();
		session.close();
	}

	private void saveUpdateClarityBacklogTimeSheetDetails(String hcscid, Integer userid1, String key1,
			String value1, String backlogmonth) {

		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int clarityIdRetrived = 0;
		ClarityHours clarityHours;
		clarityHours = new ClarityHours();
		clarityHours.setAssociateID(userid1);
		clarityHours.setHCSCID(hcscid);
		clarityHours.setDate(key1);
		clarityHours.setBacklogHours(Integer.parseInt(value1));
		clarityHours.setBacklogMonth(backlogmonth);
		Criteria crit = session.createCriteria(ClarityHours.class);
		crit.setProjection(Projections.property("clarityhoursid"));
		crit.add(Restrictions.eq("AssociateID", clarityHours.getAssociateID()))
				.add(Restrictions.eq("Date", clarityHours.getDate()))
				.add(Restrictions.eq("BacklogMonth", clarityHours.getBacklogMonth()));
		@SuppressWarnings("unchecked")
		List<Integer> clarityId = crit.list();
		Iterator<Integer> iterator = clarityId.iterator();
		while (iterator.hasNext()) {
			clarityIdRetrived = (Integer) iterator.next();
		}
		clarityHours.setClarityhoursid(clarityIdRetrived);
		session.saveOrUpdate(clarityHours);
		tx.commit();
		session.close();
	}

	public List<ClarityHours> fetchClarityHours(int associateId, String dateReceived) {
		
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(ClarityHours.class);
		List<ClarityHours> clarityList = new ArrayList<ClarityHours>();
		crit.add(Restrictions.eq("AssociateID", associateId)).add(Restrictions.eq("Date", dateReceived));
		@SuppressWarnings("unchecked")
		List<ClarityHours> clarList = crit.list();
		for (Iterator<ClarityHours> iterator = clarList.iterator(); iterator.hasNext();) {
			ClarityHours ch = (ClarityHours) iterator.next();
			ch.setBacklogHours(ch.getBacklogHours());
			ch.setBacklogMonth(ch.getBacklogMonth());
			ch.setHours(ch.getHours());
			clarityList.add(ch);
		}
		tx.commit();
		session.close();
		return clarityList;

	}
	
	
	public int fetchClarityHoursBacklog(int associateId, String dateReceived, String backlogMonth) {

		int backlogHoursReceived = 0;
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(ClarityHours.class);
		crit.setProjection(Projections.property("BacklogHours"));
		crit.add(Restrictions.eq("AssociateID", associateId)).add(Restrictions.eq("Date", dateReceived))
				.add(Restrictions.eq("BacklogMonth", backlogMonth));
		@SuppressWarnings("unchecked")
		List<Integer> clarityHours = crit.list();
		Iterator<Integer> iterator = clarityHours.iterator();
		while (iterator.hasNext()) {
			backlogHoursReceived = (Integer) iterator.next();
		}
		tx.commit();
		session.close();
		return backlogHoursReceived;

	}

	public int fetchClarityHoursFieldGlass(int associateId, String startDate, String endDate) {
		int fieldGlassTimeSheetHours = 0;
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(FieldGlass.class);
		crit.setProjection(Projections.sum("TimeSheetBillableHours"));
		crit.add(Restrictions.eq("AssociateID", associateId)).add(Restrictions.ge("StartDate", startDate))
				.add(Restrictions.lt("EndDate", endDate));
		@SuppressWarnings("unchecked")
		List<Integer> clarityHours = crit.list();
		Iterator<Integer> iterator = clarityHours.iterator();
		while (iterator.hasNext()) {
			fieldGlassTimeSheetHours = (Integer) iterator.next();
		}
		tx.commit();
		session.close();
		return fieldGlassTimeSheetHours;

	}
	
	public  int fetchClarityHoursInClarityDetails(int associateId, String dateReceived) {

		int clarityHoursRetrived = 0;
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(ClarityHours.class);
		crit.setProjection(Projections.property("Hours"));
		crit.add(Restrictions.eq("AssociateID", associateId)).add(Restrictions.eq("Date", dateReceived));
		List clarityHours = crit.list();

		Iterator iterator = clarityHours.iterator();
		while (iterator.hasNext()) {
			clarityHoursRetrived = (Integer) iterator.next();
		}
		
		tx.commit();
		session.close();
		return clarityHoursRetrived;
			}
	

	public void removeClarityBacklogTimesheet(int associateid, String date) {
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String date1 = date + "%";
		
		Criteria crit = session.createCriteria(ClarityHours.class);
		crit.add(Restrictions.eq("AssociateID", associateid)).add(Restrictions.like("Date", date1))
				.add(Restrictions.isNotNull("BacklogMonth"));
		List<ClarityHours> clarList = crit.list();
		
		for (Iterator<ClarityHours> iterator = clarList.iterator(); iterator.hasNext();) {
			ClarityHours ch = (ClarityHours) iterator.next();
			session.delete(ch);
		}

		tx.commit();
		session.close();
	}

}
