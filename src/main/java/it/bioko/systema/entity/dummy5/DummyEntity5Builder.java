package it.bioko.systema.entity.dummy5;

import it.bioko.systema.entity.dummy4.DummyEntity4;
import it.bioko.utils.domain.EntityBuilder;

import java.util.ArrayList;

public class DummyEntity5Builder extends EntityBuilder<DummyEntity5> {

	public static final String EXAMPLE = "example";
	private static final String EXAMPLE_JSON = "{'id':'1','list':[],'value':'vino5'}";

	public DummyEntity5Builder() {
		super(DummyEntity5.class);
		putExample(EXAMPLE, EXAMPLE_JSON);
	}

	@Override
	public EntityBuilder<DummyEntity5> loadDefaultExample() {
		return loadExample(EXAMPLE);
	}

	public void setList(ArrayList<DummyEntity4> entityList) {
		_currentFields.put(DummyEntity5.LIST, entityList);
	}
}
