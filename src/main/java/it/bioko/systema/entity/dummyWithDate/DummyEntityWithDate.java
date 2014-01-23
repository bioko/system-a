package it.bioko.systema.entity.dummyWithDate;

import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;
import it.bioko.utils.validator.Validator;

import org.joda.time.DateTime;

@SuppressWarnings("serial")
public class DummyEntityWithDate extends DomainEntity {

	@Field(type=DateTime.class, dateFormat=Validator.ISO_TIMESTAMP)
	public static final String VALUE = "value";
	
	
	public DummyEntityWithDate(Fields input) {
		super(input);		
	}

	


}
