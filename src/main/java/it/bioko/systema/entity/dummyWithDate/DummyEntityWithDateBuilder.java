package it.bioko.systema.entity.dummyWithDate;

import it.bioko.utils.domain.EntityBuilder;

public class DummyEntityWithDateBuilder extends EntityBuilder<DummyEntityWithDate> {

	public static final String EXAMPLE1 = "example1";
	public static final String EXAMPLE2 = "example2";
	public static final String EXAMPLE3 = "example3";
	public static final String EXAMPLE4 = "example4";
	public static final String EXAMPLE5 = "example5";
	
	

	public DummyEntityWithDateBuilder() {
		super(DummyEntityWithDate.class);
		
		putExample(EXAMPLE1, "{ 'id':'1', 'value':'2013-11-20T10:00:00+0100' } ");
		putExample(EXAMPLE2, "{ 'id':'2', 'value':'2013-11-20T10:05:00+0100' } ");
		putExample(EXAMPLE3, "{ 'id':'3', 'value':'2013-11-20T10:10:00+0100' } ");
		putExample(EXAMPLE4, "{ 'id':'4', 'value':'2013-11-21T10:00:00+0100' } ");
		putExample(EXAMPLE5, "{ 'id':'5', 'value':'2013-11-22T08:00:00+0100' } ");
		
		
	}

	@Override
	public EntityBuilder<DummyEntityWithDate> loadDefaultExample() {
		loadExample(EXAMPLE1);
		return this;
	}

}
