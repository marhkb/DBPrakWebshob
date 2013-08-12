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

package de.behrfriedapp.webshop.client.data;

import de.behrfriedapp.webshop.shared.data.ShortProductInfo;

import java.util.List;

/**
 * @author marcus
 */
public class ServerDataReceiver implements DataReceiver {

	public List<String> getWCategories() {
		return null;
	}

	public List<ShortProductInfo> getProducts() {
		return null;
	}

	public List<ShortProductInfo> getProducts(String wCategory) {
		return null;
	}
}
