/*
 * Copyright (c) 2014.
 * 	Mikol Faro		<mikol.faro@gmail.com>
 * 	Simone Mangano	 	<simone.mangano@ieee.org>
 * 	Mattia Tortorelli	<mattia.tortorelli@gmail.com>
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
 */

package org.biokoframework.systema.entity.population;

import org.biokoframework.system.entity.template.Template;
import org.biokoframework.system.repository.population.IRepositoryPopulator;
import org.biokoframework.system.repository.service.IRepositoryService;
import org.biokoframework.system.services.entity.IEntityBuilderService;
import org.biokoframework.utils.exception.ValidationException;
import org.biokoframework.utils.fields.Fields;
import org.biokoframework.utils.repository.RepositoryException;

/**
 * @author Mikol Faro <mikol.faro@gmail.com>
 * @date 2014 Apr 03
 */
public class SystemARepositoryPopulator implements IRepositoryPopulator {

    @Override
    public void populate(IRepositoryService repositoryService, IEntityBuilderService entityBuilderService) throws ValidationException, RepositoryException {
        Template template = entityBuilderService.getInstance(Template.class, new Fields(
                Template.TITLE, "Password reset",
                Template.BODY, "Abbiamo ricevuto una richiesta di reset della password. Clicca <a href=\"${url}?token=${token}\">qui</a> per resettare la tua password",
                Template.TRACK, "passwordResetMailTemplate",
                Template.CATEGORY, "email"));

        repositoryService.getRepository(Template.class).save(template);
    }

}
