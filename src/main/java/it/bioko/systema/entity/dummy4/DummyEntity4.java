package it.bioko.systema.entity.dummy4;

import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyEntity4 extends DomainEntity {

	public static final String ENTITY_KEY = "dummyEntity4Id";
	
	@Field(type=DummyEntity1.class)
	public static final String ENTITY1_ID = "dummyEntity1Id";
	
	@Field
	public static final String VALUE = "value";
	
	public DummyEntity4(Fields input) {
		super(input);
	}

}
