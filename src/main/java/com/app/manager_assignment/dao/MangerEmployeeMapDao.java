package com.app.manager_assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.manager_assignment.entity.MangerEmployeeMap;

/**
 * 
 * @author  SThakur (manager-employee-mapper Dao)
 *
 */
@Repository
public interface MangerEmployeeMapDao extends JpaRepository<MangerEmployeeMap, Long>{

	List<MangerEmployeeMap> findMangerEmployeeMapByManagerId(Long managerId);

	MangerEmployeeMap findMangerEmployeeMapByEmployeeId(Long employeeId);
	MangerEmployeeMap findMangerEmployeeMapByManagerIdAndEmployeeId(Long managerId, Long employeeId);
}
