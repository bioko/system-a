package it.bioko.systema.entity.dummy2;

import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyEntity2 extends DomainEntity {

	public static final String ENTITY_KEY = "dummyEntity2Id";
	
	@Field
	public static final String VALUE = "value";
	
	@Field(type=DummyEntity1.class)
	public static final String ENTITY1_ID = DummyEntity1.ENTITY_KEY;

	public DummyEntity2(Fields input) {
		super(input);
	}

}
