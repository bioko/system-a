package it.bioko.systema.entity.dummy1;

import it.bioko.utils.domain.EntityBuilder;

public class DummyEntity1Builder extends EntityBuilder<DummyEntity1> {

	public static final String EXAMPLE1 = "example1";
	public static final String EXAMPLE2 = "example2";
	public static final String EXAMPLE3 = "example3";

	public DummyEntity1Builder() {
		super(DummyEntity1.class);

		putExample(EXAMPLE1, "{'id':'1','value':'pino1'}");
		putExample(EXAMPLE2, "{'id':'2','value':'Pino1'}");
		putExample(EXAMPLE3, "{'id':'3','value':'PINO1'}");
	}

	@Override
	public EntityBuilder<DummyEntity1> loadDefaultExample() {
		return loadExample(EXAMPLE1);
	}

}
