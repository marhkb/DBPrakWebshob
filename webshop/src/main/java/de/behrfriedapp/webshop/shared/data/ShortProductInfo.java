package de.behrfriedapp.webshop.shared.data;

import java.io.Serializable;

/**
 * @author marcus
 */
public class ShortProductInfo implements Serializable {

	private String name;

	public ShortProductInfo() {
	}

	public ShortProductInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
