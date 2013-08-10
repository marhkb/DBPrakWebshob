package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;

import java.util.List;

/**
 * @author marcus
 */
public class DefaultServerDataAccess implements ServerDataAcces {
	public List<ShortProductInfo> getAllProducts() {
		return null;
	}

	public List<ShortProductInfo> getAllProducts(int limit) {
		return null;
	}

	public List<ShortProductInfo> getAllProducts(String wCategory) {
		return null;
	}

	public List<ShortProductInfo> getAllProducts(String wCategory, int limit) {
		return null;
	}

	public DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo) {
		return null;
	}
}
