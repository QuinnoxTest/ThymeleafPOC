package sample.springboot.service;

import sample.pojo.EmployerEventDom;

public interface IEmployerEventManager {

	EmployerEventDom getEmployerEventDetails(String eventId);

}
