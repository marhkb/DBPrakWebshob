/*
 * Copyright 2013 Marcus Behrendt, Gerrit Appeler & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
