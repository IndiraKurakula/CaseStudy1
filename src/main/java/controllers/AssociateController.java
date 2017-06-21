package controllers;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import beans.AssociateDetails;
import beans.ClarityHours;
import dao.AssociateDao;
import dao.ClarityDetailsDao;

@Controller
public class AssociateController 
{
	@RequestMapping(value = "/loginhere", method = RequestMethod.GET)
	public ModelAndView from() {
		System.out.println("at get method controller");
		/*String genuserid = "561931";
		//String validationresult=associatedao.validateAssociateIDforUpload(genuserid);
		String validationresult = "invalid";
		if(validationresult.equals("invalid"))
		{
			 ModelAndView modelandview = new ModelAndView("ClarityHome","id","invalid");
			 return modelandview;
		}
		else if(validationresult.equals("valid")){
		
			ModelAndView modelandview = new ModelAndView("ClarityHome","id","valid");
			 return modelandview;
		}*/
		
		ModelAndView modelAndView = new ModelAndView("ClarityHome");
		return modelAndView;
	}
	
	@Autowired
	private AssociateDao associatedao;
	public void setLoginDao(AssociateDao associatedao) {
		this.associatedao = associatedao;
	}
	ModelAndView modelAndView;
	
	@RequestMapping(value = "/submituser", method = RequestMethod.POST)
	
	public ModelAndView userEntry(@ModelAttribute("associate") AssociateDetails associate, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		System.out.println("at post method controller:::: submit user");
		String usertype=request.getParameter("clarity");
		String userid=request.getParameter("userid");
		String genuserid = "561931";
		//String genuserid = "561931";
		System.out.println("user"+usertype);
		System.out.println("user id:::"+userid);
		if(usertype.equals("Lead&Super")){
		session = request.getSession(true);
		session.setAttribute("useridfromhome", userid);
		}
		else if(usertype.equals("General")){
			session = request.getSession(true);
			session.setAttribute("useridfromhome", genuserid);	
		}
//		AssociateDetails ad;
		LinkedHashMap<AssociateDetails,ClarityHours> viewresult= new LinkedHashMap<AssociateDetails,ClarityHours>();
		LinkedHashMap<AssociateDetails,ClarityHours> viewresult2= new LinkedHashMap<AssociateDetails,ClarityHours>();
		String validationresultPMO=associatedao.validateAssociateIDforUpload(genuserid);
         System.out.println("SYSOUT: validation result after upload validation"+validationresultPMO);
         if(validationresultPMO.equals("valid"))
         {
                modelAndView= new ModelAndView("ClarityHome", "errmsg", "Access Denied to PMO Users!!!");
                return modelAndView;
         }	
         else{
        	 /*&&(!userid.equals("      "))*/
			if((usertype.equals("Lead&Super"))&&(userid.length()==6)&&(!userid.equals(null))&&(!userid.equals("      ")))
				
			
				{	String validationforsupuser = associatedao.validateAssociateIDsuper(genuserid);
				
				
				if(validationforsupuser.equals("super"))
				{
				String validationresult=associatedao.validateAssociateID(userid);
				System.out.println("validationresult"+validationresult);
					if(validationresult.equals("invalid"))
					{
						modelAndView= new ModelAndView("ClarityHome", "msg", "Invalid User!!! Access Denied");
					}
					else 
					{
						viewresult=associatedao.autoPopulateSuperUser(userid);
						session.setAttribute("viewresult", viewresult);
						System.out.println("in controller after viewresult from dao");
						 modelAndView = new ModelAndView("ClaritySuperUser");
					 }
				}
					
					else{
						modelAndView= new ModelAndView("ClarityHome", "msg", "Not a Super User.. Access Denied!!!");						
					}
				
			}
			else{
				modelAndView= new ModelAndView("ClarityHome", "msg", "Invalid Associate ID! ");
			}
			if(usertype.equals("General"))
			{
				/*user id should be obatined through session*/
				System.out.println("In general user controller");
				viewresult=associatedao.autoPopulateSuperUser(genuserid);
		//		associatedao.registerEmployee();
				session.setAttribute("viewresult", viewresult);
				modelAndView = new ModelAndView("ClarityGeneralUser");
			}
			return modelAndView;
			}
	}

	@RequestMapping(value = "/submitassociate", method = RequestMethod.POST)
	public ModelAndView saveAssociate(@ModelAttribute("associate") AssociateDetails associate, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		/*session = request.getSession(false);
		session.setAttribute("associate", associate);*/
		associatedao.updateAssociateDetailsForID(associate);
		return modelAndView;
	}
	
	/*//exporting the data
		@RequestMapping(value = "/exportbutton", method = RequestMethod.GET)
		public AssociateDetailsExcelView exportAssociateDetails(Model model){
			List<AssociateDetails> assoDetails = associatedao.listAssociates();
			model.addAttribute("AssociateDetails", assoDetails);
			return new AssociateDetailsExcelView();
		}
	
		ClarityDetailsDao clarityDetailsDao=new ClarityDetailsDao();
	
	@RequestMapping(value = "/exportbuttonaftersearch", method = RequestMethod.GET)
	public AssociateDetailsExcelView exportAssociateDetailsBySearch(Model model,String val, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		System.out.println("SYSOUT: ------------------------------------------------------------------------");
		System.out.println("SYSOUT: val in export"+val);
		String id=request.getParameter("val");
		//val=(String) ses.getAttribute("val");
		List<AssociateDetails> assoDetails = clarityDetailsDao.listAssociatesByProject(id);
		model.addAttribute("AssociateDetails", assoDetails);
		return new AssociateDetailsExcelView();
	} 
	*/
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView  handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile fileUpload, HttpSession session) throws Exception {
		// @RequestParam CommonsMultipartFile[] fileUpload
          System.out.println("SYSOUT: At upload controller");
          String userid = "568433";
         String validationresultPMO=associatedao.validateAssociateIDforUpload(userid);
          System.out.println("SYSOUT: validation result after upload validation"+validationresultPMO);
          if(validationresultPMO.equals("invalid"))
          {
                 modelAndView= new ModelAndView("ClarityHome", "errmsg", "Only PMO Users can upload!!!");
                 return modelAndView;
          }
          else{
         String path=session.getServletContext().getRealPath("/");
         
      /*   JFileChooser fileChooser = new JFileChooser();
   	  FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xls");
   	
   	  fileChooser.setFileFilter(filter); 
   	  System.out.println("fileChooser"+fileChooser);*/
   	
   	System.out.println(session.getServletContext().getRealPath("/"));
    String filename=fileUpload.getOriginalFilename();  
    if(filename.equals(null)||filename.equals("")){
    	modelAndView= new ModelAndView("ClarityHome", "errmsg", "No File Chosen ");
        return modelAndView;
    }
    else{
    System.out.println(path+" "+filename);  
          try{  
              byte barr[]=fileUpload.getBytes();  
                
              BufferedOutputStream bout=new BufferedOutputStream(  
                       new FileOutputStream(path+"/"+filename));  
              bout.write(barr);  
              
              //
              
               	    System.out.println("In scr");
            	    Resource resource = new ClassPathResource("C:\\Users\\561930\\Desktop\\FiletoUpload.sql");
            	    System.out.println("going well");
            	    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            	    System.out.println("123");
            	    /*databasePopulator.setScripts(resource);
            	    System.out.println("scripts replaced"); */
             /* BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\568436\\Desktop\\FiletoUpload.sql"));
              LineNumberReader fileReader = new LineNumberReader(in);
              String query = JdbcTestUtils.readScript(fileReader);*/
              bout.flush();  
              bout.close();  
              }
          catch(Exception e){System.out.println(e);
          }  
          modelAndView= new ModelAndView("ClarityHome", "errmsg", "File "+filename+"  Uploaded Successfully ");
          return modelAndView;
          
	}
          }
}
}
