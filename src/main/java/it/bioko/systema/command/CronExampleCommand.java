package it.bioko.systema.command;

import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.system.context.Context;
import it.bioko.systema.commons.SystemACommandNames;
import it.bioko.systema.misc.Dummy1Mock;
import it.bioko.utils.fields.Fields;

import org.apache.log4j.Logger;

public class CronExampleCommand extends Command {

	public CronExampleCommand(Context context) {
		_context = context;
	}
	
	public CronExampleCommand() {		
	}

	@Override
	public Fields execute(Fields input) throws CommandException {
		Logger logger = _context.get(Context.LOGGER);
		logger.info("EXECUTING Command: " + this.getClass().getSimpleName());
		logger.info("INPUT: " + input.asString());

		Dummy1Mock.setShape(Dummy1Mock.QUADRATO);
		
		logger.info("OUTPUT after execution: no output");
		logger.info("END Command: " + this.getClass().getSimpleName());
		return Fields.empty();
	}

	@Override
	public String getName() {
		return SystemACommandNames.CRON_EXAMPLE;
	}

}
