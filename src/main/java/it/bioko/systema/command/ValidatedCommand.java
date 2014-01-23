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

package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericFieldNames;
import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.system.repository.core.Repository;
import it.bioko.system.repository.core.SafeRepositoryHelper;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.systema.entity.dummy2.DummyEntity2;
import it.bioko.systema.factory.SystemACommands;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.fields.Fields;

import java.util.ArrayList;

public class ValidatedCommand extends Command{

	public static final String		TEXT_MANDATORY_FIELD = "textMandatoryField";
	public static final String		TEXT_OPTIONAL_FIELD = "textOptionalField";
	public static final String		INTEGER_OPTIONAL_FIELD = "integerOptionalField";


	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);

		// save text mandatory field into dummy1 entity repo
		Repository<DummyEntity1> dummy1Repo = _context.getRepository(SystemARepositories.DUMMY1);
		DummyEntity1 dummy1 = new DummyEntity1(Fields.empty());
		dummy1.set(DummyEntity1.VALUE, input.stringNamed(TEXT_MANDATORY_FIELD));
		dummy1 = SafeRepositoryHelper.save(dummy1Repo, dummy1, _context);

		// save integer optional field into dummy2
		String integerFieldValue = input.stringNamed(INTEGER_OPTIONAL_FIELD); 
		if (integerFieldValue!=null && !integerFieldValue.isEmpty()) {
			Repository<DummyEntity2> dummy2Repo = _context.getRepository(SystemARepositories.DUMMY2);
			DummyEntity2 dummy2 = new DummyEntity2(Fields.empty());
			dummy2.set(DummyEntity2.VALUE, input.stringNamed(INTEGER_OPTIONAL_FIELD));
			dummy2.set(DummyEntity2.ENTITY1_ID, dummy1.getId());
			dummy2 = SafeRepositoryHelper.save(dummy2Repo, dummy2, _context);
		}

		logOutput();
		return Fields.single(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

	@Override
	public String getName() {
		return SystemACommands.VALIDATED_COMMAND;
	}



}
