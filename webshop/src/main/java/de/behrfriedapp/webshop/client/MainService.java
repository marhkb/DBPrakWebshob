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

	List<ShortProductInfo> getAllProducts();

	List<ShortProductInfo> getAllProducts(int limit);

	List<ShortProductInfo> getAllProducts(WProductGroupInfo wCategory);

	List<ShortProductInfo> getAllProducts(WProductGroupInfo wCategory, int limit);

	DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo);

    List<ShortProductInfo> getAllGroupProducts(String searchedCategory, String searchedProduct);
}
