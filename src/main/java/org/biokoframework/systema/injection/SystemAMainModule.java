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

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import org.biokoframework.http.exception.ExceptionResponseModule;
import org.biokoframework.http.handler.IHandlerLocator;
import org.biokoframework.http.handler.annotation.AnnotationHandlerLocator;
import org.biokoframework.http.injection.HttpSystemMainModule;
import org.biokoframework.http.response.ResponseBuilderModule;
import org.biokoframework.http.routing.RouteParserModule;
import org.biokoframework.system.services.authentication.AuthenticationModule;
import org.biokoframework.system.services.cron.CronModule;
import org.biokoframework.system.services.currenttime.CurrentTimeModule;
import org.biokoframework.system.services.email.EmailModule;
import org.biokoframework.system.services.entity.EntityModule;
import org.biokoframework.system.services.queue.QueueModule;
import org.biokoframework.system.services.random.RandomModule;
import org.biokoframework.systema.factory.SystemACommands;
import org.biokoframework.utils.validation.ValidationModule;

import java.util.Map;

/**
 * 
 * @author Mikol Faro <mikol.faro@gmail.com>
 * @date Feb 13, 2014
 *
 */
public class SystemAMainModule extends HttpSystemMainModule {

    @Override
	protected void configureMain() {
		bindProperty("systemName").to("system-a");
		bindProperty("systemVersion").to("1.0");
		
		bindProperty("Commands").to(SystemACommands.class);
		
		BiMap<String, String> headerToFieldMap = HashBiMap.create();
        headerToFieldMap.put("Engaged-Auth-Token", "authToken");
        headerToFieldMap.put("Engaged-Auth-Token-Expire", "authTokenExpire");
        headerToFieldMap = Maps.unmodifiableBiMap(headerToFieldMap);
		bind(new TypeLiteral<Map<String, String>>(){}).annotatedWith(Names.named("httpHeaderToFieldsMap")).toInstance(headerToFieldMap);
        bind(new TypeLiteral<Map<String, String>>(){}).annotatedWith(Names.named("fieldsHttpHeaderToMap")).toInstance(headerToFieldMap.inverse());

		bind(IHandlerLocator.class).to(AnnotationHandlerLocator.class);
		
	}

	@Override
	protected void configureProperties() {
		bindProperty("cronEmailAddress").to("cron@engaged.it");
		bindProperty("noReplyEmailAddress").to("no-reply@engaged.it");
		bindProperty("tokenValiditySecs").to(900L);
	}

    @Override
    protected void configureOtherModules() {
        install(new EntityModule());
        install(new SystemAMemRepoModule(getConfig()));

        install(new CurrentTimeModule(getConfig()));
        install(new RandomModule(getConfig()));
        install(new AuthenticationModule(getConfig()));
        install(new CronModule(getConfig()));
        install(new EmailModule(getConfig()));
        install(new QueueModule(getConfig()));
        install(new ValidationModule());

        install(new ResponseBuilderModule());
        install(new RouteParserModule());
        install(new ExceptionResponseModule());

    }
}