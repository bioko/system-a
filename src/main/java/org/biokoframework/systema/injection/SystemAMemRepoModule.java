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

package org.biokoframework.systema.injection;

import org.biokoframework.system.entity.authentication.Authentication;
import org.biokoframework.system.entity.authentication.EmailConfirmation;
import org.biokoframework.system.entity.authentication.PasswordReset;
import org.biokoframework.system.entity.binary.BinaryEntity;
import org.biokoframework.system.entity.login.Login;
import org.biokoframework.system.entity.template.Template;
import org.biokoframework.system.entity.template.TemplateBuilder;
import org.biokoframework.system.factory.binary.BinaryEntityRepositoryFactory;
import org.biokoframework.system.repository.memory.InMemoryRepository;
import org.biokoframework.system.services.RepositoryModule;
import org.biokoframework.systema.entity.dummy1.DummyEntity1;
import org.biokoframework.systema.entity.dummy2.DummyEntity2;
import org.biokoframework.systema.entity.dummy3.DummyEntity3;
import org.biokoframework.systema.entity.dummy6.DummyEntity6;
import org.biokoframework.systema.entity.dummyComplex.DummyComplexDomainEntity;
import org.biokoframework.systema.entity.dummyWithInteger.DummyEntityWithInteger;
import org.biokoframework.utils.exception.ValidationException;
import org.biokoframework.utils.repository.Repository;
import org.biokoframework.utils.repository.RepositoryException;

import com.google.inject.TypeLiteral;

/**
 * 
 * @author Mikol Faro <mikol.faro@gmail.com>
 * @date Feb 10, 2014
 *
 */
public class SystemAMemRepoModule extends RepositoryModule {

	@Override
	protected void configureRepositories() throws RepositoryException {
		bind(new TypeLiteral<Repository<Login>>(){}).
		toInstance(new InMemoryRepository<>(Login.class));
		
		bind(new TypeLiteral<Repository<Authentication>>(){}).
		toInstance(new InMemoryRepository<>(Authentication.class));
		
		bind(new TypeLiteral<Repository<PasswordReset>>(){}).
		toInstance(new InMemoryRepository<>(PasswordReset.class));
		
		bind(new TypeLiteral<Repository<EmailConfirmation>>(){}).
		toInstance(new InMemoryRepository<>(EmailConfirmation.class));
		
		bind(new TypeLiteral<Repository<DummyEntity1>>(){}).
		toInstance(new InMemoryRepository<>(DummyEntity1.class));
		
		bind(new TypeLiteral<Repository<DummyEntity2>>(){}).
		toInstance(new InMemoryRepository<>(DummyEntity2.class));
		
		bind(new TypeLiteral<Repository<DummyEntity3>>(){}).
		toInstance(new InMemoryRepository<>(DummyEntity3.class));
		
		bind(new TypeLiteral<Repository<DummyEntity6>>(){}).
		toInstance(new InMemoryRepository<>(DummyEntity6.class));
		
		bind(new TypeLiteral<Repository<DummyComplexDomainEntity>>(){}).
		toInstance(new InMemoryRepository<>(DummyComplexDomainEntity.class));
		
		bind(new TypeLiteral<Repository<DummyEntityWithInteger>>(){}).
		toInstance(new InMemoryRepository<>(DummyEntityWithInteger.class));
		
		bind(new TypeLiteral<Repository<BinaryEntity>>(){}).
		toInstance(BinaryEntityRepositoryFactory.createForTemp("system-a", 
				new InMemoryRepository<>(BinaryEntity.class)));
		
//		bind(new TypeLiteral<BinaryEntityRepository>(){}).
//		annotatedWith(Names.named(SystemARepositories.BLOB_REPO_FOR_MULTIPART)).
//		toInstance(BinaryEntityRepositoryFactory.createForTemp("system-a", 
//				new InMemoryRepository<>(BinaryEntity.class)));
		
		InMemoryRepository<Template> templateRepo = new InMemoryRepository<>(Template.class);
		prepareTemplates(templateRepo);
		bind(new TypeLiteral<Repository<Template>>(){}).
		toInstance(templateRepo);
		
	}

	private void prepareTemplates(InMemoryRepository<Template> templateRepo) throws RepositoryException {
		try {
			templateRepo.save(new TemplateBuilder().loadExample(TemplateBuilder.SYSTEM_A_TEMPLATE).build(false));
		} catch (ValidationException exception) {
			addError(exception);
		}
	}

}
