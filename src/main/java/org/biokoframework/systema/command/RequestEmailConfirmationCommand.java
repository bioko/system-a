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

package org.biokoframework.systema.command;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.biokoframework.system.KILL_ME.commons.GenericFieldNames;
import org.biokoframework.system.command.AbstractCommand;
import org.biokoframework.system.command.CommandException;
import org.biokoframework.system.entity.authentication.EmailConfirmation;
import org.biokoframework.system.entity.login.Login;
import org.biokoframework.system.exceptions.CommandExceptionsFactory;
import org.biokoframework.system.repository.core.SafeRepositoryHelper;
import org.biokoframework.system.services.email.EmailException;
import org.biokoframework.system.services.email.IEmailService;
import org.biokoframework.system.services.random.IRandomService;
import org.biokoframework.utils.domain.DomainEntity;
import org.biokoframework.utils.fields.Fields;
import org.biokoframework.utils.repository.Repository;

import java.util.ArrayList;

public class RequestEmailConfirmationCommand extends AbstractCommand {

	public static final String EMAIL_CONFIRMATION_TOKEN = "emailConfirmationToken";

	private static final String EMAIL_CONFIRMATION_SUBJECT = "Email confirmation";
	
	private final IRandomService fRandomTokenService;
	private final IEmailService fEmailService;
	private final String fNoReplyEmailAddress;
	
	@Inject
	public RequestEmailConfirmationCommand(IRandomService randomService, IEmailService emailService, @Named("noReplyEmailAddress") String noreplyEmailAddress) {
		fRandomTokenService = randomService;
		fEmailService = emailService;
		fNoReplyEmailAddress = noreplyEmailAddress;
	}

	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);

		Repository<Login> loginRepo = getRepository(Login.class);
		Repository<EmailConfirmation> confirmationRepo = getRepository(EmailConfirmation.class);


        String userEmail = input.get(Login.USER_EMAIL);
        Login login = loginRepo.retrieveByForeignKey(Login.USER_EMAIL, userEmail);
        if (login == null) {
            throw CommandExceptionsFactory.createEntityNotFound(Login.class.getSimpleName(), Login.USER_EMAIL, userEmail);
        }

        String token = fRandomTokenService.generateUUID().toString();

        EmailConfirmation confirmation = createEntity(EmailConfirmation.class, new Fields(
            EmailConfirmation.LOGIN_ID, login.getId(),
		    EmailConfirmation.TOKEN, token,
		    EmailConfirmation.CONFIRMED, false));
		SafeRepositoryHelper.save(confirmationRepo, confirmation);
		
		// TODO extract template
		String mailContent = new StringBuilder().
				append("<html>\n<body>\n").
				append("Clicca sul link riportato sotto per confermare la tua mail\n").
				append("<a href=\"http://www.example.net/confirm-email?token=").
					append(token).append("&userEmail=").append(userEmail).append(">").
				append("Conferma email</a>\n<body>\n</html>").toString();
		
		try {
			fEmailService.sendASAP(userEmail, fNoReplyEmailAddress, mailContent, EMAIL_CONFIRMATION_SUBJECT);
		} catch (EmailException exception) {
			throw CommandExceptionsFactory.createContainerException(exception);
		}
		
		logOutput();
		return new Fields(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

}
