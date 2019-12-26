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
 * @author SThakur (Manager Entity)
 *
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@DiscriminatorValue("M")
public class Manager extends UserEmployee implements Serializable{

	private static final long serialVersionUID = -1965807652643686987L;

	@Column(name = "password")
	private String password;
	
	@Column(name = "company")
    private String company;
}
