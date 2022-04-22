package com.example.employee.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping(value="/employees", method=RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee emp) {
		log.info("Post a new employee");
		return empService.createEmployee(emp);
	}

	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public List<Employee> readEmployees() {
	    return empService.getEmployees();
	}
	
	@RequestMapping(value="/employees/template", method=RequestMethod.GET)
	public String templateEmployees(Model model) {
	   	
		model.addAttribute("employees", empService.getEmployees());
		return "employees";
		//return empService.Employees();
	}
	
	@RequestMapping(value="/employees/{empId}", method=RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable(value = "empId") Long id, @RequestBody Employee empDetails) {
	    if(id > 2000) {
	    	log.warn("The user is trying to access a large id number");
	    	log.debug("Running this");
	    	log.error("Checking how logging custom error works");
	    }
	    
	    return empService.updateEmployee(id, empDetails);
	}
	
	@RequestMapping(value="/employees/{empId}", method=RequestMethod.DELETE)
	public void deleteEmployees(@PathVariable(value = "empId") Long id) {
	    empService.deleteEmployee(id);
	}
}
