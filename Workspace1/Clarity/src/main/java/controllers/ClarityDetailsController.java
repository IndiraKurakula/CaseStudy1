package controllers;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sun.java.swing.plaf.windows.resources.windows;

import beans.AssociateDetails;
import beans.ClarityHours;
import dao.AssociateDao;
import dao.ClarityDetailsDao;

@Controller
public class ClarityDetailsController
{
	private static final String UPLOAD_DIRECTORY ="/images";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	
	@Autowired
	private AssociateDao associatedao;
	public void setLoginDao(AssociateDao associatedao) {
		this.associatedao = associatedao;
	}
	
	ModelAndView modelAndView;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listAssociates")
	public @ResponseBody String listAssociates(HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
	    String month = request.getParameter("month");
	    String year = request.getParameter("year");
	    for(AssociateDetails p : associateDao.listAssociates()){
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	        formDetailsJson.put("timings",AssociateDao.getTimings(month,year,p.getAssociateID()+"") );
	        
	       jsonArray.add(formDetailsJson);
	    }
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listProjects")
	public @ResponseBody String listProjects(HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
	    for(String p : associateDao.listProjects()){
	        JSONObject formDetailsJson = new JSONObject();
	        System.out.println(p);
	        formDetailsJson.put("ProjectName", p);
	        System.out.println(p);
	       jsonArray.add(formDetailsJson);
	    }
	    System.out.println("jsonArray"+jsonArray);
	    
	    responseDetailsJson.put("projectdetails", jsonArray);//Here you can see the data in json format
	    System.out.println("responseDetailsJson"+responseDetailsJson);
	    return responseDetailsJson.toJSONString();
	}
	
	ClarityDetailsDao clarityDetailsDao=new ClarityDetailsDao();
	
	@RequestMapping(value = "/listAssociatesBySearch")
	public @ResponseBody String listAssociatesBySearch(String id,String proj,String month,String year, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		System.out.println("SYSOUT: in list associates by serach controller");
		System.out.println(id);
		System.out.println(month);
		String aid = id;
		String aproj=proj;
		String amonth=month;
		String ayear=year;
		System.out.println("AID="+aid);
		
		if(amonth!="select"){
		System.out.println("4444444444444444444444");
		amonth=associateDao.getmonth();
		ayear=associateDao.getyear();
	}
		
		 List<AssociateDetails> results=null;
		System.out.println("amonth="+amonth);
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
		//To populate all the associate details in the clarity Details page
		  try{
	
	    	System.out.println("in try block");
	    	int uid=Integer.parseInt(aid);
	    	System.out.println(uid);
	    	results = associateDao.listAssociatesBySearch(uid, aproj, amonth);
	    	System.out.println("results size ="+results.size());
	    /*	if(results.size()!=0){*/
	    	 for(AssociateDetails p : results){
	 	    	System.out.println(p.getAssociateID());
	 	        JSONObject formDetailsJson = new JSONObject();
	 	        formDetailsJson.put("index", ++count);
	 	        formDetailsJson.put("AssociateID", p.getAssociateID());
	 	        formDetailsJson.put("HCSCID", p.getHCSCID());
	 	        formDetailsJson.put("AssociateName", p.getAssociateName());
	 	        formDetailsJson.put("ProjectName", p.getProjectName());
	 	        formDetailsJson.put("Location", p.getLocation());
	 	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	 	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	 	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	 	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	 	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	 	        formDetailsJson.put("Rate", p.getRate());
	 	        formDetailsJson.put("timings",AssociateDao.getTimings(amonth,ayear,p.getAssociateID()+"") );
	 	       jsonArray.add(formDetailsJson);
	 	      responseDetailsJson.put("associatedetails", jsonArray);
	 	    }
	    	/*}
	    	else{
	    		String msg="No results found";
		    	System.out.println(msg);
		    	JSONObject formDetailsJson = new JSONObject();
		    	formDetailsJson.put("AssociateName", msg);
	    		 JSONObject formDetailsJson = new JSONObject();
		 	        formDetailsJson.put("index", 0);
		 	System.out.println(count);
		 	jsonArray.add(formDetailsJson);
		 	System.out.println(formDetailsJson);
		 	responseDetailsJson.put("associatedetails", jsonArray);
	    	}*/
	    	
	    }
	    catch(Exception e){
	    
	 		e.printStackTrace();
	    	
	    	System.out.println("in catch block");
	    	results = associateDao.listAssociatesBySearch(aid, aproj, amonth);
	    	System.out.println("results size ="+results.size());
	    	/*if(results.size()!=0){*/
	    for(AssociateDetails p : results){
	    	System.out.println(p.getAssociateID());
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	        formDetailsJson.put("timings",AssociateDao.getTimings(amonth,year,p.getAssociateID()+"") );
	       jsonArray.add(formDetailsJson);
	       responseDetailsJson.put("associatedetails", jsonArray);
	    }
	    	/*}
	    	else{
	    		String msg="No results found";
		    	System.out.println(msg);
		    	JSONObject formDetailsJson = new JSONObject();
		    	formDetailsJson.put("AssociateName", msg);
	    		 
		 	System.out.println(count);
		 	jsonArray.add(formDetailsJson);
		 	System.out.println(formDetailsJson);
		 	responseDetailsJson.put("associatedetails", jsonArray);
	    	}*/
	    }
	    //Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	
	/*@RequestMapping(value = "/listAssociatesBySearch")
	public @ResponseBody String listAssociatesBySearch(String id, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		System.out.println("SYSOUT: in list associates by serach controller");
		System.out.println(id);
	
		String aid = id;
		
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    List<AssociateDetails> list=null;
	    System.out.println("-------------------------------"+aid.charAt(0));
	    try{
	    int uid=Integer.parseInt(aid);
	    list=associateDao.listAssociatesById(aid);
	    }
	  catch(Exception e){
		  list=associateDao.listAssociatesBySearch(aid); 
	  }
	    
	
	    int count=0;
	    System.out.println(list.size());
	    if(list.isEmpty()){
	    	String msg="No results found";
	    	System.out.println(msg);
	    	JSONObject formDetailsJson = new JSONObject();
	    	formDetailsJson.put("msg", msg);
	    	 jsonArray.add(formDetailsJson);
	    
	    	 responseDetailsJson.put("noresults", jsonArray);//Here you can see the data in json format
	    	 System.out.println(responseDetailsJson.toString());
	    	  return responseDetailsJson.toJSONString();
	    }
	    else{
	    for(AssociateDetails p : list){
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	 
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
System.out.println(responseDetailsJson.size());
	   
	    return responseDetailsJson.toJSONString();
	    }
	    
	
	
	public ModelAndView GiveErrorMessage(){
		System.out.println("SYSOUT: Inside error method");
		modelAndView= new ModelAndView("ErrorPage");
		
		
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/listAssociatesByAllValues")
	public @ResponseBody String listAssociatesByAllValues(Integer id,String proj,String month, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		System.out.println("SYSOUT: in list associates by serach controller");
		System.out.println(id);
	
		Integer aid = id;
		String aproj=proj;
		String amonth=month;
		System.out.println("AID="+aid);
		System.out.println("aproj="+aproj);
		System.out.println("amonth="+amonth);
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
	    for(AssociateDetails p : associateDao.listAssociatesByAllValues(aid,aproj,amonth)){
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	
	@RequestMapping(value = "/listAssociatesByIdANDProj")
	public @ResponseBody String listAssociatesByIdandProj(String id,String proj, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		System.out.println("SYSOUT: in list associates by serach controller");
		System.out.println(id);
	
		String aid = id;
		String aproj=proj;
		
		//To populate all the associate details in the clarity Details page
		List<AssociateDetails> list =null;
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
		 System.out.println("-------------------------------"+aid.charAt(0));
		    try{
		    int uid=Integer.parseInt(aid);
		    list=associateDao.listAssociatesByIdandProj(aid, aproj);
		    }
		  catch(Exception e){
			  list=associateDao.listAssociatesByNameandProj(aid, aproj); 
		  }
		    
		    if((aid.>=0)&&(aid.charAt(0)<=9)){
		    	list=associateDao.listAssociatesById(aid);
		    }
		    else{
		    	 list=associateDao.listAssociatesBySearch(aid);
		    }
		    int count=0;
		    for(AssociateDetails p : list){
	
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	
	
	@RequestMapping(value = "/listAssociatesByIdANDMonth")
	public @ResponseBody String listAssociatesByIdANDMonth(String id,String month, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		System.out.println(id);
		
		String aid = id;
		String amonth=month;
		
		//To populate all the associate details in the clarity Details page
		List<AssociateDetails> list =null;
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
		 System.out.println("-------------------------------"+aid.charAt(0));
		    try{
		    int uid=Integer.parseInt(aid);
		    list=associateDao.listAssociatesByIdANDMonth(uid, amonth);
		    }
		  catch(Exception e){
			  list=associateDao.listAssociatesByNameandMonth(aid, amonth); 
		  }
		    
		    if((aid.>=0)&&(aid.charAt(0)<=9)){
		    	list=associateDao.listAssociatesById(aid);
		    }
		    else{
		    	 list=associateDao.listAssociatesBySearch(aid);
		    }
		    int count=0;
		    for(AssociateDetails p : list){
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	
	@RequestMapping(value = "/listAssociatesByProjANDMonth")
	public @ResponseBody String listAssociatesByProjANDMonth(String proj,String month, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		System.out.println("SYSOUT: in list associates by serach controller");
	
		
		String aproj=proj;
	
		String amonth=month;
		
		System.out.println("amonth="+amonth);
		System.out.println("amonth="+amonth);
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
	    for(AssociateDetails p : associateDao.listAssociatesByProjANDMonth(aproj,amonth)){
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	
	
	@RequestMapping(value = "/listAssociatesByMonth")
	public @ResponseBody String listAssociatesByMonth(String month, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
	
		System.out.println("SYSOUT: in list associates by serach controller");
		System.out.println(month);
	
		String aid = month;
		
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
	
	  List<AssociateDetails> lists = associateDao.listAssociatesByMonth(aid);
	
	    
	    	 for(AssociateDetails p : lists){
	    		 
	    		 System.out.println("sysout: ------------METHOD IN CONTROLLER");
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	
	@RequestMapping(value = "/listAssociatesByProject")
	public @ResponseBody String listAssociatesByProject(String proj, HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		System.out.println("SYSOUT: in list associates by serach controller");
		System.out.println(proj);
		int aid = Integer.parseInt(id);
		String aid = proj;
		System.out.println("Inside vinutha code");
		String search=request.getParameter("searchedvalue");
		System.out.println("search===="+search); 
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
	    for(AssociateDetails p : associateDao.listAssociatesByProject(aid)){
	        JSONObject formDetailsJson = new JSONObject();
	        formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	    responseDetailsJson.put("associatedetails", jsonArray);//Here you can see the data in json format
	    return responseDetailsJson.toJSONString();
	}
	*/
	
	@RequestMapping(value = "/editAssociate")
	public @ResponseBody String editAssociate(HttpServletRequest request, HttpServletResponse response) {
		AssociateDao associateDao=new AssociateDao();
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    int count=0;
	    JSONObject formDetailsJson = new JSONObject();
	    for(AssociateDetails p : associateDao.edit(request.getParameter("id").toString())) {
	    	formDetailsJson.put("index", ++count);
	        formDetailsJson.put("AssociateID", p.getAssociateID());
	        formDetailsJson.put("HCSCID", p.getHCSCID());
	        formDetailsJson.put("AssociateName", p.getAssociateName());
	        formDetailsJson.put("ProjectName", p.getProjectName());
	        formDetailsJson.put("Location", p.getLocation());
	        formDetailsJson.put("ClarityAccess", p.getClarityAccess());
	        formDetailsJson.put("HCSCEmailid", p.getHCSCEmailID());
	        formDetailsJson.put("PhoneNumber", p.getPhoneNumber());
	        formDetailsJson.put("HCSCJoiningDate", p.getHCSCJoiningDate());
	        formDetailsJson.put("LastClarityUpdateDate", p.getLastClarityUpdateDate());
	        formDetailsJson.put("Rate", p.getRate());
	       jsonArray.add(formDetailsJson);
	    }
	    //responseDetailsJson.put("associate", jsonArray);//Here you can see the data in json format

	    return formDetailsJson.toJSONString();
	}
	
	@RequestMapping(value = "/updateAssociate")
	public ModelAndView updateAssociate(@ModelAttribute("associatedetails") AssociateDetails associatedetails){
		// write code to add associate object
		System.out.println("hello indira");
		AssociateDao associateDao=new AssociateDao();
		//		associateDao.update(associatedetails,(String)(associatedetails.getAssociateID()));
		System.out.println(associatedetails.getAssociateName()+"associative-------");
		System.out.println(associatedetails.getHCSCEmailID()+"associative-------");
		associateDao.update(associatedetails);
		System.out.println("saved::");
		ModelAndView modelandview=new ModelAndView("ClaritySuperUser");
		return modelandview;
	}
	
	
	
	
	
}