package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericFieldNames;
import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.system.repository.core.Repository;
import it.bioko.system.repository.core.SafeRepositoryHelper;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.systema.entity.dummy2.DummyEntity2;
import it.bioko.systema.factory.SystemACommands;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.fields.Fields;

import java.util.ArrayList;

public class ValidatedCommand extends Command{

	public static final String		TEXT_MANDATORY_FIELD = "textMandatoryField";
	public static final String		TEXT_OPTIONAL_FIELD = "textOptionalField";
	public static final String		INTEGER_OPTIONAL_FIELD = "integerOptionalField";


	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);

		// save text mandatory field into dummy1 entity repo
		Repository<DummyEntity1> dummy1Repo = _context.getRepository(SystemARepositories.DUMMY1);
		DummyEntity1 dummy1 = new DummyEntity1(Fields.empty());
		dummy1.set(DummyEntity1.VALUE, input.stringNamed(TEXT_MANDATORY_FIELD));
		dummy1 = SafeRepositoryHelper.save(dummy1Repo, dummy1, _context);

		// save integer optional field into dummy2
		String integerFieldValue = input.stringNamed(INTEGER_OPTIONAL_FIELD); 
		if (integerFieldValue!=null && !integerFieldValue.isEmpty()) {
			Repository<DummyEntity2> dummy2Repo = _context.getRepository(SystemARepositories.DUMMY2);
			DummyEntity2 dummy2 = new DummyEntity2(Fields.empty());
			dummy2.set(DummyEntity2.VALUE, input.stringNamed(INTEGER_OPTIONAL_FIELD));
			dummy2.set(DummyEntity2.ENTITY1_ID, dummy1.getId());
			dummy2 = SafeRepositoryHelper.save(dummy2Repo, dummy2, _context);
		}

		logOutput();
		return Fields.single(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

	@Override
	public String getName() {
		return SystemACommands.VALIDATED_COMMAND;
	}



}
