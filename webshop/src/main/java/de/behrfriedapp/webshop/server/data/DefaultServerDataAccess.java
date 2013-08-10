package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcus
 */
public class DefaultServerDataAccess implements ServerDataAcces {

	private final static String CONNECTION_URL = "jdbc:oracle:thin:@//134.106.56.57:1521/pw2.offis.uni-oldenburg.de";
	private final static String USER = "uebung01";
	private final static String PASSW = "geheim";
	private final Logger logger = LoggerFactory.getLogger(DefaultServerDataAccess.class);
	private Connection conn;

	public DefaultServerDataAccess() {
		try {
			this.conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSW);
			this.conn.setAutoCommit(false);
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<ShortProductInfo> getAllProducts() {
		return null;
	}

	public List<ShortProductInfo> getAllProducts(int limit) {
		final List<ShortProductInfo> result = new ArrayList<ShortProductInfo>();
		try {
			Statement stmt = this.conn.createStatement();
			final ResultSet rset =
					stmt.executeQuery("SELECT * FROM Produkt where rownum<=" + limit);
			while(rset.next()) {
				final ShortProductInfo spi = new ShortProductInfo();
				spi.setName(rset.getString(3));
				System.out.println(spi.getName());
			}
			stmt.close();
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
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
