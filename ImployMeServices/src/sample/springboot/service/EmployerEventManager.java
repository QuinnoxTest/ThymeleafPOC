package sample.springboot.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.RowMapper;
import org.springframework.data.cassandra.core.CassandraOperations;

import sample.pojo.EmployerEventDom;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

public class EmployerEventManager implements IEmployerEventManager {

	Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	CassandraOperations cassandraOperations;
	
	private static class EmployerEventMapper implements RowMapper<EmployerEventDom> {
		@Override
		public EmployerEventDom mapRow(Row rs, int arg1)throws DriverException {
			
			EmployerEventDom employerEventDom = new EmployerEventDom();
			employerEventDom.setEventId(rs.getString("event_id"));
			employerEventDom.setEventName(rs.getString("event_name"));
			
			if(rs.getDate("start_date") != null)
				employerEventDom.setStartDate(rs.getDate("start_date").toString());
			
			if(rs.getDate("end_date") != null)
				employerEventDom.setEndDate(rs.getDate("end_date").toString());
			
			employerEventDom.setStartTime(rs.getString("start_time"));
			employerEventDom.setEndTime(rs.getString("end_time"));
			employerEventDom.setParticipatingUniversity(rs.getString("university_name"));
			employerEventDom.setEventDescription(rs.getString("expectations"));
			employerEventDom.setEventStatus(rs.getString("status"));
			employerEventDom.setInvitationStatus(rs.getString("invitation_status"));
			employerEventDom.setCompanyName(rs.getString("company_name"));
			employerEventDom.setEmailId(rs.getString("email_id"));
			employerEventDom.setEventLocation(rs.getString("event_location"));
			
			employerEventDom.setEligibleBatches(rs.getList("eligible_batches", String.class));
			employerEventDom.setNumberOfHirings(rs.getList("no_of_hirings", String.class));
			employerEventDom.setFunctionalAreas(rs.getList("functional_areas", String.class));
			employerEventDom.setMinimumSalaryOffered(rs.getList("min_sal_offered", String.class));
			employerEventDom.setEmployerRepositoryFileNames(rs.getList("repo_file_names", String.class));
			
			employerEventDom.setAttachCompanyProfile(rs.getBool("is_company_profile_attached"));
			employerEventDom.setAttachPreplacementFiles(rs.getBool("is_pre_placement_attached"));
			
			employerEventDom.setAcceptedByStudentsList(rs.getList("accepted_by_students", String.class));
			employerEventDom.setDeniedByStudentsList(rs.getList("denied_by_students", String.class));
			
			return employerEventDom;
		}
	
	}
	
	
	@Override
	public EmployerEventDom getEmployerEventDetails(String eventId) {
		String selectEventDetailsQuery = "select * from employer_event_details where event_id = '"+eventId+"'";
		EmployerEventDom eventDetailsDom = new EmployerEventDom();
		
		try{
			eventDetailsDom = cassandraOperations.queryForObject(selectEventDetailsQuery,new EmployerEventMapper());
		}
		catch(IllegalArgumentException argEx)
		{
			logger.error("Empty Result Set in getFormulaNames");
		}
		catch(NoHostAvailableException noHostEx)
		{
			logger.error("No Host Available to Serve Requests");
		}
		return eventDetailsDom == null ? new EmployerEventDom(): eventDetailsDom;
	}
	
	
	

}
