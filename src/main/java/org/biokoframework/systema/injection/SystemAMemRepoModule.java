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

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.biokoframework.system.ConfigurationEnum;
import org.biokoframework.system.repository.memory.InMemoryRepository;
import org.biokoframework.system.repository.sql.SqlRepository;
import org.biokoframework.system.services.repository.RepositoryModule;

import com.google.inject.name.Names;

/**
 * 
 * @author Mikol Faro <mikol.faro@gmail.com>
 * @date Feb 10, 2014
 *
 */
public class SystemAMemRepoModule extends RepositoryModule {

	public SystemAMemRepoModule(ConfigurationEnum config) {
		super(config);
	}

	@Override
	protected void configureForDev() {
		bind(File.class).annotatedWith(Names.named("fileBaseDirectory")).toInstance(FileUtils.getTempDirectory());
		
		bindRepositoryTo(InMemoryRepository.class);
	}
	
	@Override
	protected void configureForDemo() {
		
		bind(File.class).annotatedWith(Names.named("fileBaseDirectory")).toInstance(FileUtils.getTempDirectory());
		
		bindRepositoryTo(SqlRepository.class);
	}
	
	@Override
	protected void configureForProd() {
		configureForDemo();
	}
	
//		bind(new TypeLiteral<BinaryEntityRepository>(){}).
//		annotatedWith(Names.named(SystemARepositories.BLOB_REPO_FOR_MULTIPART)).
//		toInstance(BinaryEntityRepositoryFactory.createForTemp("system-a", 
//				new InMemoryRepository<>(BinaryEntity.class)));
//
//		InMemoryRepository<Template> templateRepo = new InMemoryRepository<>(Template.class);
//		prepareTemplates(templateRepo);
//		
//		
//	private void prepareTemplates(InMemoryRepository<Template> templateRepo) throws RepositoryException {
//		try {
//			templateRepo.save(new TemplateBuilder().loadExample(TemplateBuilder.SYSTEM_A_TEMPLATE).build(false));
//		} catch (ValidationException exception) {
//			addError(exception);
//		}
//	}

}
