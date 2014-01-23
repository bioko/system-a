package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericFieldNames;
import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.utils.fields.Fields;

public class PrintLoginIdCommand extends Command {

	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);
		
		String loginId = input.stringNamed(GenericFieldNames.AUTH_LOGIN_ID);
		if (loginId==null)
			loginId="";
		
		Fields output = Fields.empty();
		output.put("value", loginId);
		
		Fields result = Fields.single(GenericFieldNames.RESPONSE, output);
		
		logOutput(result);
		
		return result;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

}
