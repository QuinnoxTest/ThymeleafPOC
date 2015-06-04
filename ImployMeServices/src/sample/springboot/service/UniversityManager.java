package sample.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.RowMapper;
import org.springframework.data.cassandra.core.CassandraOperations;

import sample.pojo.EmployerCampusJobPostDom;
import sample.pojo.EmployerEventDom;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;

public class UniversityManager implements IUniversityManager {

	@Autowired
	CassandraOperations cassandraOperations;
	
	private static class EmployerEventMapper implements RowMapper<EmployerEventDom>{

		@Override
		public EmployerEventDom mapRow(Row rs, int arg1) throws DriverException {
			EmployerEventDom employerEventDom = new EmployerEventDom();
			employerEventDom.setEventId(rs.getString("event_id"));
			employerEventDom.setEventName(rs.getString("event_name"));
			
			if(null != rs.getDate("start_date"))
				employerEventDom.setStartDate(rs.getDate("start_date").toString());
			if(null != rs.getDate("end_date"))
				employerEventDom.setEndDate(rs.getDate("end_date").toString());
		
			employerEventDom.setStartTime(rs.getString("start_time"));
			employerEventDom.setEndTime(rs.getString("end_time"));
			employerEventDom.setParticipatingUniversity(rs.getString("university_name"));
			employerEventDom.setEventDescription(rs.getString("expectations"));
			employerEventDom.setInvitationStatus(rs.getString("status"));
			employerEventDom.setInvitationStatus(rs
					.getString("invitation_status"));
			employerEventDom.setEventStatus(rs.getString("status"));
			employerEventDom.setInvitationStatus(rs.getString("invitation_status"));
			employerEventDom.setCompanyName(rs.getString("company_name"));
			employerEventDom.setEmailId(rs.getString("email_id"));
			return employerEventDom;
		}

	}
	
	
	private static class UniversityJobsBroadcastMapper implements
	RowMapper<EmployerCampusJobPostDom> {

		@Override
		public EmployerCampusJobPostDom mapRow(Row rs, int arg1) throws DriverException {
			EmployerCampusJobPostDom employerCampusJobPostDom = new EmployerCampusJobPostDom();
		
			employerCampusJobPostDom.setJobIdAndFirmId(rs
					.getString("job_id_and_firm_id"));
		
			employerCampusJobPostDom.setJobId(rs.getString("job_id"));
			employerCampusJobPostDom.setFirmId(rs.getString("firm_id"));
			employerCampusJobPostDom.setJobTitle(rs.getString("job_title"));
			employerCampusJobPostDom.setJobType(rs.getString("job_type"));
			employerCampusJobPostDom.setYearsOfExperienceFrom(rs
					.getInt("yearofexperiencefrom"));
			employerCampusJobPostDom.setYearsOfExperienceTo(rs
					.getInt("yearofexperienceto"));
			employerCampusJobPostDom.setJobDescription(rs
					.getString("description"));
			employerCampusJobPostDom.setPrimarySkills(rs.getList(
					"primary_skills", String.class));
			employerCampusJobPostDom.setSecondarySkills(rs.getList(
					"secondary_skills", String.class));
			employerCampusJobPostDom.setPayPerWeek(rs.getString("payperweek"));
			employerCampusJobPostDom.setFirmName(rs.getString("firm_name"));
			// employerCampusJobPostDom.setEligibleStreams(rs.getList("eligible_streams",String.class));
			employerCampusJobPostDom.setJobLocation(rs.getString("location"));
			employerCampusJobPostDom.setIndustry(rs.getString("industry"));
			employerCampusJobPostDom.setFunctionalArea(rs
					.getString("functional_area"));
			employerCampusJobPostDom.setGpaCutOffFrom(rs
					.getInt("gpacutofffrom"));
			employerCampusJobPostDom.setGpaCutOffTo(rs.getInt("gpacutoffto"));
			employerCampusJobPostDom.setStatus(rs.getString("status"));
			employerCampusJobPostDom
					.setCandidateJobStatus((Map<String, String>) (rs.getMap(
							"candidate_job_status", String.class, String.class)));
		
			// rs.getMap("applied_students",String.class,Date.class);
			employerCampusJobPostDom
					.setCampusJobAppliedStudents((Map<String, Date>) (rs
							.getMap("applied_students", String.class,
									Date.class)));
			employerCampusJobPostDom.setUniversityName(rs.getString("univ_name"));
			
			employerCampusJobPostDom.setPostedOn(CaerusCommonUtility.stringToDate(rs.getString("posted_on"),"E MMM dd HH:mm:ss Z yyyy"));
			
			employerCampusJobPostDom.setSeenByUniversityFlag(rs
					.getBool("campus_job_seen_by_university_flag"));

	return employerCampusJobPostDom;
	}

}
	
	
	
	private static class CampusInternshipMapper implements
	RowMapper<EmployerCampusJobPostDom> {

		@Override
		public EmployerCampusJobPostDom mapRow(Row rs, int arg1)
				throws DriverException {
				EmployerCampusJobPostDom campusInternshipDetails = new EmployerCampusJobPostDom();

				campusInternshipDetails.setInternshipIdAndFirmId(rs
						.getString("internship_id_and_firm_id"));
				campusInternshipDetails.setInternshipId(rs
						.getString("internship_id"));
				campusInternshipDetails.setFirmId(rs.getString("firm_id"));
				campusInternshipDetails.setFirmName(rs.getString("firm_name"));
				campusInternshipDetails.setPostedBy(rs.getString("firm_name"));
				campusInternshipDetails.setInternshipTitle(rs
						.getString("internship_title"));
				campusInternshipDetails.setInternshipType(rs
						.getString("internship_type"));
				campusInternshipDetails.setApproximateHours(rs
						.getString("approximate_hours"));
				campusInternshipDetails.setStartDate(rs.getString("start_date"));
				campusInternshipDetails.setEndDate(rs.getString("end_date"));
				campusInternshipDetails
						.setPayPerHours(rs.getString("payper_hours"));
				campusInternshipDetails.setNumberOfVacancy(rs
						.getString("numberofvacancy"));
				campusInternshipDetails
						.setUniversityName(rs.getString("univ_name"));
				campusInternshipDetails.setPrimarySkills(rs.getList(
						"primary_skills", String.class));
				campusInternshipDetails.setSecondarySkills(rs.getList(
						"secondary_skills", String.class));
				campusInternshipDetails.setInternshipLocation(rs
						.getString("internship_location"));
				campusInternshipDetails.setInternshipDescription(rs
						.getString("internship_description"));
				campusInternshipDetails.setStatus(rs.getString("status"));
				
				campusInternshipDetails.setPostedOn(CaerusCommonUtility.stringToDate(rs.getString("posted_on"),"E MMM dd HH:mm:ss Z yyyy"));
			
				/*
				Integer responseCount = 0;
				if( rs.getMap("applied_students", String.class,Date.class) != null &&  ! rs.getMap("applied_students", String.class,Date.class).isEmpty())
				{
					responseCount = rs.getMap("applied_students", String.class,Date.class).size();
				}
				campusInternshipDetails.setResponseCount(responseCount.toString());*/
				campusInternshipDetails.setCampusInternshipAppliedStudents((Map<String, Date>) rs.getMap("applied_students", String.class,Date.class));
				return campusInternshipDetails;
	}
}

	
	
	
	
	@Override
	public void updateCorporateInvitationStatus(String eventId, String action) {
		
		Update updateCorporateInviteStatus = QueryBuilder.update("employer_event_details");
		
		updateCorporateInviteStatus.with(QueryBuilder.set("invitation_status",""));
		updateCorporateInviteStatus.where(QueryBuilder.eq("event_id",eventId));
		
		cassandraOperations.execute(updateCorporateInviteStatus);
	}

	@Override
	public List<EmployerEventDom> getEventListByUniversityName(String universityName) {
		List<EmployerEventDom> events = new ArrayList<EmployerEventDom>();
		
		Select selectEvents = QueryBuilder.select().from("employer_event_details");
		selectEvents.where(QueryBuilder.eq("university_name", universityName));
		
		try {
			events = cassandraOperations.query(selectEvents,new EmployerEventMapper());
		}
		catch(NullPointerException | IllegalArgumentException | NoHostAvailableException ex)
		{
		}

		return null == events ? new ArrayList<EmployerEventDom>() : events;
	}

	@Override
	public int getNoOfUpcomingEvents(String universityEmailId) {
		String sql = "select count(*) from university_event_details where email_id='"+ universityEmailId + "' and  status='Publish' ALLOW FILTERING ";

		int upcomingEventsNo = Integer.valueOf(cassandraOperations.queryForObject(sql, Long.class).toString());

		return upcomingEventsNo;
	}

	@Override
	public int getNoOfPendingInvitations(String universityName) {
		String sqlQuery1 = "select count(*) from employer_event_details where university_name='"
				+ universityName
				+ "' and invitation_status='Pending' ALLOW FILTERING";

		int noOfPendingRecevivedInvitations = Integer
				.valueOf(cassandraOperations.queryForObject(sqlQuery1,
						Long.class).toString());

		return noOfPendingRecevivedInvitations;
	}

	@Override
	public List<EmployerCampusJobPostDom> getCampusJobs(String universityName) {
		List<String> statusList = new ArrayList<String>();
		List<EmployerCampusJobPostDom> jobsList = new ArrayList<EmployerCampusJobPostDom>();

		statusList.add("Published");
		statusList.add("Broadcasted");

		if (null != statusList) {
			for (String status : statusList) {

				String sqlQuery = "select * from university_job_dtls where univ_name='"
						+ universityName
						+ "' and status='"
						+ status
						+ "' ALLOW FILTERING;";
				List<EmployerCampusJobPostDom> jobPostedListForUniversity = cassandraOperations
						.query(sqlQuery, new UniversityJobsBroadcastMapper());
				jobsList.addAll(jobPostedListForUniversity);
			}

		}
		return jobsList;
	}

	@Override
	public List<EmployerCampusJobPostDom> getCampusInternships(String universityName) {
		Select selectCampusInternships = QueryBuilder.select().from("university_internship_dtls");
		selectCampusInternships.where(QueryBuilder.eq("univ_name",universityName));
		selectCampusInternships.where(QueryBuilder.eq("is_deleted",false));
		selectCampusInternships.where(QueryBuilder.eq("self_posted",false));
		selectCampusInternships.allowFiltering();
		
		List<EmployerCampusJobPostDom> campusInternships = new ArrayList<EmployerCampusJobPostDom>();
		
		try {
			campusInternships = cassandraOperations.query(selectCampusInternships, new CampusInternshipMapper());
		}
		catch(IllegalArgumentException | NullPointerException | NoHostAvailableException ex) {
			campusInternships = new ArrayList<EmployerCampusJobPostDom>();
		}
		return campusInternships == null ? new ArrayList<EmployerCampusJobPostDom>() : campusInternships;
	}

	@Override
	public void updateCampusInternshipStatus(String internshipId,String universityName, String internshipAction) {
		String sql = "update university_internship_dtls set status='"
				+ internshipAction + "' where internship_id_and_firm_id ='"
				+ internshipId + "' and univ_name='" + universityName
				+ "'";

		cassandraOperations.execute(sql);
		
	}

	@Override
	public void updateCampusJobStatus(String jobId, String universityName,
			String jobAction) {
		// TODO Auto-generated method stub
		
	}

}
