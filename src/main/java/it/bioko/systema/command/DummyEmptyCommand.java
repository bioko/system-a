package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericFieldNames;
import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.fields.Fields;

import java.util.ArrayList;


public class DummyEmptyCommand extends Command {

	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);
		// do nothing
		logOutput();
		return Fields.single(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

	@Override
	public String getName() {		
		return this.getClass().getSimpleName();
	}

}
