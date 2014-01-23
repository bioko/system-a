/*
 * Copyright (c) 2014																 
 *	Mikol Faro			<mikol.faro@gmail.com>
 *	Simone Mangano		<simone.mangano@ieee.org>
 *	Mattia Tortorelli	<mattia.tortorelli@gmail.com>
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package org.biokoframework.systema.misc;

import org.biokoframework.systema.entity.dummy1.DummyEntity1;
import org.biokoframework.utils.domain.DomainEntity;
import org.biokoframework.utils.fields.Fields;

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
