package it.bioko.systema.injection;

import it.bioko.http.BiokoServlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class SystemAServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		Injector injector = Guice.createInjector(
				new SystemAModule(),
				new ServletModule() {
					@Override
					protected void configureServlets() {
						serve("/1.0/*").with(BiokoServlet.class);
					}
				});
		
		return injector;
	}

}
