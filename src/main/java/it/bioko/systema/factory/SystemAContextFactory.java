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

package it.bioko.systema.factory;

import it.bioko.system.ConfigurationEnum;
import it.bioko.system.KILL_ME.exception.SystemException;
import it.bioko.system.command.ValidationException;
import it.bioko.system.context.Context;
import it.bioko.system.entity.authentication.Authentication;
import it.bioko.system.entity.authentication.EmailConfirmation;
import it.bioko.system.entity.authentication.PasswordReset;
import it.bioko.system.entity.binary.BinaryEntity;
import it.bioko.system.entity.login.Login;
import it.bioko.system.entity.template.Template;
import it.bioko.system.entity.template.TemplateBuilder;
import it.bioko.system.factory.binary.BinaryEntityRepository;
import it.bioko.system.factory.binary.BinaryEntityRepositoryFactory;
import it.bioko.system.repository.core.RepositoryException;
import it.bioko.system.repository.memory.InMemoryRepository;
import it.bioko.system.repository.sql.MySQLConnector;
import it.bioko.system.repository.sql.SqlConstants;
import it.bioko.system.repository.sql.SqlRepository;
import it.bioko.system.service.context.AbstractContextFactory;
import it.bioko.system.service.currenttime.CurrentTimeService;
import it.bioko.system.service.random.RandomGeneratorService;
import it.bioko.systema.commons.SystemAConstants;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.systema.entity.dummy1.DummyEntity1;
import it.bioko.systema.entity.dummy2.DummyEntity2;
import it.bioko.systema.entity.dummy3.DummyEntity3;
import it.bioko.systema.entity.dummy6.DummyEntity6;
import it.bioko.systema.entity.dummyComplex.DummyComplexDomainEntity;
import it.bioko.systema.entity.dummyMultipart.DummyMultipart;
import it.bioko.systema.entity.dummyWithInteger.DummyEntityWithInteger;
import it.bioko.systema.misc.TestShutdownListener;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class SystemAContextFactory extends AbstractContextFactory {

	public static final String MEMORY = "memory";

	public SystemAContextFactory() {
		super();		
	}

	@Override
	protected Context configureForProd(Context context) throws RepositoryException {		
		addInMemoryRepositories(context);
		//setSqlRepos(context);
		
		context.addSystemListener(new TestShutdownListener());
		
		return context;
	}

	@Override
	protected Context configureForDev(Context context) throws RepositoryException {
		Logger.getLogger("systemA").setLevel(Level.DEBUG);
		
		addInMemoryRepositories(context);
//		setSqlRepos(context);
		
		CurrentTimeService timeService = new CurrentTimeService(ConfigurationEnum.DEV);
		context.put(SystemAConstants.CONTEXT_CURRENT_TIME_SERVICE, timeService);
		
		RandomGeneratorService randomService = new RandomGeneratorService(ConfigurationEnum.DEV);
		context.put(SystemAConstants.CONTEXT_RANDOM_GENERATOR_SERVICE, randomService);
		
		context.addSystemListener(new TestShutdownListener());
		
		return context;
	}
	
	@Override
	protected Context configureForDemo(Context context) throws SystemException {
		Logger.getLogger("systemA").setLevel(Level.DEBUG);
		
		addSqlRepositories(context);
		
		context.addSystemListener(new TestShutdownListener());
		
		return context;
	}

	@Override
	protected Logger getSystemLogger() {
		return Logger.getLogger("systemA");
	}
	
	
	////////////////////////////////////////////////////////////
		
	private void addInMemoryRepositories(Context context) throws RepositoryException {
		
		////////////// login stuff
		InMemoryRepository<Login> loginRepo = new InMemoryRepository<Login>(Login.class);
		context.addRepository(SystemARepositories.LOGIN, loginRepo);
		
		InMemoryRepository<Authentication> authRepo = new InMemoryRepository<Authentication>(Authentication.class);
		context.addRepository(SystemARepositories.AUTHENTICATION_REPOSITORY, authRepo);
		
		InMemoryRepository<PasswordReset> passResetRepo = new InMemoryRepository<PasswordReset>(PasswordReset.class);
		context.addRepository(SystemARepositories.PASSWORD_RESET, passResetRepo);
		
		InMemoryRepository<EmailConfirmation> emailConfirmRepo = new InMemoryRepository<EmailConfirmation>(EmailConfirmation.class);
		context.addRepository(SystemARepositories.EMAIL_CONFIRMATION, emailConfirmRepo);
		
		//////////////// dummy entities //////
		InMemoryRepository<DummyEntity1> dummy1Repo = new InMemoryRepository<DummyEntity1>(DummyEntity1.class);
		context.addRepository(SystemARepositories.DUMMY1, dummy1Repo);
		
		InMemoryRepository<DummyEntity2> dummy2Repo = new InMemoryRepository<DummyEntity2>(DummyEntity2.class);
		context.addRepository(SystemARepositories.DUMMY2, dummy2Repo);
		
		InMemoryRepository<DummyEntity3> dummy3Repo = new InMemoryRepository<DummyEntity3>(DummyEntity3.class);
		context.addRepository(SystemARepositories.DUMMY3, dummy3Repo);
		
		InMemoryRepository<DummyEntity6> dummy6Repo = new InMemoryRepository<DummyEntity6>(DummyEntity6.class);
		context.addRepository(SystemARepositories.DUMMY6, dummy6Repo);
		
		InMemoryRepository<DummyComplexDomainEntity> dummyComplexDERepo = new InMemoryRepository<DummyComplexDomainEntity>(DummyComplexDomainEntity.class);
		context.addRepository(SystemARepositories.DUMMY_COMPLEX_DE_REPO, dummyComplexDERepo);
// TODO aggiungere a SQL da qui in giu		
		InMemoryRepository<DummyEntityWithInteger> dummyWithInteger = new InMemoryRepository<DummyEntityWithInteger>(DummyEntityWithInteger.class);
		context.addRepository(SystemARepositories.DUMMY_WITH_INTEGER_DE_REPO, dummyWithInteger);
		
		
		///////////////// blob stuff //////
		BinaryEntityRepository blobRepo = BinaryEntityRepositoryFactory.createForTemp(context.getSystemName(), new InMemoryRepository<BinaryEntity>(BinaryEntity.class));
		context.addRepository(SystemARepositories.BLOB, blobRepo);
		BinaryEntityRepository blobRepoForMultipartCommand = BinaryEntityRepositoryFactory.createForTemp(context.getSystemName(), new InMemoryRepository<BinaryEntity>(BinaryEntity.class));
		context.addRepository(SystemARepositories.BLOB_REPO_FOR_MULTIPART, blobRepoForMultipartCommand);
		InMemoryRepository<DummyMultipart> dummyMultipartRepo = new InMemoryRepository<DummyMultipart>(DummyMultipart.class);
		context.addRepository(SystemARepositories.DUMMY_MULTIPART_REPO, dummyMultipartRepo);
		
		InMemoryRepository<Template> templateRepo = new InMemoryRepository<Template>(Template.class);
		context.addRepository(SystemARepositories.TEMPLATE_REPO, templateRepo);
		try {
			templateRepo.save(new TemplateBuilder().loadExample(TemplateBuilder.SYSTEM_A_TEMPLATE).build(false));
		} catch (ValidationException exception) {
			System.err.println("[easy-men] something went wrong populating the template repository");
			exception.printStackTrace();
		}

	}
	
	private void addSqlRepositories(Context context) throws RepositoryException {
		String dbUrl = context.getSystemProperty(SqlConstants.DB_URL);
		String dbName = context.getSystemProperty(SqlConstants.DB_NAME);
		String dbUser = context.getSystemProperty(SqlConstants.DB_USER);
		String dbPassword = context.getSystemProperty(SqlConstants.DB_PASSWORD);
		String dbPort = context.getSystemProperty(SqlConstants.DB_PORT);
		
		MySQLConnector connectionHelper = new MySQLConnector(dbUrl, dbName, dbUser, dbPassword, dbPort);
		
		//////////////login stuff
		SqlRepository<Login> loginRepo = new SqlRepository<Login>(Login.class, connectionHelper);
		context.addRepository(SystemARepositories.LOGIN, loginRepo);
		SqlRepository<Authentication> authRepo = new SqlRepository<Authentication>(Authentication.class, connectionHelper);
		context.addRepository(SystemARepositories.AUTHENTICATION_REPOSITORY, authRepo);
		
		//////////////// dummy entities //////
		SqlRepository<DummyEntity1> dummy1Repo = new SqlRepository<DummyEntity1>(DummyEntity1.class, connectionHelper);
		context.addRepository(SystemARepositories.DUMMY1, dummy1Repo);
		SqlRepository<DummyEntity2> dummy2Repo = new SqlRepository<DummyEntity2>(DummyEntity2.class, connectionHelper);
		context.addRepository(SystemARepositories.DUMMY2, dummy2Repo);
		SqlRepository<DummyEntity3> dummy3Repo = new SqlRepository<DummyEntity3>(DummyEntity3.class, connectionHelper);
		context.addRepository(SystemARepositories.DUMMY3, dummy3Repo);
		SqlRepository<DummyEntity6> dummy6Repo = new SqlRepository<DummyEntity6>(DummyEntity6.class, connectionHelper);
		context.addRepository(SystemARepositories.DUMMY6, dummy6Repo);
		SqlRepository<DummyComplexDomainEntity> dummyComplexDERepo = new SqlRepository<DummyComplexDomainEntity>(DummyComplexDomainEntity.class, connectionHelper);
		context.addRepository(SystemARepositories.DUMMY_COMPLEX_DE_REPO, dummyComplexDERepo);
		
		///////////////// blob stuff //////
		BinaryEntityRepository blobRepo = BinaryEntityRepositoryFactory.createForTemp(context.getSystemName(), new SqlRepository<BinaryEntity>(BinaryEntity.class, connectionHelper));
		context.addRepository(SystemARepositories.BLOB, blobRepo);
		BinaryEntityRepository blobRepoForMultipartCommand = BinaryEntityRepositoryFactory.createForTemp(context.getSystemName(), new SqlRepository<BinaryEntity>(BinaryEntity.class, connectionHelper));
		context.addRepository(SystemARepositories.BLOB_REPO_FOR_MULTIPART, blobRepoForMultipartCommand);
		SqlRepository<DummyMultipart> dummyMultipartRepo = new SqlRepository<DummyMultipart>(DummyMultipart.class, connectionHelper);
		context.addRepository(SystemARepositories.DUMMY_MULTIPART_REPO, dummyMultipartRepo);
	}

}
