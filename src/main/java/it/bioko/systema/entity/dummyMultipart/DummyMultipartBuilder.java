package it.bioko.systema.entity.dummyMultipart;

import it.bioko.utils.domain.EntityBuilder;

public class DummyMultipartBuilder extends EntityBuilder<DummyMultipart> {

	public static final String EXAMPLE1 = "example1";

	public DummyMultipartBuilder() {
		super(DummyMultipart.class);
		
		putExample(EXAMPLE1, "{" +
				"'id':'1'," +
				"'firstTextField':'firstTextValue'," +
				"'secondTextField':'secondTextValue'," +
				"'firstFileId':'1'," +
				"'secondFileId':'2'" +
				"}");
	}

	@Override
	public EntityBuilder<DummyMultipart> loadDefaultExample() {
		loadExample(EXAMPLE1);
		return this;
	}

}
