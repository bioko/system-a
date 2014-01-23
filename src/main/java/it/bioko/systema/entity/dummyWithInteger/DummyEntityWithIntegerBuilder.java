package it.bioko.systema.entity.dummyWithInteger;

import it.bioko.utils.domain.EntityBuilder;

public class DummyEntityWithIntegerBuilder extends EntityBuilder<DummyEntityWithInteger> {

	public static final String EXAMPLE1 = "example1";
	public static final String EXAMPLE2 = "example2";
	public static final String EXAMPLE3 = "example3";
	public static final String EXAMPLE4 = "example4";
	public static final String EXAMPLE5 = "example5";
	
	

	public DummyEntityWithIntegerBuilder() {
		super(DummyEntityWithInteger.class);
		
		putExample(EXAMPLE1, "{ 'id':'1', 'value':'1' } ");
		putExample(EXAMPLE2, "{ 'id':'2', 'value':'2' } ");
		putExample(EXAMPLE3, "{ 'id':'3', 'value':'3' } ");
		putExample(EXAMPLE4, "{ 'id':'4', 'value':'4' } ");
		putExample(EXAMPLE5, "{ 'id':'5', 'value':'5' } ");
		
		
	}

	@Override
	public EntityBuilder<DummyEntityWithInteger> loadDefaultExample() {
		loadExample(EXAMPLE1);
		return this;
	}

}
