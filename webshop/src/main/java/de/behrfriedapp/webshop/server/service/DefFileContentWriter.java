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

package de.behrfriedapp.webshop.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Default implementation of {@link FileContentWriter}.
 *
 * @author marcus
 *
 */
public class DefFileContentWriter implements FileContentWriter {

	/**
	 * {@link org.slf4j.Logger} for logging messages
	 */
	private final Logger logger = LoggerFactory.getLogger(DefFileContentWriter.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final String content, final String filePath) throws FileContentWriteException {

		this.logger.debug("Write content\n" + content + "\nto '" + filePath + "'");

		FileOutputStream fileOutputStream = null;
		DataOutputStream dataOutputStream = null;
		try {

			final File file = new File(filePath).getParentFile();
			if(!file.exists()) {
				this.logger.warn("Path '" + file.getAbsolutePath() + "' doesn't exist -> try to create it");
				this.createDir(file.getAbsolutePath());
			}

			fileOutputStream = new FileOutputStream(filePath);
			dataOutputStream = new DataOutputStream(fileOutputStream);
			dataOutputStream.write(content.getBytes());
		} catch(final IOException e) {
			this.logger.error(e.getMessage(), e);
		} finally {
			if(dataOutputStream != null) {
				try {
					dataOutputStream.close();
					this.logger.debug("Content written to '" + filePath + "'");
				} catch(final IOException e) {
					this.logger.error(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createDir(final String dirPath) throws FileContentWriteException {
		final File file = new File(dirPath);
		this.logger.info("creating path '" + file.getAbsolutePath() + "'");
		if(!file.mkdirs()) {
			throw new FileContentWriteException("Could not create directory '" + dirPath + "'");
		}
		this.logger.info("Path '" + file.getAbsolutePath() + "' created successfully");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createDirIfNotExist(final String dirPath) throws FileContentWriteException {
		if(!new File(dirPath).exists()) {
			this.createDir(dirPath);
			return true;
		}
		return false;
	}
}
