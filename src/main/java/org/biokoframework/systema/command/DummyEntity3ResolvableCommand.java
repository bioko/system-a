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

import org.biokoframework.system.command.CommandException;
import org.biokoframework.system.command.entityDependencies.ResolvableCommand;
import org.biokoframework.systema.commons.SystemARepositories;
import org.biokoframework.systema.entity.dummy1.DummyEntity1;
import org.biokoframework.systema.entity.dummy2.DummyEntity2;
import org.biokoframework.utils.repository.Repository;

public class DummyEntity3ResolvableCommand extends ResolvableCommand {

	public DummyEntity3ResolvableCommand() {
		super(null);

	}
	
	@Override
	public void onContextInitialized()  {
		try {
			_baseCommand = _context.getCommandHandler().getByName("GET_dummy-entity3");
		} catch (CommandException e) {			
			e.printStackTrace();
		}
		
		Repository<DummyEntity1> dummy1Repo = _context.getRepository(SystemARepositories.DUMMY1);
		Repository<DummyEntity2> dummy2Repo = _context.getRepository(SystemARepositories.DUMMY2);
		
		with(dummy1Repo, DummyEntity1.class);
		with(dummy2Repo, DummyEntity2.class);
	}

	
	
}
