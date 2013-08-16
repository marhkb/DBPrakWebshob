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

package de.behrfriedapp.webshop.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author marcus
 */
public class FirstEntryGImageSearchUrlExtractor implements GImageSearchUrlExtractor {

	private final static String PATTERN_TO_COMPILE = "imgurl:&quot;http://[\\S]+?\\.(jpg|jpeg|png|bmp|gif)";
	/**
	 * {@link org.slf4j.Logger} for logging messages
	 */
	private final Logger logger = LoggerFactory.getLogger(FirstEntryGImageSearchUrlExtractor.class);
	/**
	 * the regex to validate compiled lazily
	 */
	private Pattern pattern;

	@Override
	public String extractUrl(String src) {
		if(src == null) {
			throw new IllegalArgumentException("src == null");
		}
		if(this.pattern == null) {
			this.logger.debug("Compiling pattern: " + PATTERN_TO_COMPILE);
			this.pattern = Pattern.compile(PATTERN_TO_COMPILE);
		}
		final Matcher matcher = pattern.matcher(src);
		if(matcher.find()) {
			return matcher.group().substring(13);
		}
		return null;
	}
}
