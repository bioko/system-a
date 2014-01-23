package it.bioko.systema.misc;

import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.fields.Fields;

public class Dummy1Mock {

	public static final String QUADRATO = "quadrato";
	public static final String TRIANGOLO = "triangolo";
	
	private static DummyEntity1 _currentShape = null;

	public static DomainEntity getShape() {
		return _currentShape;
	}

	public static void setShape(String shape) {
		if (shape.equals(QUADRATO))
			_currentShape = getQuadratoShape();
		else if (shape.equals(TRIANGOLO))
			_currentShape = getTriangoloShape();
		
	}

	private static DummyEntity1 getQuadratoShape() {
		Fields input = Fields.empty();		
		input.put(DomainEntity.ID,"1");
		input.put(DummyEntity1.VALUE,"quadrato");
		
		return new DummyEntity1(input);
	}
	
	private static DummyEntity1 getTriangoloShape() {
		Fields input = Fields.empty();		
		input.put(DomainEntity.ID,"2");
		input.put(DummyEntity1.VALUE,"triangolo");
		
		return new DummyEntity1(input);
	}
	
	

}
