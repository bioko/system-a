package it.bioko.systema.entity.dummyMultipart;

import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyMultipart extends DomainEntity {

	@Field
	public static final String		FIRST_TEXT_FIELD	=	"firstTextField";
	@Field
	public static final String		SECOND_TEXT_FIELD	=	"secondTextField";
	@Field
	public static final String		FIRST_FILE_ID		=	"firstFileId";
	@Field
	public static final String		SECOND_FILE_ID		=	"secondFileId";
	
	
	public DummyMultipart(Fields input) {
		super(input);		
	}
	
	

}
