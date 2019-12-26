package com.app.manager_assignment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.manager_assignment.dto.EmployeeRequest;
import com.app.manager_assignment.dto.EmployeeUpdateRequest;
import com.app.manager_assignment.dto.LoginRequest;
import com.app.manager_assignment.entity.Employees;
import com.app.manager_assignment.entity.Manager;
import com.app.manager_assignment.entity.UserEmployee;
import com.app.manager_assignment.service.UserEmployeeService;
/**
 * 
 * @author SThakur (manager-employee Controller)
 *
 */

@RestController
@RequestMapping("/api/v1.0")
public class UserRegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	private UserEmployeeService userEmployeeService;
	
	@PostMapping("/login")
	public UserEmployee Login(@RequestBody LoginRequest loginRequest,HttpServletRequest request) {
			return userEmployeeService.login(loginRequest);
	}
	
	@PostMapping("/registration")
	public Manager createRegistration(@RequestBody Manager manager) {
		return userEmployeeService.createRegistration(manager);
	}
	
	@PostMapping("/create/employee")
	public Employees createEmployee(@RequestBody EmployeeRequest employee) {
		return userEmployeeService.createEmployee(employee);
	}
	
	@GetMapping("/employees/{managerId}")
	public List<UserEmployee> getAllEmployee(@PathVariable(name = "managerId") Long managerId) {
		return userEmployeeService.findAllEmployeesForManger(managerId);
	}
	
	@PutMapping("/update/employee/{employeeId}")
	public Employees updateEmployee(@PathVariable(name = "employeeId") Long employeeId,@RequestBody EmployeeUpdateRequest employeeResquest) {
		return userEmployeeService.updateEmployee(employeeId,employeeResquest);
	}
	
	@DeleteMapping("/delete/employee/{employeeId}")
	public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		userEmployeeService.deleteEmployee(employeeId);
	}
}
