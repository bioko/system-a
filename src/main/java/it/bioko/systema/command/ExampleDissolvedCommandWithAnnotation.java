package it.bioko.systema.command;

import it.bioko.system.command.entityDependencies.DissolvedCommand;
import it.bioko.system.repository.core.Repository;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.systema.entity.dummy2.DummyEntity2;

public class ExampleDissolvedCommandWithAnnotation extends DissolvedCommand {
	
	@Override
	public void onContextInitialized() {
		
		Repository<DummyEntity1> dummyEntity1Repo = _context.getRepository(SystemARepositories.DUMMY1);
		Repository<DummyEntity2> dummyEntity2Repo = _context.getRepository(SystemARepositories.DUMMY2);
		
		savingIn(dummyEntity1Repo, DummyEntity1.class);
		savingIn(dummyEntity2Repo, DummyEntity2.class);
		
	}

}
