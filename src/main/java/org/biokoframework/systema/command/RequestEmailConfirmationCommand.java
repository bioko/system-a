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

import java.util.ArrayList;

import javax.mail.internet.MimeMessage;

import org.biokoframework.system.KILL_ME.commons.GenericFieldNames;
import org.biokoframework.system.command.AbstractCommand;
import org.biokoframework.system.command.CommandException;
import org.biokoframework.system.entity.authentication.EmailConfirmation;
import org.biokoframework.system.entity.login.Login;
import org.biokoframework.system.exceptions.CommandExceptionsFactory;
import org.biokoframework.system.repository.core.SafeRepositoryHelper;
import org.biokoframework.system.service.mail.EmailFiller;
import org.biokoframework.system.service.mail.EmailServiceImplementation;
import org.biokoframework.system.services.random.IRandomService;
import org.biokoframework.utils.domain.DomainEntity;
import org.biokoframework.utils.fields.Fields;
import org.biokoframework.utils.repository.Repository;

import com.google.inject.Inject;

public class RequestEmailConfirmationCommand extends AbstractCommand {

	public static final String EMAIL_CONFIRMATION_TOKEN = "emailConfirmationToken";
	
	private final IRandomService fRandomTokenService;
	
	@Inject
	public RequestEmailConfirmationCommand(IRandomService randomService) {
		fRandomTokenService = randomService;
	}

	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);

		Repository<Login> loginRepo = getRepository(Login.class);
		Repository<EmailConfirmation> confirmationRepo = getRepository(EmailConfirmation.class);

		EmailConfirmation confirmation = new EmailConfirmation(new Fields());
		
		String userEmail = input.get(Login.USER_EMAIL);
		Login login = loginRepo.retrieveByForeignKey(Login.USER_EMAIL, userEmail);
		if (login == null) {
			throw CommandExceptionsFactory.createEntityNotFound(Login.class.getSimpleName(), Login.USER_EMAIL, userEmail);
		}
		
		String token = fRandomTokenService.generateString(EMAIL_CONFIRMATION_TOKEN, 10);
		
		confirmation.set(EmailConfirmation.LOGIN_ID, login.getId());
		confirmation.set(EmailConfirmation.TOKEN, token);
		confirmation.set(EmailConfirmation.CONFIRMED, false);
		SafeRepositoryHelper.save(confirmationRepo, confirmation, fContext);
		
		EmailServiceImplementation emailService = EmailServiceImplementation.mailServer();
		EmailFiller filler = new EmailFiller();
		filler.setFrom("noreply@engaged.it");
		filler.addTo(userEmail);
		filler.setSubject("Email confirmation");
		filler.setContent("<html>\n<body>\nClicca sul link riportato sotto per confermare la tua mail\n<a href=\"http://www.example.net/confirm-email?token=" + token + "&userEmail=" + userEmail + ">Conferma email</a>\n<body>\n</html>");
		
		MimeMessage message = emailService.newMessage();
		filler.fill(message);
		emailService.send(message);
		
		logOutput();
		return new Fields(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

}
