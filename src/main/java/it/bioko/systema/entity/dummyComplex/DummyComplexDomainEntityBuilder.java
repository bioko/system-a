package it.bioko.systema.entity.dummyComplex;

import it.bioko.utils.domain.EntityBuilder;

public class DummyComplexDomainEntityBuilder extends EntityBuilder<DummyComplexDomainEntity> {

	public static final String EXAMPLE1 = "example1";

	
	public DummyComplexDomainEntityBuilder() {
		super(DummyComplexDomainEntity.class);
		
		putExample(EXAMPLE1, "{'id': '1', " +
				"'aStringMandatoryField': 'I am a string', " +
				"'aStringOptionalField': 'I am another string', " +
				"'anIntegerOptionalField':'32', " +
				"'aStringFieldMandatoryAlsoInGet':'group'" +
				"}");
		
	}

	@Override
	public DummyComplexDomainEntityBuilder loadDefaultExample() {
		loadExample(EXAMPLE1);
		return this;
	}

}
