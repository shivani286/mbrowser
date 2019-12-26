package com.app.manager_assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author SThakur (Employee Entity)
 *
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@DiscriminatorValue("E")
public class Employees extends UserEmployee  implements Serializable {

	private static final long serialVersionUID = -5973724816602157534L;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "city")
    private String city;
}
