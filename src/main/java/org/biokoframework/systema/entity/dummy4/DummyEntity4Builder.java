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

package org.biokoframework.systema.entity.dummy4;

import java.util.ArrayList;

import org.biokoframework.utils.domain.EntityBuilder;

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
