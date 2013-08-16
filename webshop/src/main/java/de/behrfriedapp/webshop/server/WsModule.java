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

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.behrfriedapp.webshop.server.data.DefaultServerDataAccess;
import de.behrfriedapp.webshop.server.data.ServerDataAccess;
import de.behrfriedapp.webshop.server.web.*;
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
		this.logger.info("Configuring dependencies");
		this.bind(ServerDataAccess.class).to(DefaultServerDataAccess.class).in(Singleton.class);
		this.bind(ImageSearchUrlCreator.class).to(BingImageSearchUrlCreator.class).in(Singleton.class);
		this.bind(HttpAccess.class).to(DefaultHttpAccess.class).in(Singleton.class);
		this.bind(ImageSearchUrlExtractor.class).to(BingImageSearchUrlExtractor.class).in(Singleton.class);
		this.bind(ImageDownloader.class).to(MemoryImageDownloader.class).in(Singleton.class);
		this.logger.info("Configuring dependencies => Done");
	}

}
