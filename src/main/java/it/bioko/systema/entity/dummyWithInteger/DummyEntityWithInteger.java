package it.bioko.systema.entity.dummyWithInteger;

import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyEntityWithInteger extends DomainEntity {

	@Field(type=Integer.class)
	public static final String VALUE = "value";
	
	
	public DummyEntityWithInteger(Fields input) {
		super(input);		
	}

	


}
