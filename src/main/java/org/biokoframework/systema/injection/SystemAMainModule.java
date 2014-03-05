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

import org.biokoframework.http.exception.ExceptionResponseModule;
import org.biokoframework.http.handler.IHandlerLocator;
import org.biokoframework.http.handler.annotation.AnnotationHandlerLocator;
import org.biokoframework.http.response.ResponseBuilderModule;
import org.biokoframework.http.routing.RouteParserModule;
import org.biokoframework.system.ConfigurationEnum;
import org.biokoframework.system.SystemMainModule;
import org.biokoframework.system.services.cron.CronModule;
import org.biokoframework.system.services.currenttime.CurrentTimeModule;
import org.biokoframework.system.services.email.EmailModule;
import org.biokoframework.system.services.queue.QueueModule;
import org.biokoframework.system.services.random.RandomModule;
import org.biokoframework.systema.factory.SystemACommands;

/**
 * 
 * @author Mikol Faro <mikol.faro@gmail.com>
 * @date Feb 13, 2014
 *
 */
public class SystemAMainModule extends SystemMainModule {

	private final ConfigurationEnum fConfig;
	
	public SystemAMainModule(ConfigurationEnum config) {
		fConfig = config;
		
	}

	@Override
	protected void configureMain() {
		bindProperty("systemName").to("system-a");
		bindProperty("systemVersion").to("1.0");
		
		bindProperty("Commands").to(SystemACommands.class);
	
		bind(ConfigurationEnum.class).toInstance(fConfig);

		bind(IHandlerLocator.class).to(AnnotationHandlerLocator.class);
		
	}

	@Override
	protected void configureProperties() {
		bindProperty("cronEmailAddress").to("cron@engaged.it");
		bindProperty("noReplyEmailAddress").to("no-reply@engaged.it");
	}
	
	@Override
	protected void configureOtherModules() {
		install(new CurrentTimeModule(fConfig));
		install(new RandomModule(fConfig));
		install(new CronModule(fConfig));
		install(new EmailModule(fConfig));
		install(new QueueModule(fConfig));
		install(new SystemAMemRepoModule());
		
		install(new ResponseBuilderModule());
		install(new RouteParserModule());
		install(new ExceptionResponseModule());
		
	}

}