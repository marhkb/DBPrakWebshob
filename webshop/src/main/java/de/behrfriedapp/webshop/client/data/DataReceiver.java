package de.behrfriedapp.webshop.client.data;

import de.behrfriedapp.webshop.shared.data.ShortProductInfo;

import java.util.List;

/**
 * @author marcus
 */
public interface DataReceiver {

	List<String> getWCategories();

	List<ShortProductInfo> getProducts();

	List<ShortProductInfo> getProducts(String wCategory);
}
