package de.behrfriedapp.webshop.server.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.behrfriedapp.webshop.client.MainService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.behrfriedapp.webshop.server.data.ServerDataAcces;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;

import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Singleton
public class MainServiceImpl extends RemoteServiceServlet implements MainService {

	private final ServerDataAcces serverDataAcces;

	@Inject
	public MainServiceImpl(final ServerDataAcces serverDataAcces) {
		this.serverDataAcces = serverDataAcces;
	}

	public List<ShortProductInfo> getAllProducts() {
		return this.serverDataAcces.getAllProducts();
	}

	public List<ShortProductInfo> getAllProducts(int limit) {
		return this.serverDataAcces.getAllProducts(limit);
	}

	public List<ShortProductInfo> getAllProducts(String wCategory) {
		return this.serverDataAcces.getAllProducts(wCategory);
	}

	public List<ShortProductInfo> getAllProducts(String wCategory, int limit) {
		return this.serverDataAcces.getAllProducts(wCategory, limit);
	}

	public DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo) {
		return this.serverDataAcces.getDetailedProductInfo(shortProductInfo);
	}
}
