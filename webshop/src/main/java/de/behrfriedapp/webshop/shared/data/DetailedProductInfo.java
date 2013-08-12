
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

package de.behrfriedapp.webshop.shared.data;

import java.io.Serializable;
import java.util.List;

/**
 * @author marcus
 */
public class DetailedProductInfo extends ShortProductInfo implements Serializable {

	private String manufactor;
	private int stock;
	private List<ShortProductInfo> similiarProducts;

	public DetailedProductInfo() {
	}

	public DetailedProductInfo(String name,
							   String price,
							   String manufactor,
							   List<ShortProductInfo> similiarProducts, int stock) {
		super(name, price);
		this.manufactor = manufactor;
		this.similiarProducts = similiarProducts;
		this.stock = stock;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public List<ShortProductInfo> getSimiliarProducts() {
		return similiarProducts;
	}

	public void setSimiliarProducts(List<ShortProductInfo> similiarProducts) {
		this.similiarProducts = similiarProducts;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
