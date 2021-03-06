/*
 * Copyright (c) 2014																 
 *	Mikol Faro			<mikol.faro@gmail.com>
 *	Simone Mangano		<simone.mangano@ieee.org>
 *	Mattia Tortorelli	<mattia.tortorelli@gmail.com>
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package org.biokoframework.systema.validators;

import org.biokoframework.system.service.validation.AbstractValidator;
import org.biokoframework.systema.command.ValidatedCommand;
import org.biokoframework.systema.commons.SystemARepositories;
import org.biokoframework.systema.entity.dummy2.DummyEntity2;
import org.biokoframework.utils.domain.ErrorEntity;
import org.biokoframework.utils.fields.Fields;
import org.biokoframework.utils.repository.Repository;
import org.biokoframework.utils.validation.ValidationErrorBuilder;

import java.util.List;

public class UniqueOptionalIntegerValidator extends AbstractValidator {

	@Override
	public void validate(Fields input, List<ErrorEntity> errors) {
		Repository<DummyEntity2> dummy2Repo = _context.getRepository(SystemARepositories.DUMMY2);
		
		Long valueToTest = input.get(ValidatedCommand.INTEGER_OPTIONAL_FIELD);
		List<DummyEntity2> result = dummy2Repo.getEntitiesByForeignKey(DummyEntity2.VALUE, valueToTest);
		
		if (!result.isEmpty())
			errors.add(ValidationErrorBuilder.buildUniqueViolationError(ValidatedCommand.INTEGER_OPTIONAL_FIELD));
		
	}

}
