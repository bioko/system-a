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

package org.biokoframework.systema.command;

import org.biokoframework.system.KILL_ME.commons.GenericFieldNames;
import org.biokoframework.system.command.AbstractCommand;
import org.biokoframework.system.command.CommandException;
import org.biokoframework.system.repository.core.SafeRepositoryHelper;
import org.biokoframework.systema.entity.dummy1.DummyEntity1;
import org.biokoframework.systema.entity.dummy2.DummyEntity2;
import org.biokoframework.utils.domain.DomainEntity;
import org.biokoframework.utils.fields.Fields;
import org.biokoframework.utils.repository.Repository;

import java.util.ArrayList;

public class ValidatedCommand extends AbstractCommand{

	public static final String		TEXT_MANDATORY_FIELD = "textMandatoryField";
	public static final String		TEXT_OPTIONAL_FIELD = "textOptionalField";
	public static final String		INTEGER_OPTIONAL_FIELD = "integerOptionalField";

	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);

		// save text mandatory field into dummy1 entity repo
		Repository<DummyEntity1> dummy1Repo = getRepository(DummyEntity1.class);
		DummyEntity1 dummy1 = new DummyEntity1();
		dummy1.set(DummyEntity1.VALUE, input.get(TEXT_MANDATORY_FIELD));
		dummy1 = SafeRepositoryHelper.save(dummy1Repo, dummy1);

		// save integer optional field into dummy2
		Long integerFieldValue = input.get(INTEGER_OPTIONAL_FIELD); 
		if (integerFieldValue != null) {
			Repository<DummyEntity2> dummy2Repo = getRepository(DummyEntity2.class);
			DummyEntity2 dummy2 = new DummyEntity2();
			dummy2.set(DummyEntity2.VALUE, input.get(INTEGER_OPTIONAL_FIELD));
			dummy2.set(DummyEntity2.ENTITY1_ID, dummy1.getId());
			dummy2 = SafeRepositoryHelper.save(dummy2Repo, dummy2);
		}

		logOutput();
		return new Fields(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

}
