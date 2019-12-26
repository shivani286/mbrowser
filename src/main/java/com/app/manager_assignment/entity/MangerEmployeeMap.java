package com.app.manager_assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*  (manager-employee-mapper entity)
 * 
 */
@Entity
@Table(name = "manger_employee_map")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class MangerEmployeeMap implements Serializable {

	private static final long serialVersionUID = 2129548946006185831L;
	@Id
	@Column(name = "manger_emp_map_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mangerEmpMapId;
	@Column(name = "manger_id")
	private Long managerId;
	@Column(name = "employee_id")
	private Long employeeId;

}
