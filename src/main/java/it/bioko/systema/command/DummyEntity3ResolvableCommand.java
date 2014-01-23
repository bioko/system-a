package it.bioko.systema.command;

import it.bioko.system.command.CommandException;
import it.bioko.system.command.entityDependencies.ResolvableCommand;
import it.bioko.system.repository.core.Repository;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.systema.entity.dummy2.DummyEntity2;

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
