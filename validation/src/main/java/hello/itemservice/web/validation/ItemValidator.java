package hello.itemservice.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import hello.itemservice.domain.item.Item;

@Component
public class ItemValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Item.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Item item = (Item) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");
	
		// if(item)
	}
	
	
}
