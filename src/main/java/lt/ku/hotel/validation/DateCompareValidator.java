package lt.ku.hotel.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class DateCompareValidator implements ConstraintValidator<DateCompare, Object>{
	private String arrivalDate;
	private String departureDate;
	@Override
	public void initialize(DateCompare constraintAnnotation) {
		
		this.arrivalDate = constraintAnnotation.arrivalDate();
		this.departureDate = constraintAnnotation.departureDate();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			LocalDate firstValue = LocalDate.parse(new BeanWrapperImpl(value)
				.getPropertyValue(arrivalDate).toString());
		LocalDate secondValue = LocalDate.parse(new BeanWrapperImpl(value)
				.getPropertyValue(departureDate).toString());
		if(firstValue.isAfter(secondValue) || firstValue.equals(secondValue)) return false;
		return true;
		}catch(DateTimeParseException e) {
			return true;
		}
	
		
		
		
	}
	

}
