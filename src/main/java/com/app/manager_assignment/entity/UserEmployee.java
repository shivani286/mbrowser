package com.app.manager_assignment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 * @author SThakur (manager-employee common data)
 *
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "EMP_TYPE")
@Table(name = "user_employee")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserEmployee implements Serializable {

	private static final long serialVersionUID = 6573175696782184292L;

	@Id
	@Column(name = "user_employee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Column(name = "email_id")
	private String emailId;

	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dateOfBirth;

	@Column(name = "address")
	private String address;
}
