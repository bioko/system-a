package it.bioko.systema.entity.dummy2;

import it.bioko.utils.domain.EntityBuilder;

public class DummyEntity2Builder extends EntityBuilder<DummyEntity2> {

	private static final String EXAMPLE1 = "example1";
	
	public DummyEntity2Builder() {
		super(DummyEntity2.class);

		putExample(EXAMPLE1, "{'id':'1','dummyEntity1Id':'1','value':'gino2'}");
	}

	@Override
	public EntityBuilder<DummyEntity2> loadDefaultExample() {
		return loadExample(EXAMPLE1);
	}

}
