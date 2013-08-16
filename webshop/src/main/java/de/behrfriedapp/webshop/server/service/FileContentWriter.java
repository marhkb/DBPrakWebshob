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


/**
 * Interface for writing files.
 *
 * @author marcus
 *
 */
public interface FileContentWriter {

	/**
	 * Writes the passed content to the passed file path. If the path does not
	 * exist it will be created.
	 *
	 * @param content
	 *            the content to write to the file
	 * @param filePath
	 *            the path to file to write
	 * @throws FileContentWriteException
	 *             if something unexpected happened
	 */
	void write(String content, String filePath) throws FileContentWriteException;

	/**
	 * Creates a directory at the passed path. If there is already a directory
	 * it will be overwritten.
	 *
	 * @param dirPath
	 *            the path to the directory to create
	 * @throws FileContentWriteException
	 *             if something unexpected happened
	 */
	void createDir(String dirPath) throws FileContentWriteException;

	/**
	 * Creates a directory at the passed path only if it does not exist already.
	 *
	 * @param dirPath
	 *            the path to the directory to create
	 * @return true if directory has been created, otherwise false
	 * @throws FileContentWriteException
	 *             if something unexpected happened
	 */
	boolean createDirIfNotExist(String dirPath) throws FileContentWriteException;
}

