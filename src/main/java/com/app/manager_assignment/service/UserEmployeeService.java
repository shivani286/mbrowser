package com.app.manager_assignment.service;

import java.util.List;

import com.app.manager_assignment.dto.EmployeeRequest;
import com.app.manager_assignment.dto.EmployeeUpdateRequest;
import com.app.manager_assignment.dto.LoginRequest;
import com.app.manager_assignment.entity.Employees;
import com.app.manager_assignment.entity.Manager;
import com.app.manager_assignment.entity.UserEmployee;

public interface UserEmployeeService {
	
	public Manager createRegistration(Manager manager);
	
	public Employees createEmployee(EmployeeRequest employee);
	
	public List<UserEmployee> findAllEmployeesForManger(Long managerId);

	public void deleteEmployee(Long employeeId);

	public Employees updateEmployee(Long employeeId, EmployeeUpdateRequest employeeResquest);

	public UserEmployee login(LoginRequest loginRequest);

}
