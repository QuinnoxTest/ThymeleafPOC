package sample.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sample.springboot.dao.SampleDAO;

public class SampleManager {

	@Autowired
	SampleDAO sampleDAO;

	public List<String> getUsernames() {
		return sampleDAO.getUsernames();
	}
	
}
