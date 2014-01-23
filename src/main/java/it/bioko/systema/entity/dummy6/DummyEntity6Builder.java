package it.bioko.systema.entity.dummy6;

import it.bioko.utils.domain.EntityBuilder;

public class DummyEntity6Builder extends EntityBuilder<DummyEntity6> {

	private static final String EXAMPLE1 = "example1";

	public DummyEntity6Builder() {
		super(DummyEntity6.class);

		putExample(EXAMPLE1, "{'id':'1','value':'martino66'}");
	}

	@Override
	public EntityBuilder<DummyEntity6> loadDefaultExample() {
		return loadExample(EXAMPLE1);
	}

}
