package it.bioko.systema.entity.dummyComplex;

import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.domain.annotation.field.Field;
import it.bioko.utils.fields.Fields;

@SuppressWarnings("serial")
public class DummyComplexDomainEntity extends DomainEntity {

	@Field
	public static final String	A_STRING_MANDATORY_FIELD	=	"aStringMandatoryField";
	@Field(mandatory=false)
	public static final String	A_STRING_OPTIONAL_FIELD	=	"aStringOptionalField";
	@Field(type=Integer.class, mandatory=false)
	public static final String	AN_INTEGER_OPTIONAL_FIELD	=	"anIntegerOptionalField";
	@Field
	public static final String	A_STRING_FIELD_MANDATORY_ALSO_IN_GET	=	"aStringFieldMandatoryAlsoInGet";
	
	public DummyComplexDomainEntity(Fields input) {
		super(input);
	}
	
	
	
	
	
	
	

}
