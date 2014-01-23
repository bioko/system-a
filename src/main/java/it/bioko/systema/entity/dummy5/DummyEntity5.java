package it.bioko.systema.entity.dummy5;

import it.bioko.systema.entity.dummy4.DummyEntity4;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyEntity5 extends DomainEntity {

	public static final String ENTITY_KEY = "dummyEntity5Id";

	@Field(type=DummyEntity4.class)
	public static final String LIST = "list";
	
	@Field
	public static final String VALUE = "value";

	
	public DummyEntity5(Fields input) {
		super(input);
	}
	
}
