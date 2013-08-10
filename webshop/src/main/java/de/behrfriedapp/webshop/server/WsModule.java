package de.behrfriedapp.webshop.server;

import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server side module for configuring regular services.
 *
 * @author marcus
 *
 */
public class WsModule extends AbstractModule {

	/**
	 * logger linked to any logging framework
	 */
	private final Logger logger = LoggerFactory.getLogger(WsModule.class);

	/**
	 * Binds all regular interfaces to a certain implementation.
	 */
	@Override
	protected void configure() {
		this.logger.info("configuring dependencies");
//		this.bind(WikiApi.class).to(HttpWikiApi.class).in(Singleton.class);
//		this.bind(WikiAccess.class).to(JsonWikiAccess.class).in(Singleton.class);
		this.logger.info("dependencies configured");
	}

}
