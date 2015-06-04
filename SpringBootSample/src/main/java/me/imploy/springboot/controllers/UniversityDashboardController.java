package me.imploy.springboot.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sample.pojo.EmployerCampusJobPostDom;
import sample.pojo.EmployerEventDom;
import sample.springboot.service.IEmployerEventManager;
import sample.springboot.service.ILoginManagement;
import sample.springboot.service.IUniversityManager;

/**
 * This class is used to display the university dashboard
 * @author KarthikeyanK
 * Annotated by BalajiI on 25/9/2014
 */
@Controller
public class UniversityDashboardController
{
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	List<EmployerCampusJobPostDom> campusJobs = null;

	List<EmployerCampusJobPostDom> campusInternships = null;
	
	// Auto-wiring service component
	@Autowired
	private IUniversityManager universityManager;
	
	@Autowired
	private IEmployerEventManager employerEventManager;
	
	@Autowired 
	private ILoginManagement loginManagement;
	
	/**
	 * This method is used to fetch all the data from the database that is to be displayed on the university dashboard
	 */
	
	
	/**
	 * This method is used to broadcast a campus internship.
	 * @author balajii
	 * @param internshipId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return Boolean(successFlag)
	 */
	@RequestMapping(value="university_broadcast_internship.json",method=RequestMethod.POST)
	@ResponseBody
	public Boolean broadcastInternship(@RequestParam("internshipId") String internshipId,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		boolean successFlag = false;
	
		//getting new session		
		//HttpSession session = httpServletRequest.getSession();
		// Setting values in the session
		String universityName = "Yale University";
		
		try
		{
			/** Updating the status of Internship */
			universityManager.updateCampusInternshipStatus(internshipId,universityName,"Broadcasted");
			successFlag = true;
		}
		catch(Exception ex)
		{
			logger.error(ex.getLocalizedMessage());
		}
		return successFlag;
	}
	
	@RequestMapping(value = "university_dashboard.htm")
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		/*// Spring security authentication containing the logged in user details
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String universityEmailId = authentication.getName();
		
		logger.info(universityEmailId +" Logged In");
		
		List<GrantedAuthority> authorityList = (List<GrantedAuthority>) authentication.getAuthorities();
		String authority = null;
		Iterator<GrantedAuthority> itr = authorityList.iterator();

		while (itr.hasNext()) 
		{
			authority = itr.next().toString();
		}

		if(null != loginManagementDom && loginManagementDom.isAdminFlag())
		{
			request.getSession().setAttribute("isAdmin", true);
		}
	    
		String universityName = loginManagementDom.getEntityName();
		String universityUserName = "";
		if(null!= loginManagementDom.getFirstName() && null!= loginManagementDom.getLastName()){
			
			universityUserName = loginManagementDom.getFirstName()+ " "+loginManagementDom.getLastName();
		}
		else {
			universityUserName= "User";
		}*/
		
		
		// Creating a new session
		//HttpSession session = request.getSession();
		
		// Setting values in the session
		//session.setAttribute("univName", universityName);
		//session.setAttribute("username", universityUserName);
		
		// Fetching parameter value from request
		String eventId = request.getParameter("event_id");
		String jobId = request.getParameter("job_id");
		String jobAction = request.getParameter("job_action");
	    String internshipId = request.getParameter("internship_id");
	    String internshipAction = request.getParameter("internship_action");
		String action = request.getParameter("action");

		// The modelAndView object contains the model(data) and the view(page)
		ModelAndView modelAndView = new ModelAndView("university_dashboard");

		String universityName = "Yale University";
		String universityEmailId = "yale@caerus.com";
		
				// check if the job id and action are not null, then update the status of the job based on the job id and university name
			    if(jobId!= null && jobAction != null)
			    {
			    	universityManager.updateCampusJobStatus(jobId,universityName,jobAction);
			    }
			    
			    // check if the internship id and action are not null, then update the status of the internship based on the internship id and university name
			    if( internshipId != null && internshipAction != null)
			    {
			    	universityManager.updateCampusInternshipStatus(internshipId,universityName,internshipAction);
			    }
			    
				if (universityEmailId != null) {

					// Retrieving list of jobs and its details posted for the university
					campusJobs = universityManager.getCampusJobs(universityName);
					
					// Retrieving list of internships and its details posted for the university
					campusInternships = universityManager.getCampusInternships(universityName);
					
					List<EmployerCampusJobPostDom> seenCampusJobsList = new ArrayList<EmployerCampusJobPostDom>();
						
						if (campusJobs != null && campusInternships != null) 
						{
							// Adding values to the modelAndView Object
							
							for (EmployerCampusJobPostDom campusJob : campusJobs) {
								
								if(campusJob.isSeenByUniversityFlag() == false)
								{
									seenCampusJobsList.add(campusJob);
								}
							}
							
							int campusJobCount = 0;
							if(null != campusJobs && campusJobs.size() > 0)
								campusJobCount = campusJobs.size();
							
							modelAndView.addObject("campusJobs",campusJobs);
							modelAndView.addObject("campusInternships",campusInternships);
							modelAndView.addObject("newJobAlerts",seenCampusJobsList.size());
							
							int campusInternshipCount = 0;
							if(null != campusInternships && campusInternships.size() > 0)
								campusInternshipCount = campusInternships.size();
							
							modelAndView.addObject("campusJobCount",campusJobCount);
							modelAndView.addObject("campusInternshipCount",campusInternshipCount);

						}
					
					if (null != eventId && eventId.trim().length() > 0) {

						// Fetching event details from the database
						//EmployerEventDom employerEventDom = employerEventManager.getEmployerEventDetails(eventId);

						// Change the status of the event depending on the action
						universityManager.updateCorporateInvitationStatus(eventId, action);
						
					}
					
				
					// Retrieving list of events and its details from the database
					List<EmployerEventDom> eventList = universityManager.getEventListByUniversityName(universityName);

					// Retrieving the number of upcoming events for the university
					int noOfUpcomingEvents = universityManager.getNoOfUpcomingEvents(universityEmailId);

					// Retrieving the number of pending invitations for the university
					int pendingInvitations = universityManager.getNoOfPendingInvitations(universityName);

					// Adding values to the modelAndView Object
					modelAndView.addObject("eventList", eventList);
					
					modelAndView.addObject("noOfUpcomingEvents", noOfUpcomingEvents);
					
					modelAndView.addObject("pendingInvitations", pendingInvitations);
				}
				
			
			modelAndView.addObject("dbDateFormat","E MMM dd HH:mm:ss Z yyyy");
			modelAndView.addObject("displayDateFormat","dd MMM yyyy");
		// redirect to the defined view(page)
		   return modelAndView;
	}

	
}
