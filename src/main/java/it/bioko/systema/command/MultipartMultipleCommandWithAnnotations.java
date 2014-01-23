package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericCommandNames;
import it.bioko.system.KILL_ME.commons.HttpMethod;
import it.bioko.system.command.KILL_ME.MultipleCommandPlusPlus;
import it.bioko.system.context.Context;
import it.bioko.systema.factory.SystemACommands;

public class MultipartMultipleCommandWithAnnotations extends MultipleCommandPlusPlus {

	@Override
	public void setContext(Context context) {		
		super.setContext(context);
		
		addStep("postMyBlob", GenericCommandNames.composeRestCommandName(HttpMethod.POST, SystemACommands.MY_BLOB));
		addStep("postDummyEntity1", GenericCommandNames.composeRestCommandName(HttpMethod.POST, SystemACommands.DUMMY_ENTITY1));		
	}

	
	
	
	

}
