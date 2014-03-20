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

package org.biokoframework.systema.factory;

import org.biokoframework.system.ConfigurationEnum;
import org.biokoframework.system.KILL_ME.commons.GenericCommandNames;
import org.biokoframework.system.KILL_ME.commons.GenericFieldValues;
import org.biokoframework.system.KILL_ME.commons.HttpMethod;
import org.biokoframework.system.command.KILL_ME.GetCommandInvocationInfoCommand;
import org.biokoframework.system.command.KILL_ME.GetCommandsListCommand;
import org.biokoframework.system.command.annotation.Command;
import org.biokoframework.system.command.annotation.CommandInputs;
import org.biokoframework.system.command.authentication.ApplyPasswordResetCommand;
import org.biokoframework.system.command.authentication.EngagedCheckInCommand;
import org.biokoframework.system.command.authentication.RequestPasswordResetCommand;
import org.biokoframework.system.command.authentication.ResponseEmailConfirmationCommand;
import org.biokoframework.system.command.crud.annotation.CrudCommand;
import org.biokoframework.system.command.crud.binary.annotation.BlobCrudCommand;
import org.biokoframework.system.command.description.SystemDescriptionCommand;
import org.biokoframework.system.entity.authentication.Authentication;
import org.biokoframework.system.entity.authentication.EmailConfirmation;
import org.biokoframework.system.entity.authentication.PasswordReset;
import org.biokoframework.system.entity.login.Login;
import org.biokoframework.system.service.validation.Validators;
import org.biokoframework.system.services.authentication.annotation.Auth;
import org.biokoframework.system.services.cron.annotation.Cron;
import org.biokoframework.system.services.cron.annotation.CronExpression;
import org.biokoframework.systema.command.*;
import org.biokoframework.systema.commons.SystemACommandNames;
import org.biokoframework.systema.commons.SystemARepositories;
import org.biokoframework.systema.entity.dummy1.DummyEntity1;
import org.biokoframework.systema.entity.dummy2.DummyEntity2;
import org.biokoframework.systema.entity.dummy3.DummyEntity3;
import org.biokoframework.systema.entity.dummyComplex.DummyComplexDomainEntity;
import org.biokoframework.systema.validators.UniqueOptionalIntegerValidator;
import org.biokoframework.systema.validators.UniqueTextMandatoryFieldValidator;
import org.biokoframework.utils.domain.annotation.field.Field;

public class SystemACommands {

	////////////////////////////////////////////////////////////////////////////////////////
	//                               LOGIN                                                //
	////////////////////////////////////////////////////////////////////////////////////////

	@CrudCommand(entity = Login.class, repoName = SystemARepositories.LOGIN)
	public static final String LOGIN = "login";

	@CrudCommand(entity = Authentication.class, repoName = SystemARepositories.AUTHENTICATION_REPOSITORY)
	public static final String AUTHENTICATION = "authentication";

    @Auth
	@Command(impl=EngagedCheckInCommand.class, rest = HttpMethod.POST )
	public static final String ENGAGED_CHECK_IN = GenericCommandNames.ENGAGED_CHECK_IN;
	
	@Command(impl = RequestPasswordResetCommand.class, rest = HttpMethod.GET)
	@CommandInputs({ 
		@Field(name = Login.USER_EMAIL) 
	})
	public static final String REQUEST_PASSWORD_RESET = "password-reset";
	
	@Command(impl = ApplyPasswordResetCommand.class, rest = HttpMethod.POST)
	@CommandInputs({ 
		@Field(name = Login.PASSWORD),
		@Field(name = PasswordReset.TOKEN)
	})
	public static final String APPLY_PASSWORD_RESET = "password-reset";
	

	@Command(impl = RequestEmailConfirmationCommand.class, rest = HttpMethod.GET)
	@CommandInputs({
		@Field(name = Login.USER_EMAIL)
	})
	public static final String CONFIRMATION_EMAIL_REQUEST = "confirmation-email";
	
	@Command(impl = ResponseEmailConfirmationCommand.class, rest = HttpMethod.POST)
	@CommandInputs({
		@Field(name = Login.USER_EMAIL),
		@Field(name = EmailConfirmation.TOKEN)
	})
	public static final String CONFIRMATION_EMAIL_RESPONSE = "confirmation-email";
	
	@CrudCommand(entity = EmailConfirmation.class, repoName = SystemARepositories.EMAIL_CONFIRMATION, hideOn = ConfigurationEnum.PROD)
	public static final String EMAIL_CONFIRMATION_TEST = "email-confirmation";

	////////////////////////////////////////////////////////////////////////////////////////
	//                               CRUD                                          		  //
	////////////////////////////////////////////////////////////////////////////////////////

	@CrudCommand(entity=DummyEntity1.class, repoName=SystemARepositories.DUMMY1)
	public static final String DUMMY_ENTITY1 = "dummy-entity1";

	@CrudCommand(entity=DummyEntity2.class, repoName=SystemARepositories.DUMMY2)
	public static final String DUMMY_ENTITY2 = "dummy-entity2";

	@CrudCommand(entity=DummyEntity3.class, repoName=SystemARepositories.DUMMY3)
	public static final String DUMMY_ENTITY3 = "dummy-entity3";

	////////////////////////////////////////////////////////////////////////////////////////
	//                               BLOB, MULTIPART AND C STUFF                          //
	////////////////////////////////////////////////////////////////////////////////////////

	@BlobCrudCommand(repoName = SystemARepositories.BLOB)
	public static final String MY_BLOB = SystemACommandNames.MY_BLOB;

	@Command(impl=MultipartMultipleCommandWithAnnotations.class, rest = HttpMethod.POST)
	public static final String MULTIPART_MULTIPLE = SystemACommandNames.MULTIPART_MULTIPLE;

	@Command(impl=MultipartCommand.class, rest=HttpMethod.POST)
	public static final String MULTIPART_COMMAND = SystemACommandNames.MULTIPART_COMMAND;


	////////////////////////////////////////////////////////////////////////////////////////
	//                       MOCK COMMANDS                                                //
	////////////////////////////////////////////////////////////////////////////////////////

	@Command(impl=Dummy1MockCommand.class, rest=HttpMethod.GET)
	public static final String DUMMY1_MOCK_COMMAND = SystemACommandNames.DUMMY1_MOCK_COMMAND;


	//	@Command(impl=MultipleExampleWithAnnotations.class, rest=HttpMethod.POST)
	//	public static final String MULTIPLE_EXAMPLE = SystemACommandNames.MULTIPLE_EXAMPLE;


	////////////////////////////////////////////////////////////////////////////////////////
	//                       DISSOLVENCE ET ALL. STUFF                                    //
	////////////////////////////////////////////////////////////////////////////////////////

	@Command(impl=ExampleDissolvedCommandWithAnnotation.class, rest=HttpMethod.POST)
	public static final String DISSOLVED_EXAMPLE = SystemACommandNames.DISSOLVED_EXAMPLE;

	@Command(impl=DummyEntity3ResolvableCommand.class, rest=HttpMethod.GET)
	public static final String DUMMY_ENTITY3_RESOLVED = "dummy-entity3-resolved";


	////////////////////////////////////////////////////////////////////////////////////////
	//                       SYSTEM COMMANDS                                              //
	////////////////////////////////////////////////////////////////////////////////////////

	@Command(impl=GetCommandsListCommand.class, rest=HttpMethod.OPTIONS)
	public static final String COMMAND_LIST = GenericCommandNames.COMMAND_LIST;

	@Command(impl=GetCommandInvocationInfoCommand.class, rest=HttpMethod.OPTIONS)
	public static final String COMMAND_INVOCATION_INFO = GenericCommandNames.COMMAND_INVOCATION_INFO;

	////////////////////////////////////////////////////////////////////////////////////////
	//                       CRON COMMANDS                                                //
	////////////////////////////////////////////////////////////////////////////////////////

	@Cron(notifyTo=GenericFieldValues.CRON_EMAIL,  
			expressions={
				@CronExpression(exp="0/10 * * * * ?", conf=ConfigurationEnum.DEV),
				@CronExpression(exp="0/10 * * * * ?", conf=ConfigurationEnum.PROD),
				@CronExpression(exp="0/10 * * * * ?", conf=ConfigurationEnum.DEMO)
			} 
		)
	@Command(impl = CronExampleCommand.class, rest = HttpMethod.NONE)
	public static final String CRON_EXAMPLE_COMMAND = "cron-example-command";

	@Cron(notifyTo=GenericFieldValues.CRON_EMAIL,  
			expressions={
				@CronExpression(exp="0/10 * * * * ?", conf=ConfigurationEnum.DEV),
				@CronExpression(exp="0/10 * * * * ?", conf=ConfigurationEnum.PROD),
				@CronExpression(exp="0/10 * * * * ?", conf=ConfigurationEnum.DEMO)
			} 
		)
	@Command(impl = CronFailingCommand.class, rest = HttpMethod.NONE)
	public static final String CRON_FAILING_COMMAND = "cron-failing-command";

	////////////////////////////////////////////////////////////////////////////////////////
	//                       VALIDATOR COMMANDS                                           //
	////////////////////////////////////////////////////////////////////////////////////////

	@Command(impl = ValidatedCommand.class, rest = HttpMethod.POST)
	@CommandInputs({
		@Field(name=ValidatedCommand.TEXT_MANDATORY_FIELD),
		@Field(name=ValidatedCommand.TEXT_OPTIONAL_FIELD, mandatory=false),
		@Field(name=ValidatedCommand.INTEGER_OPTIONAL_FIELD, mandatory=false, type=Long.class)
	})
	public static final String VALIDATED_COMMAND = "validated-command";

	@CrudCommand(entity=DummyComplexDomainEntity.class, repoName = SystemARepositories.DUMMY_COMPLEX_DE_REPO)
	@CommandInputs({
		@Field(name=DummyComplexDomainEntity.A_STRING_FIELD_MANDATORY_ALSO_IN_GET)
	})
	public static final String DUMMY_COMPLEX_DOMAIN_ENTITY = "dummy-complex-domain-entity";

	@Command(impl = ValidatedCommand.class, rest = HttpMethod.POST)
	@CommandInputs({
		@Field(name=ValidatedCommand.TEXT_MANDATORY_FIELD),
		@Field(name=ValidatedCommand.TEXT_OPTIONAL_FIELD, mandatory=false),
		@Field(name=ValidatedCommand.INTEGER_OPTIONAL_FIELD, mandatory=false, type=Long.class)
	})
	@Validators({UniqueTextMandatoryFieldValidator.class})
	public static final String VALIDATED_COMMAND_WITH_UNIQUE_VALIDATOR = "validated-command-with-unique-validator";


	@Command(impl = ValidatedCommand.class, rest = HttpMethod.POST)
	@CommandInputs({
		@Field(name=ValidatedCommand.TEXT_MANDATORY_FIELD),
		@Field(name=ValidatedCommand.TEXT_OPTIONAL_FIELD, mandatory=false),
		@Field(name=ValidatedCommand.INTEGER_OPTIONAL_FIELD, mandatory=false, type=Long.class)
	})
	@Validators({UniqueTextMandatoryFieldValidator.class, UniqueOptionalIntegerValidator.class})
	public static final String VALIDATED_COMMAND_WITH_TWO_UNIQUE_VALIDATORS = "validated-command-with-two-unique-validators";


	////////////////////////////////////////////////////////////////////////////////////////
	//                       AUTHENTICATED COMMANDS                                       //
	////////////////////////////////////////////////////////////////////////////////////////

	@Auth
	@CrudCommand(entity = Login.class, repoName = SystemARepositories.LOGIN, read=false, delete=false, head=false, update=false)	
	public static final String LOGIN_AUTHENTICATED = "login-" + GenericCommandNames.AUTHENTICATED;

	//@Auth(mandatory=false)
	@CrudCommand(entity = Login.class, repoName = SystemARepositories.LOGIN, read=false, delete=false, head=false, update=false)	
	public static final String LOGIN_AUTHENTICATED_OPTIONAL = "login-" + GenericCommandNames.AUTHENTICATED + "-optional";

	@Auth
	@Command(impl=PrintLoginIdCommand.class, rest=HttpMethod.POST)
	public static final String CHECK_AUTH_LOGIN_ID_WITH_AUTH_ANNOTATION = "check-auth-login-id-with-auth-annotation";

	@Command(impl=PrintLoginIdCommand.class, rest=HttpMethod.POST)
	public static final String CHECK_AUTH_LOGIN_ID_WITHOUT_AUTH_ANNOTATION = "check-auth-login-id-without-auth-annotation";


	////////////////////////////////////////////////////////////////////////////////////////
	//                       ROLES                                           			  //
	////////////////////////////////////////////////////////////////////////////////////////

	@Command(impl=DummyEmptyCommand.class, rest=HttpMethod.POST)
	public static final String DUMMY_COMMAND_NOT_AUTHENTICATED = "dummy-command-not-authenticated";	

	@Auth
	@Command(impl=DummyEmptyCommand.class, rest=HttpMethod.POST)
	public static final String DUMMY_COMMAND_AUTHENTICATED_WITHOUT_ROLES = "dummy-command-authenticated-wihout-roles";

	@Auth(roles={"admin"})
	@Command(impl=DummyEmptyCommand.class, rest=HttpMethod.POST)
	public static final String DUMMY_COMMAND_AUTHENTICATED_ONYL_FOR_ADMIN = "dummy-command-authenticated-only-for-admin";

	@Auth(roles={"another"})
	@Command(impl=DummyEmptyCommand.class, rest=HttpMethod.POST)
	public static final String DUMMY_COMMAND_AUTHENTICATED_ONYL_FOR_ANOTHER = "dummy-command-authenticated-only-for-another";

	@Auth(roles={"admin","another"})
	@Command(impl=DummyEmptyCommand.class, rest=HttpMethod.POST)
	public static final String DUMMY_COMMAND_AUTHENTICATED_FOR_BOTH = "dummy-command-authenticated-for-both";
	
 	///////////////////////////////////////////////////////////////////////////////////////////
 	//                       SYSTEM DESCRIPTION                                              //
 	///////////////////////////////////////////////////////////////////////////////////////////
 	
 	@Command(impl = SystemDescriptionCommand.class, rest = HttpMethod.OPTIONS)
 	public static final String DESCRIBE_SYSTEM = "";
 		
	////////////////////////////////////////////////////////////////////////////////////////
	//                       HIDE-ON TEST                                      			  //
	////////////////////////////////////////////////////////////////////////////////////////
	
	@CrudCommand(entity=DummyEntity1.class, repoName=SystemARepositories.DUMMY1, hideOn={ConfigurationEnum.PROD})
	public static final String DUMMY_ENTITY1_HIDDEN_ON_PROD = "dummy-entity1-hidden-on-prod";
	
	@Command(impl=DummyEmptyCommand.class, rest = HttpMethod.POST, hideOn = { ConfigurationEnum.PROD, ConfigurationEnum.DEMO })
	public static final String DEV_MUTANT_COMMAND = "mutant-command";
	
	@Command(impl=PrintLoginIdCommand.class, rest = HttpMethod.POST, hideOn = { ConfigurationEnum.DEV, ConfigurationEnum.DEMO })
	public static final String PROD_MUTANT_COMMAND = "mutant-command";
	
	@Command (impl = DummyEmptyCommand.class, rest = HttpMethod.POST, hideOn = { ConfigurationEnum.PROD, ConfigurationEnum.DEMO })
	public static final String DEV_AUTH_DUMMY_COMMAND = "auth-mutant-command";
	
	@Auth
	@Command (impl = DummyEmptyCommand.class, rest = HttpMethod.POST, hideOn = { ConfigurationEnum.DEV, ConfigurationEnum.DEMO })
	public static final String PROD_AUTH_DUMMY_COMMAND = "auth-mutant-command";

}

