package de.behrfriedapp.webshop.shared.data;

import java.io.Serializable;

/**
 * @author marcus
 */
public class ShortProductInfo implements Serializable {

	private String name, price;

	public ShortProductInfo() {
	}

	public ShortProductInfo(String name, String price) {
		this.name = name;
        this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
