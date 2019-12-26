package com.app.manager_assignment.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeRequest {

	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	private Date dateOfBirth;
	private String address;
	private String city;
	private Long mangerId;

}
