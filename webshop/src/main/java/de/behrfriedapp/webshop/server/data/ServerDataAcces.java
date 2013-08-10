package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;

import java.util.List;

/**
 * @author marcus
 */
public interface ServerDataAcces {

	List<ShortProductInfo> getAllProducts();

	List<ShortProductInfo> getAllProducts(int limit);

	List<ShortProductInfo> getAllProducts(String wCategory);

	List<ShortProductInfo> getAllProducts(String wCategory, int limit);

	DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo);
}
