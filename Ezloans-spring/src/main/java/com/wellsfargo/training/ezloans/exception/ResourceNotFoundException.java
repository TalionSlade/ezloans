package com.wellsfargo.training.ezloans.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends Exception{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ResourceNotFoundException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
		
		

	}



