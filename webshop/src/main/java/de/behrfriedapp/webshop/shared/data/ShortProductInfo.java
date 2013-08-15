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

/**
 * @author marcus
 */
public class ShortProductInfo implements Serializable {

	private int id;
	private String name;
	private double price;
	private String imageData;

	public ShortProductInfo() {
	}

    public ShortProductInfo(String name, double price, int id, String imageData) {
        this.name = name;
        this.price = price;
        this.id = id;
		this.imageData = imageData;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
}
