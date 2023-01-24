package com.jrp.pma.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueValidator.class)
public @interface UniqueValue {

	//this message will display when there's an error
	String message() default "Unique Constraint voilated";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
}
