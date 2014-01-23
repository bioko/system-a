package it.bioko.systema.entity.dummy1;

import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyEntity1 extends DomainEntity {

	public static final String ENTITY_KEY = "dummyEntity1Id";
	
	@Field
	public static final String VALUE = "value";

	public DummyEntity1(Fields input) {
		super(input);
	}

}
