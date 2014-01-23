package it.bioko.systema.entity.dummy3;

import it.bioko.utils.domain.EntityBuilder;

public class DummyEntity3Builder extends EntityBuilder<DummyEntity3> {

	private static final String EXAMPLE1 = "example1";

	public DummyEntity3Builder() {
		super(DummyEntity3.class);
		
		putExample(EXAMPLE1, "{'id':'1','dummyEntity2Id':'1','value':'tino3'}");
	}

	@Override
	public EntityBuilder<DummyEntity3> loadDefaultExample() {
		return loadExample(EXAMPLE1);
	}
	
}
