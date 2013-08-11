package de.behrfriedapp.webshop.server.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.behrfriedapp.webshop.client.MainService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.behrfriedapp.webshop.server.data.ServerDataAccess;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;

import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@Singleton
public class MainServiceImpl extends RemoteServiceServlet implements MainService {

	private final ServerDataAccess serverDataAccess;

	@Inject
	public MainServiceImpl(final ServerDataAccess serverDataAccess) {
		this.serverDataAccess = serverDataAccess;
	}

	public List<WProductGroupInfo> getAllProductGroups() {
		return this.serverDataAccess.getAllProductGroups();
	}

    public List<ShortProductInfo> getAllGroupProducts(String searchedCategory, String searchedProduct) {
        return this.serverDataAccess.getAllGroupProducts(searchedCategory, searchedProduct);
    }

    public List<ShortProductInfo> getAllGroupProducts(String searchedCategory) {
        return this.serverDataAccess.getAllGroupProducts(searchedCategory);
    }

	public List<WProductGroupInfo> getAllProductGroups(int limit) {
		return this.serverDataAccess.getAllProductGroups(limit);
	}

	public List<ShortProductInfo> getAllProducts() {
		return this.serverDataAccess.getAllProducts();
	}

	public List<ShortProductInfo> getAllProducts(int limit) {
		return this.serverDataAccess.getAllProducts(limit);
	}

	public List<ShortProductInfo> getAllProducts(WProductGroupInfo wCategory) {
		return this.serverDataAccess.getAllProducts(wCategory);
	}

	public List<ShortProductInfo> getAllProducts(WProductGroupInfo wCategory, int limit) {
		return this.serverDataAccess.getAllProducts(wCategory, limit);
	}

	public DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo) {
		return this.serverDataAccess.getDetailedProductInfo(shortProductInfo);
	}
}
