package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entity.OrderItem;

@Component
public class InventoryValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return OrderItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderItem orderItem =(OrderItem)target;
		if (orderItem.getAmount()==null || orderItem.getAmount()==0) {
			//"驗證欄位" , "設定驗證名稱(隨意取)" , 錯誤名稱
			errors.rejectValue("amount", "orderitem.amount.required" , "輸入不可空白");
		}
	}

}
