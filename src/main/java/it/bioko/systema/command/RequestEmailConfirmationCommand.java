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

package it.bioko.systema.command;

import it.bioko.system.KILL_ME.commons.GenericConstants;
import it.bioko.system.KILL_ME.commons.GenericFieldNames;
import it.bioko.system.command.Command;
import it.bioko.system.command.CommandException;
import it.bioko.system.entity.authentication.EmailConfirmation;
import it.bioko.system.entity.login.Login;
import it.bioko.system.exceptions.CommandExceptionsFactory;
import it.bioko.system.repository.core.Repository;
import it.bioko.system.repository.core.SafeRepositoryHelper;
import it.bioko.system.service.mail.EmailFiller;
import it.bioko.system.service.mail.EmailServiceImplementation;
import it.bioko.system.service.random.RandomGeneratorService;
import it.bioko.systema.commons.SystemARepositories;
import it.bioko.utils.domain.DomainEntity;
import it.bioko.utils.fields.FieldValues;
import it.bioko.utils.fields.Fields;

import java.util.ArrayList;

import javax.mail.internet.MimeMessage;

public class RequestEmailConfirmationCommand extends Command {

	public static final String EMAIL_CONFIRMATION_TOKEN = "emailConfirmationToken";
	
	private Repository<Login> _loginRepo;
	private Repository<EmailConfirmation> _confirmationRepo;
	private RandomGeneratorService _randomTokenService;
	
	@Override
	public void onContextInitialized() {
		super.onContextInitialized();
		
		_loginRepo = _context.getRepository(SystemARepositories.LOGIN);
		_confirmationRepo = _context.getRepository(SystemARepositories.EMAIL_CONFIRMATION);
		_randomTokenService = (RandomGeneratorService) _context.get(GenericConstants.CONTEXT_RANDOM_GENERATOR_SERVICE);
		
	}

	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);

		EmailConfirmation confirmation = new EmailConfirmation(Fields.empty());
		
		String userEmail = input.stringNamed(Login.USER_EMAIL);
		Login login = _loginRepo.retrieveByForeignKey(Login.USER_EMAIL, userEmail);
		if (login == null) {
			throw CommandExceptionsFactory.createEntityNotFound(Login.class.getSimpleName(), Login.USER_EMAIL, userEmail);
		}
		
		String token = _randomTokenService.generateString(EMAIL_CONFIRMATION_TOKEN, 10);
		
		confirmation.set(EmailConfirmation.LOGIN_ID, login.getId());
		confirmation.set(EmailConfirmation.TOKEN, token);
		confirmation.set(EmailConfirmation.CONFIRMED, FieldValues.FALSE);
		SafeRepositoryHelper.save(_confirmationRepo, confirmation, _context);
		
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
		return Fields.single(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

}
