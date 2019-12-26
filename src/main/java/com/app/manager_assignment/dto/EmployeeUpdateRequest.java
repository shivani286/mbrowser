package com.app.manager_assignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdateRequest {
	private String firstName;
	private String lastName;
	private String phoneNumber;
    private String dateOfBirth;
	private String address;
    private String city;

}
