package com.wellsfargo.training.ezloans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
		
		private static final long serialVersionUID = 1L;
		
		//custom exception used with OrElseThrow method.
		public ResourceNotFoundException(String message) {
			super(message);
		}
		
		

	}



