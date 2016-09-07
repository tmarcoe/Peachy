package com.peachy.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String>{

	@Override
	public void initialize(Password constraintAnnotation) { }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		if (!value.matches(".*[A-Z].*")){
			return false;
		}
		if (!value.matches(".*[!@#$%^&*_].*")) {
			return false;
		}
		if (!value.matches(".*\\d+.*")) {
			return false;
		}
		if (value.length() < 8) {
			return false;
		}
		
		return true;
	}

}
