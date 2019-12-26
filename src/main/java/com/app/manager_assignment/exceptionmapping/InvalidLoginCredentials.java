package com.app.manager_assignment.exceptionmapping;

import java.io.Serializable;

public class InvalidLoginCredentials extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767121509987628799L;

	public InvalidLoginCredentials(String message) {
		super(message);
	}
}
