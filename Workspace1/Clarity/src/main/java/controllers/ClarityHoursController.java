package controllers;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import beans.AssociateDetails;
import beans.ClarityHours;
import dao.ClarityHoursDao;
import dao.ClarityMonthDao;

@Controller
public class ClarityHoursController {

	ModelAndView modelAndView;

//	@Autowired
//	private dao.ClarityHoursDao clarityhoursdao;
//
//	public void setLoginDao(ClarityHoursDao clarityhoursdao) {
//		this.clarityhoursdao = clarityhoursdao;
//	}
	
	@RequestMapping(value = "/fromClarityHours", method = RequestMethod.POST)
	public ModelAndView fromTable(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws InterruptedException {

		ClarityMonthDao claritymonthdao = new ClarityMonthDao();
		ClarityHoursDao clarityhoursdao = new ClarityHoursDao();
		String userid1 = (String) session.getAttribute("useridfromhome");
		@SuppressWarnings("unchecked")
		LinkedHashMap<AssociateDetails, ClarityHours> lp = (LinkedHashMap<AssociateDetails, ClarityHours>) session
				.getAttribute("viewresult");
		AssociateDetails ad = null;
		ClarityHours ch = null;
		Iterator iterator = lp.keySet().iterator();
		while (iterator.hasNext()) {
			ad = (AssociateDetails) iterator.next();
			ch = (ClarityHours) lp.get(ad);
		}
		String hcscid = ad.getHCSCID();
		String userid = userid1;
		String keyForRemove=null;
		LinkedHashMap<String, String> datehours = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> backlogdatehours = new LinkedHashMap<String, String>();
		String actions = request.getParameter("hiddenArray");
		String[] dataresult = actions.split("[,]");
		if (dataresult != null) {
			for (int i = 0; i < dataresult.length; i++) {
				String data = dataresult[i];
				String[] dataresult1 = data.split("-");
				datehours.put(dataresult1[0], dataresult1[1]);
				keyForRemove=dataresult1[0];
			}
		} else {
		}
		String actionsbacklog = request.getParameter("BacklogHiddenArray");
		String backlogmonth = request.getParameter("BacklogDate");
		String[] backlogdataresult = actionsbacklog.split("[,]");
		int backlogdataresultlength = backlogdataresult.length;
		if (backlogdataresultlength >= 2) {
			for (int i = 0; i < backlogdataresult.length; i++) {
				String backlogdata = backlogdataresult[i];
				String[] backlogdataresult1 = backlogdata.split("-");
				backlogdatehours.put(backlogdataresult1[0], backlogdataresult1[1]);
			}
		} else {

		}
		String[] keyfordeletion = keyForRemove.split("/");
		String deletionkey = keyfordeletion[0] + "/" + keyfordeletion[1]+ "/" ;
		System.out.println("before remove");
		clarityhoursdao.removeClarityBacklogTimesheet(Integer.parseInt(userid), deletionkey);
		System.out.println("before update hrs");
		clarityhoursdao.updateClarityHoursByID(userid, hcscid, datehours, backlogdatehours, backlogmonth,keyfordeletion[0],keyfordeletion[1]);
		System.out.println("before update totals");
		claritymonthdao.updateTotalHours(userid,hcscid,keyfordeletion[0],keyfordeletion[1]);
		modelAndView = new ModelAndView("RegisterSuccess");
		return modelAndView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tdetails")
	public @ResponseBody String tdetails(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ClarityHoursDao clarityhoursdao = new ClarityHoursDao();
		JSONObject jsonHoursFetchedObj = new JSONObject();
		JSONArray jsonHoursFetchedArray = new JSONArray();
		int hrFetched = 0;
		List<ClarityHours> clarityHoursList = new ArrayList<ClarityHours>();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String userid1 = (String) session.getAttribute("useridfromhome");
		int associateId = Integer.parseInt(userid1);
		String datesClarity = "";
		String datesClarityKey = "";
		int numberOfDays = identifyNumberofDays(year, month);
		for (int i = 1; i <= numberOfDays; i++) {
			JSONObject times = new JSONObject();
			datesClarity = year + "/" + month + "/" + i;
			clarityHoursList = clarityhoursdao.fetchClarityHours(associateId, datesClarity);
			for (ClarityHours clHours : clarityHoursList) {
				datesClarityKey = datesClarity.concat('-' + clHours.getBacklogMonth());
				if (clHours.getBacklogMonth() != null) {
					hrFetched = clHours.getBacklogHours();
				} else {
					hrFetched = clHours.getHours();
				}
				times.put(datesClarityKey, hrFetched);

			}
			jsonHoursFetchedArray.add(times);
		}
		jsonHoursFetchedObj.put("times", jsonHoursFetchedArray);
		System.out.println(jsonHoursFetchedObj);
		return jsonHoursFetchedObj.toJSONString();
	}

	public int identifyNumberofDays(String year, String month) {

		int yearSelected = Integer.parseInt(year);
		int monthSelected = Integer.parseInt(month);
		Calendar calendar = Calendar.getInstance();
		int date = 1;
		calendar.set(yearSelected, monthSelected - 1, date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;

	}

	public String getMonth(int monthSelected) {
		return new DateFormatSymbols().getMonths()[monthSelected - 1];
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/backlogdetails")
	public @ResponseBody String backlogdetails(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		ClarityHoursDao clarityhoursdao = new ClarityHoursDao();
		JSONObject jsonHoursFetchedObj = new JSONObject();
		JSONArray jsonHoursFetchedArray = new JSONArray();
		int hrFetched = 0;
		String datesBacklog = "";
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String backlogYear = request.getParameter("backlogYear");
		String backlogMonth = request.getParameter("backlogMonth");
		datesBacklog = backlogYear + "/" + backlogMonth;
		String userid1 = (String) session.getAttribute("useridfromhome");
		int associateId = Integer.parseInt(userid1);
		String datesClarity = "";
		int numberOfDays = identifyNumberofDays(year, month);

		for (int i = 1; i <= numberOfDays; i++) {
			JSONObject times = new JSONObject();
			datesClarity = year + "/" + month + "/" + i;
			hrFetched = clarityhoursdao.fetchClarityHoursBacklog(associateId, datesClarity, datesBacklog);
			times.put(datesClarity, hrFetched);
			jsonHoursFetchedArray.add(times);
		} 
		jsonHoursFetchedObj.put("times", jsonHoursFetchedArray);
		return jsonHoursFetchedObj.toJSONString();
	}

	@RequestMapping(value = "/fieldglassDetails")
	public @ResponseBody String fieldglassDetails(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		Integer hrFetched = 0;
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String userid = (String) session.getAttribute("useridfromhome");
		int associateId = Integer.parseInt(userid);
		String startDate = identifyEndDate(year, month);
		String endDate = identifyStartDate(year, month);

		// hrFetched = clarityhoursdao.fetchClarityHoursFieldGlass(associateId,
		// startDate,startDate );
		hrFetched = 160;
		return hrFetched.toString();
	}

	private String identifyEndDate(String year, String month) {
		String startDate;
		Calendar calendar = fetchCalendarDetails(year, month);
		int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		startDate = year + "/" + month + "/" + day;
		return startDate;
	}

	private Calendar fetchCalendarDetails(String year, String month) {
		int yearSelected = Integer.parseInt(year);
		int monthSelected = Integer.parseInt(month);
		Calendar calendar = Calendar.getInstance();
		int date = 1;
		calendar.set(yearSelected, monthSelected - 1, date);
		return calendar;
	}

	private String identifyStartDate(String year, String month) {
		String endDate;
		Calendar calendar = fetchCalendarDetails(year, month);
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		endDate = year + "/" + month + "/" + day;
		return endDate;
	}

}
