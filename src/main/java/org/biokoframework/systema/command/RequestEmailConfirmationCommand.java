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
import org.biokoframework.system.KILL_ME.commons.GenericFieldNames;
import org.biokoframework.system.command.AbstractCommand;
import org.biokoframework.system.command.CommandException;
import org.biokoframework.system.entity.login.Login;
import org.biokoframework.system.entity.template.Template;
import org.biokoframework.system.exceptions.CommandExceptionsFactory;
import org.biokoframework.system.services.email.EmailException;
import org.biokoframework.system.services.email.IEmailConfirmationService;
import org.biokoframework.system.services.templates.TemplatingException;
import org.biokoframework.utils.domain.DomainEntity;
import org.biokoframework.utils.fields.Fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestEmailConfirmationCommand extends AbstractCommand {

	public static final String EMAIL_CONFIRMATION_TOKEN = "emailConfirmationToken";

	private static final String EMAIL_CONFIRMATION_SUBJECT = "Email confirmation";
	
    private final IEmailConfirmationService fConfirmationService;

    @Inject
	public RequestEmailConfirmationCommand(IEmailConfirmationService confirmationService) {
        fConfirmationService = confirmationService;
    }

	@Override
	public Fields execute(Fields input) throws CommandException {
		logInput(input);

        String userEmail = input.get(Login.USER_EMAIL);
        Login login = getRepository(Login.class).retrieveByForeignKey(Login.USER_EMAIL, userEmail);
        if (login == null) {
            throw CommandExceptionsFactory.createEntityNotFound(Login.class, Login.USER_EMAIL, userEmail);
        }

        Template template = createEntity(Template.class, new Fields(
                Template.TITLE, EMAIL_CONFIRMATION_SUBJECT,
                Template.BODY, "<html>\n" +
                                    "<body>\n" +
                                        "Clicca sul link riportato sotto per confermare la tua mail\n" +
                                        "<a href=\"${url}?token=${token}&userEmail=${userEmail}\">Conferma email</a>\n" +
                                    "<body>\n" +
                                "</html>"));

        Map<String, Object> content = new HashMap<>();
        content.put("url", "http://www.example.net/confirm-email");
        content.put("userEmail", login.get(Login.USER_EMAIL));


        try {
            fConfirmationService.sendConfirmationEmail(login, template, content);
		} catch (EmailException|TemplatingException exception) {
			throw CommandExceptionsFactory.createContainerException(exception);
		}
		
		logOutput();
		return new Fields(GenericFieldNames.RESPONSE, new ArrayList<DomainEntity>());
	}

}
