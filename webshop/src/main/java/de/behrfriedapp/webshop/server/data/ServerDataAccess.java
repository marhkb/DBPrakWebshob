package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WCategoryInfo;

import java.util.List;

/**
 * @author marcus
 */
public interface ServerDataAccess {

	List<WCategoryInfo> getAllCategories();

	List<WCategoryInfo> getAllCategories(int limit);

	List<ShortProductInfo> getAllProducts();

	List<ShortProductInfo> getAllProducts(int limit);

	List<ShortProductInfo> getAllProducts(WCategoryInfo wCategory);

	List<ShortProductInfo> getAllProducts(WCategoryInfo wCategory, int limit);

	DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo);
}
