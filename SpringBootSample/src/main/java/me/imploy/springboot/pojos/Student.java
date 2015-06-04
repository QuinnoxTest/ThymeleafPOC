package me.imploy.springboot.pojos;

public class Student {

	private String firstName;
	private String lastName;
	private String emailId;
	private String jobType;
	private String keyword;
	private String searchCriterion;
	

	
	public String getSearchCriterion() {
		return searchCriterion;
	}
	public void setSearchCriterion(String searchCriterion) {
		this.searchCriterion = searchCriterion;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
