package it.bioko.systema.injection;

import it.bioko.system.service.context.ContextFactory;
import it.bioko.systema.factory.SystemACommands;
import it.bioko.systema.factory.SystemAContextFactory;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class SystemAModule extends AbstractModule {

	@Override
	public void configure() {
		bind(new TypeLiteral<Class<?>>(){})
			.annotatedWith(Names.named("Commands"))
			.toInstance(SystemACommands.class);
		
		bind(ContextFactory.class)
			.to(SystemAContextFactory.class);
		
	}
	
}
