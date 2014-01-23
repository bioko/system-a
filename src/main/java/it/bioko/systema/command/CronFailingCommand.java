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
