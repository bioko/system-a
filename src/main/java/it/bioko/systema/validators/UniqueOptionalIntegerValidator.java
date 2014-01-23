package it.bioko.systema.validators;

import it.bioko.system.repository.core.Repository;
import it.bioko.system.service.validation.AbstractValidator;
import it.bioko.systema.command.ValidatedCommand;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummy2.DummyEntity2;
import it.bioko.utils.domain.ErrorEntity;
import it.bioko.utils.fields.Fields;
import it.bioko.utils.validator.ValidatorErrorBuilder;

import java.util.List;

public class UniqueOptionalIntegerValidator extends AbstractValidator {

	@Override
	public void validate(Fields input, List<ErrorEntity> errors) {
		Repository<DummyEntity2> dummy2Repo = _context.getRepository(SystemARepositories.DUMMY2);
		
		String valueToTest = input.stringNamed(ValidatedCommand.INTEGER_OPTIONAL_FIELD);
		List<DummyEntity2> result = dummy2Repo.getEntitiesByForeignKey(DummyEntity2.VALUE, valueToTest);
		
		if (!result.isEmpty())
			errors.add(ValidatorErrorBuilder.buildUniqueViolationError(ValidatedCommand.INTEGER_OPTIONAL_FIELD));
		
	}

}
