package dao;

import java.math.BigDecimal;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import beans.ClarityMonth;
import util.SessionUtil;;

public class ClarityMonthDao {
	//SessionFactory sessionFactory3 = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	SessionFactory sessionFactory;
	public void updateTotalHours(String userid, String hcscid, String yearSelected, String monthSelected) {
		
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int userID = Integer.parseInt(userid);
		int year = Integer.parseInt(yearSelected);
		int month = Integer.parseInt(monthSelected);
		String yearMonthSelc = yearSelected + "/" + monthSelected;
		String sql = "select sum(ch.Hours) from Clarity_hours ch  where ch.AssociateID=:associateid and ch.Year=:year and ch.Month=:month";
		SQLQuery query = session.createSQLQuery(sql);
		query.setInteger("associateid", userID);
		query.setInteger("year", year);
		query.setInteger("month", month);
		List results = query.list();
		BigDecimal totalhoursbd = null;
		int totalhours = 0;
		Iterator<BigDecimal> itr1 = results.iterator();
		while (itr1.hasNext()) {
			totalhoursbd = (BigDecimal) itr1.next();
			totalhours = totalhoursbd.intValue();
			System.out.println("total hours" + totalhoursbd);
		}

		System.out.println("totalhoursbd" + ":::" + totalhoursbd);
		String yearMonth = yearSelected + "/" + month;
		// saveOrUpdateMonth(userid,hcscid,yearMonth);
		// String sql1 = "select cm.Rate from Clarity_month cm where
		// cm.AssociateID=:associateid and cm.Month=:month";
		String sql1 = "select ar.Rate  from Associate_rate ar where ar.AssociateID=:associateid";
		SQLQuery query1 = session.createSQLQuery(sql1);
		query1.setInteger("associateid", userID);

		List<Float> results1 = query1.list();

		int rate1 = 0;
		Float ratefloat = 0.0f;
		Iterator itr = results1.iterator();
		while (itr.hasNext()) {

			ratefloat = (Float) itr.next();
			System.out.println("rate from db:::" + ratefloat);

		}
		tx.commit();
		session.close();
		saveOrUpdateClarityMonth(userid, hcscid, totalhours, ratefloat, yearMonthSelc);
		
	}

	public void saveOrUpdateClarityMonth(String userid, String hcscid, int totalhours, float ratefloat,
			String yearMonthSelc) {
		sessionFactory = SessionUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int associateRateIdRetrived = 0;
		float revenue = totalhours * ratefloat;

		ClarityMonth claritymonth= new ClarityMonth();
		claritymonth.setAssociateID(Integer.parseInt(userid));
		claritymonth.setHCSCID(hcscid);
		claritymonth.setTotalHours(totalhours);
		claritymonth.setMonth(yearMonthSelc);
		claritymonth.setRate(ratefloat);
		claritymonth.setRevenue(revenue);

		Criteria crit = session.createCriteria(ClarityMonth.class);
		crit.setProjection(Projections.property("clarityMonthId"));
		crit.add(Restrictions.eq("AssociateID", claritymonth.getAssociateID()))
				.add(Restrictions.eq("Month", claritymonth.getMonth()));
		List<Integer> rateId = crit.list();
		Iterator<Integer> iterator = rateId.iterator();
		while (iterator.hasNext()) {
			associateRateIdRetrived = (Integer) iterator.next();
			System.out.println("associateRateIdRetrived--"+associateRateIdRetrived);
		}
		claritymonth.setClarityMonthId(associateRateIdRetrived);
		session.saveOrUpdate(claritymonth);
		tx.commit();
		session.close();
	}
}
