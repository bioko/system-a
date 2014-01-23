package it.bioko.systema.entity.dummy6;

import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyEntity6 extends DomainEntity {

	@Field
	public static final String VALUE = "value";

	public DummyEntity6(Fields input) {
		super(input);
	}
	


}
