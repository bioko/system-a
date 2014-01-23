package it.bioko.systema.entity.dummy4;

import it.bioko.utils.domain.EntityBuilder;

import java.util.ArrayList;

public class DummyEntity4Builder extends EntityBuilder<DummyEntity4> {

	public static final String EXAMPLE = "example";
	private static final String EXAMPLE_JSON = "{'id':'1','dummyEntity1Id':[],'value':'lino4'}";

	public DummyEntity4Builder() {
		super(DummyEntity4.class);
		
		putExample(EXAMPLE, EXAMPLE_JSON);
	}

	@Override
	public EntityBuilder<DummyEntity4> loadDefaultExample() {
		return loadExample(EXAMPLE);
	}

	public void setEntity1(ArrayList<String> references) {
		_currentFields.put(DummyEntity4.ENTITY1_ID, references);
	}

}
