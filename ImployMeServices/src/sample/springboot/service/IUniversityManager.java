package sample.springboot.service;

import java.util.List;

import sample.pojo.EmployerCampusJobPostDom;
import sample.pojo.EmployerEventDom;

public interface IUniversityManager {

	void updateCorporateInvitationStatus(String eventId, String action);

	List<EmployerEventDom> getEventListByUniversityName(String universityName);

	int getNoOfUpcomingEvents(String universityEmailId);

	int getNoOfPendingInvitations(String universityName);

	List<EmployerCampusJobPostDom> getCampusJobs(String universityName);

	List<EmployerCampusJobPostDom> getCampusInternships(String universityName);

	void updateCampusInternshipStatus(String internshipId,
			String universityName, String internshipAction);

	void updateCampusJobStatus(String jobId, String universityName,
			String jobAction);

}
