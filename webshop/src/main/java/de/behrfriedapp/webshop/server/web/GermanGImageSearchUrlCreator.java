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

/**
 * @author marcus
 */
public class GermanGImageSearchUrlCreator implements GImageSearchUrlCreator {

	private final static String URL_1 =
			"https://www.google.com/search?safe=off&qscrl=1&site=imghp" +
			"&tbm=isch&source=hp&biw=1600&bih=788&q=";
	private final static String URL_2 =
			"&oq=fein" +
			"&gs_l=img.1.0.0l10.2801.3321.0.4639.4.4.0.0.0.0.83.324.4.4.0....0...1ac.1.25.img..0.4.323.odfTW0ebuAg";


	@Override
	public String createUrl(String searchWord) {
		return URL_1 + searchWord + URL_2;
	}
}
