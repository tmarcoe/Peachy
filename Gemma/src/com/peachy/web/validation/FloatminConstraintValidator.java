package com.peachy.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public  class FloatminConstraintValidator implements ConstraintValidator<FloatMin, Double>{
	double min;
	
	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if (value < min) {
			return false;
		}
		return true;
	}

	@Override
	public void initialize(FloatMin floatMin) {
		this.min = floatMin.value();
	}

}
