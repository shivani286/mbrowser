package com.app.manager_assignment.exceptionmapping;

import java.io.Serializable;

public class EntityAlreadyExistsException extends RuntimeException implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -8882257428143549361L;

	public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
