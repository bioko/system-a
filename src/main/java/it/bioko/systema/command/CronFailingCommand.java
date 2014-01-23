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

import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.system.context.Context;
import it.bioko.system.exceptions.CommandExceptionsFactory;
import it.bioko.systema.commons.SystemACommandNames;
import it.bioko.utils.fields.Fields;

import org.apache.log4j.Logger;

public class CronFailingCommand extends Command {

	public CronFailingCommand(Context context) {
		_context = context;
	}
	
	public CronFailingCommand() {		
	}
	
	@Override
	public Fields execute(Fields input) throws CommandException {
		
		Logger logger = _context.get(Context.LOGGER);
		logger.info("EXECUTING Command: " + this.getClass().getSimpleName());
		logger.info("INPUT: " + input.asString());

		throw CommandExceptionsFactory.createBadCommandInvocationException();

	}

	@Override
	public String getName() {
		return SystemACommandNames.CRON_FAILING_EXAMPLE;
	}

}
