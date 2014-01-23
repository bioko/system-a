package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericFieldNames;
import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.systema.commons.SystemACommandNames;
import it.bioko.systema.misc.Dummy1Mock;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.fields.Fields;

import java.util.ArrayList;

public class Dummy1MockCommand extends Command {

	@Override
	public Fields execute(Fields input) throws CommandException {

		Fields result = Fields.empty();
		
		
		
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
