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

package de.behrfriedapp.webshop.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;

import java.util.List;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("main")
public interface MainService extends RemoteService {

	List<WProductGroupInfo> getAllProductGroups();

	List<WProductGroupInfo> getAllProductGroups(int limit);

	List<String> getAllProducts();

    List<ShortProductInfo> getAllProducts(String searchedProduct);

	List<ShortProductInfo> getAllProducts(int limit);

	List<ShortProductInfo> getAllProducts(WProductGroupInfo wCategory);

	List<ShortProductInfo> getAllProducts(WProductGroupInfo wCategory, int limit);

	DetailedProductInfo getDetailedProductInfo(int pId);

    List<ShortProductInfo> getAllGroupProducts(String searchedCategory, String searchedProduct);

    List<ShortProductInfo> getAllGroupProducts(String searchedCategory);
}
