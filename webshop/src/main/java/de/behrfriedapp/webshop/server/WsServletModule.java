package de.behrfriedapp.webshop.server;

import com.google.inject.servlet.ServletModule;
import de.behrfriedapp.webshop.server.servlet.MainServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server side module for for configuring servlets.
 *
 * @author marcus
 *
 */
public class WsServletModule extends ServletModule {

	/**
	 * logger linked to any logging framework
	 */
	private final Logger logger = LoggerFactory.getLogger(WsServletModule.class);

	/**
	 * Binds all Servlets.
	 */
	@Override
	protected void configureServlets() {
		this.logger.info("Configuring servlets");

		this.logger.debug("configuring MainService");
		this.serve("/Webshop/main").with(MainServiceImpl.class);

		this.logger.info("All servlets have been configured");
	}
}
