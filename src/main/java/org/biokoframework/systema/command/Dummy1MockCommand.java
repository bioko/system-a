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

import java.util.ArrayList;

import org.biokoframework.system.KILL_ME.commons.GenericFieldNames;
import org.biokoframework.system.command.Command;
import org.biokoframework.system.command.CommandException;
import org.biokoframework.systema.commons.SystemACommandNames;
import org.biokoframework.systema.misc.Dummy1Mock;
import org.biokoframework.utils.domain.DomainEntity;
import org.biokoframework.utils.fields.Fields;

public class Dummy1MockCommand extends Command {

	@Override
	public Fields execute(Fields input) throws CommandException {

		Fields result = new Fields();
		
		
		
		ArrayList<DomainEntity> responsEntities = new ArrayList<DomainEntity>();
		responsEntities.add(Dummy1Mock.getShape());
		result.put(GenericFieldNames.RESPONSE, responsEntities );
		
		
		return result;
	}

	@Override
	public String getName() {
		return SystemACommandNames.DUMMY1_MOCK_COMMAND;		
	}

}
