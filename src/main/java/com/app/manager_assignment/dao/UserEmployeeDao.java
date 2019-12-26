package com.app.manager_assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.manager_assignment.entity.Manager;
import com.app.manager_assignment.entity.UserEmployee;
/**
 * 
 * @author SThakur (manager-employee Dao)
 *
 */
@Repository
public interface UserEmployeeDao  extends JpaRepository<UserEmployee, Long>{

	UserEmployee findUserEmployeeByEmailId(String emailId);

	Manager findByEmailId(String userName);
	
    @Query(value = "SELECT * FROM user_employee employee join manger_employee_map employeeMap on employee.user_employee_id=employeeMap.employee_id where manger_id = 1", nativeQuery = true)
	UserEmployee findUserByManagerId(Long mangerId);

}
