package it.bioko.systema.entity.dummy3;

import it.bioko.systema.entity.dummy2.DummyEntity2;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyEntity3 extends DomainEntity {

	public static final String ENTITY_KEY = "dummyEntity3Id";
	
	@Field(type=DummyEntity2.class)
	public static final String ENTITY2_ID = "dummyEntity2Id";
	
	@Field
	public static final String VALUE = "value";

	public DummyEntity3(Fields input) {
		super(input);
	}

}
