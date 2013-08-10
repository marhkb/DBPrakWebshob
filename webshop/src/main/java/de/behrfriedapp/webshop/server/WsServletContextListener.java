package de.behrfriedapp.webshop.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class whose {@code getInjector()} is invoked immediately after application
 * has deployed.
 *
 * @author marcus
 *
 */
public class WsServletContextListener extends GuiceServletContextListener {

	/**
	 * logger linked to any logging framework
	 */
	private final Logger logger = LoggerFactory.getLogger(WsServletContextListener.class);

	/**
	 * This method creates all needed {@link com.google.inject.Injector}s for server side
	 * application.
	 */
	@Override
	protected Injector getInjector() {
		this.logger.info("Creating Injectors");
		return Guice.createInjector(new WsModule(), new WsServletModule());
	}
}
