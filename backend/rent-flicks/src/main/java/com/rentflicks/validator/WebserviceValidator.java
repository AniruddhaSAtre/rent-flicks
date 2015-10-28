package com.rentflicks.validator;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rentflicks.error.ErrorObject;
import com.rentflicks.error.ErrorType;

@ControllerAdvice
public class WebserviceValidator {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorObject handleValidationError(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();
		List<ObjectError> error = result.getAllErrors();
		return new ErrorObject(error.get(0).getDefaultMessage(), ErrorType.ValidationError);
	}
}
