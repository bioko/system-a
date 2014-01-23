package it.bioko.systema.validators;



import it.bioko.system.repository.core.Repository;
import it.bioko.system.service.validation.AbstractValidator;
import it.bioko.systema.command.ValidatedCommand;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.utils.domain.ErrorEntity;
import it.bioko.utils.fields.Fields;
import it.bioko.utils.validator.ValidatorErrorBuilder;

import java.util.List;

public class UniqueTextMandatoryFieldValidator extends AbstractValidator {

	@Override
	public void validate(Fields input, List<ErrorEntity> errors) {
		Repository<DummyEntity1> dummy1Repo = _context.getRepository(SystemARepositories.DUMMY1);
		
		String valueToTest = input.stringNamed(ValidatedCommand.TEXT_MANDATORY_FIELD);
		List<DummyEntity1> result = dummy1Repo.getEntitiesByForeignKey(DummyEntity1.VALUE, valueToTest);
		
		if (!result.isEmpty())
			errors.add(ValidatorErrorBuilder.buildUniqueViolationError(ValidatedCommand.TEXT_MANDATORY_FIELD));
		
		
	}

}
