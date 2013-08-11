package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;

import java.util.List;

/**
 * @author marcus
 */
public interface ServerDataAccess {

    List<WProductGroupInfo> getAllProductGroups();

	List<WProductGroupInfo> getAllProductGroups(int limit);

    List<ShortProductInfo> getAllProducts(String searchedProduct);

    List<ShortProductInfo> getAllGroupProducts(String searchedCategory, String searchedProduct);

	List<ShortProductInfo> getAllProducts();

	List<ShortProductInfo> getAllProducts(int limit);

	List<ShortProductInfo> getAllProducts(WProductGroupInfo wGroup);

	List<ShortProductInfo> getAllProducts(WProductGroupInfo wGroup, int limit);

	DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo);
}
