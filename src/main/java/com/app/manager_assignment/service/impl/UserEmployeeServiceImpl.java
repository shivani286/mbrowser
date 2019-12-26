package com.app.manager_assignment.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.manager_assignment.dao.MangerEmployeeMapDao;
import com.app.manager_assignment.dao.UserEmployeeDao;
import com.app.manager_assignment.dto.EmployeeRequest;
import com.app.manager_assignment.dto.EmployeeUpdateRequest;
import com.app.manager_assignment.dto.LoginRequest;
import com.app.manager_assignment.entity.Employees;
import com.app.manager_assignment.entity.Manager;
import com.app.manager_assignment.entity.MangerEmployeeMap;
import com.app.manager_assignment.entity.UserEmployee;
import com.app.manager_assignment.exceptionmapping.CommonErrorConstant;
import com.app.manager_assignment.service.UserEmployeeService;

/**
 * 
 * @author SThakur (Manager-employee services Implementation)
 *
 */
@Service
public class UserEmployeeServiceImpl implements UserEmployeeService {

	@Autowired
	private UserEmployeeDao userEmployeeDao;

	@Autowired
	private MangerEmployeeMapDao mangerEmployeeMapDao;

	@Override
	public Manager createRegistration(Manager manager) {

		if (Objects.isNull(manager.getEmailId()) || manager.getEmailId().isEmpty())
			throw new NullPointerException(CommonErrorConstant.EMAIL_ERROR);

		if (Objects.isNull(manager.getPassword()))
			throw new NullPointerException(CommonErrorConstant.PASSWORD_ERROR);

		UserEmployee userEmployee = userEmployeeDao.findUserEmployeeByEmailId(manager.getEmailId());

		if (Objects.nonNull(userEmployee))
			throw new IllegalArgumentException(CommonErrorConstant.EMAIL_EXIST);

		if (isValid(manager.getEmailId()) == false)
			throw new IllegalArgumentException(CommonErrorConstant.EMAIL_ERROR);

		Manager create = new Manager();
		create.setFirstName(manager.getFirstName());
		create.setLastName(manager.getLastName());
		create.setEmailId(manager.getEmailId());

		String base64ResetPassword = Base64.getEncoder().encodeToString(manager.getPassword().getBytes());
		create.setPassword(base64ResetPassword);

		create.setAddress(manager.getAddress());
		create.setCompany(manager.getCompany());
		create.setDateOfBirth(manager.getDateOfBirth());
		return userEmployeeDao.save(create);
	}

	private boolean isValid(String emailId) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return emailId.matches(regex);
	}

	@Override
	public Employees createEmployee(EmployeeRequest employeeRequest) {

		if (Objects.isNull(employeeRequest.getEmailId()) || employeeRequest.getEmailId().trim().isEmpty())
			throw new NullPointerException(CommonErrorConstant.EMAIL_ERROR);

		if (Objects.isNull(employeeRequest.getMangerId()))
			throw new NullPointerException(CommonErrorConstant.MANAGER_NOT_NULL);

		if (isValid(employeeRequest.getEmailId()) == false)
			throw new IllegalArgumentException(CommonErrorConstant.EMAIL_INVALID);
		UserEmployee userEmployee = userEmployeeDao.findByEmailId(employeeRequest.getEmailId());
		if(employeeRequest.getMangerId().equals(userEmployee.getEmployeeId()))
		{
			throw new IllegalArgumentException(CommonErrorConstant.SELF_ASSIGN);		
		}else {
			Employees employee = userEmployeeDao.findUserEmployeeByEmailId(employeeRequest.getEmailId());
			/*	if (Objects.nonNull(userEmployee)) 
			throw new IllegalArgumentException(CommonErrorConstant.EMAIL_ALL_EXIST);
		*/
		System.out.println("================>"+employee);
			
		if (Objects.nonNull(employee)) {
				saveMangerEmployeeMap(employee, employeeRequest);
			}else {
				employee = userEmployeeDao.save(getEmployeeForSave(employeeRequest));
				saveMangerEmployeeMap(employee, employeeRequest);
			}
			
			
			return employee;	
		}
	}

	private void saveMangerEmployeeMap(Employees employee, EmployeeRequest employeeRequest) {
		MangerEmployeeMap mangerEmployeeMap = mangerEmployeeMapDao.findMangerEmployeeMapByManagerIdAndEmployeeId(employeeRequest.getMangerId(), employee.getEmployeeId());
		
		if (Objects.isNull(mangerEmployeeMap)) {
			mangerEmployeeMap = new MangerEmployeeMap();
			mangerEmployeeMap.setManagerId(employeeRequest.getMangerId());
			mangerEmployeeMap.setEmployeeId(employee.getEmployeeId());
			mangerEmployeeMapDao.save(mangerEmployeeMap);
		}
	}

	private Employees getEmployeeForSave(EmployeeRequest employeeRequest) {
		Employees employee = new Employees();
		employee.setAddress(employeeRequest.getAddress());
		employee.setCity(employeeRequest.getCity());
		employee.setDateOfBirth(employeeRequest.getDateOfBirth());
		employee.setEmailId(employeeRequest.getEmailId());
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setPhoneNumber(employeeRequest.getPhoneNumber());
		return employee;
	}

	@Override
	public List<UserEmployee> findAllEmployeesForManger(Long managerId) {

		List<MangerEmployeeMap> employeeIds = mangerEmployeeMapDao.findMangerEmployeeMapByManagerId(managerId);
		List<UserEmployee> userEmployee = new ArrayList<UserEmployee>();
		for (MangerEmployeeMap e : employeeIds) {
			UserEmployee user = userEmployeeDao.findOne(e.getEmployeeId());
			userEmployee.add(user);
		}

		return userEmployee;
	}

	@Override
	public void deleteEmployee(Long employeeId) {

		if (Objects.isNull(employeeId))
			throw new NullPointerException(CommonErrorConstant.EMAIL_NOT_NULL);

		userEmployeeDao.delete(employeeId);
		MangerEmployeeMap map = mangerEmployeeMapDao.findMangerEmployeeMapByEmployeeId(employeeId);
		mangerEmployeeMapDao.delete(map.getMangerEmpMapId());

	}

	@Override
	public Employees updateEmployee(Long employeeId, EmployeeUpdateRequest employeeResquest) {
		if (Objects.isNull(employeeId))
			throw new NullPointerException(CommonErrorConstant.EMAIL_NOT_NULL);

		Employees updateEmployee = (Employees) userEmployeeDao.findOne(employeeId);
		updateEmployee.setFirstName(Objects.nonNull(employeeResquest.getFirstName()) ? employeeResquest.getFirstName() : updateEmployee.getFirstName());
		updateEmployee.setLastName(Objects.nonNull(employeeResquest.getLastName()) ? employeeResquest.getLastName() : updateEmployee.getLastName());
		updateEmployee.setEmailId(updateEmployee.getEmailId());
		updateEmployee.setAddress(Objects.nonNull(employeeResquest.getAddress()) ? employeeResquest.getAddress() : updateEmployee.getAddress());
		updateEmployee.setCity(Objects.nonNull(employeeResquest.getCity()) ? employeeResquest.getCity() : updateEmployee.getCity());
		Date dob;
		try {
			if (Objects.nonNull(employeeResquest.getDateOfBirth())) {
				dob = new SimpleDateFormat("yyyy-MM-dd").parse(employeeResquest.getDateOfBirth());
				updateEmployee.setDateOfBirth(dob);
			} else {
				updateEmployee.setDateOfBirth(updateEmployee.getDateOfBirth());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		updateEmployee
				.setPhoneNumber(Objects.nonNull(employeeResquest.getPhoneNumber()) ? employeeResquest.getPhoneNumber()
						: updateEmployee.getPhoneNumber());
		return userEmployeeDao.saveAndFlush(updateEmployee);
	}

	@Override
	public UserEmployee login(LoginRequest loginRequest) {

		if (Objects.isNull(loginRequest) || Objects.isNull(loginRequest.getUserName()) || Objects.isNull(loginRequest.getPassword())) {
			return null;
		}

		if (isValid(loginRequest.getUserName()) == false)
			throw new IllegalArgumentException(CommonErrorConstant.EMAIL_INVALID);

		Manager user = userEmployeeDao.findByEmailId(loginRequest.getUserName());

		String userCurrentPassword = new String(Base64.getDecoder().decode(user.getPassword()));

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException(CommonErrorConstant.EMAIL_WRONG);
		}
		if (!userCurrentPassword.equals(loginRequest.getPassword())) {
			throw new IllegalArgumentException(CommonErrorConstant.WRONG_PASSWORD);
		}

		return user;
	}

}
