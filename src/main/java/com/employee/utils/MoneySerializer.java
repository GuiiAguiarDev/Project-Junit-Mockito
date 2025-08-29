package com.employee.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MoneySerializer extends JsonSerializer<BigDecimal> {
	
	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider setializers) throws IOException{
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
		gen.writeString(formatter.format(value));
	}
	
}
