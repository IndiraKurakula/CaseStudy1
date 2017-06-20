package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import beans.AssociateDetails;
import beans.UserDetails;
import dao.AssociateDao;
import dao.RolesDao;

@Controller
public class UsersController {
	@RequestMapping(value = "/testrole", method = RequestMethod.GET)
	public ModelAndView showform() {
		return new ModelAndView("Roles", "command", new UserDetails());
	}

	/*@RequestMapping(value = "/Submit", method = RequestMethod.POST)
	public ModelAndView addUers(@ModelAttribute("user") UserDetails userdetails, HttpServletRequest request,
		HttpServletResponse response) {
		String userid = request.getParameter("changeid");
		String newrole = request.getParameter("newrole");
		RolesDao rolesDao = new RolesDao();
		String userresult = rolesDao.validateUserDetails(userid, newrole);
		if (userresult.equals("existing")) {
			return new ModelAndView("Roles", "msg", "Given user is already a " + newrole + "!!!");
		} else {
			rolesDao.save(userdetails, userid, newrole);
			ModelAndView modelAndView = new ModelAndView("RoleUpdationSuccess");
			return modelAndView;
		}
	}*/
	
	@RequestMapping(value = "/Submit")
	public @ResponseBody String addUsers(String id, String role, @ModelAttribute("user") UserDetails userdetails, HttpServletRequest request, HttpServletResponse response) {
		//AssociateDao associateDao=new AssociateDao();
		System.out.println("In Roles Controller");
		//To populate all the associate details in the clarity Details page
		JSONObject responseDetailsJson = new JSONObject();
		/*String userid = id;
		String newrole = roles;*/
		RolesDao rolesDao = new RolesDao();
		String userresult = rolesDao.validateUserDetails(id, role);
		if (userresult.equals("existing")) {
			String message = "Given user is already a " + role + "!!!";
			responseDetailsJson.put("msg", message);
			
			
		} else {
			rolesDao.save(userdetails,id, role);
			String message = "User role is updated successfully!!!";
			responseDetailsJson.put("msg", message);

		}
	   
	    return responseDetailsJson.toJSONString();
	}
}