package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WCategoryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcus
 */
public class DefaultServerDataAccess implements ServerDataAccess {

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

	public List<WCategoryInfo> getAllCategories() {
		List<WCategoryInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM W_KATEGORIE");
			result = this.getCategories(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<WCategoryInfo> getAllCategories(int limit) {
		List<WCategoryInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM W_KATEGORIE WHERE ROWNUM<=?");
			stmt.setInt(1, limit);
			result = this.getCategories(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	private List<WCategoryInfo> getCategories(final PreparedStatement preparedStatement) throws
			SQLException {

		final List<WCategoryInfo> result = new ArrayList<WCategoryInfo>();
		final ResultSet rset = preparedStatement.executeQuery();
		while(rset.next()) {
			result.add(new WCategoryInfo(rset.getInt(1), rset.getString(2)));
		}
		preparedStatement.close();
		return result;
	}

	public List<ShortProductInfo> getAllProducts() {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM PRODUKT");
			result = this.getProducts(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllProducts(int limit) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM PRODUKT WHERE ROWNUM<=?");
			stmt.setInt(1, limit);
			result = this.getProducts(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllProducts(WCategoryInfo wCategory) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM PRODUKT WHERE W_KATEGORIE=?");
			stmt.setInt(1, wCategory.getId());
			result = this.getProducts(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllProducts(WCategoryInfo wCategory, int limit) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM PRODUKT WHERE W_KATEGORIE=? AND " +
																"ROWNUM<=?");
			stmt.setInt(1, wCategory.getId());
			stmt.setInt(2, limit);
			result = this.getProducts(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	private List<ShortProductInfo> getProducts(final PreparedStatement preparedStatement) throws
			SQLException {

		final List<ShortProductInfo> result = new ArrayList<ShortProductInfo>();
		final ResultSet rset = preparedStatement.executeQuery();
		while(rset.next()) {
			result.add(new ShortProductInfo(rset.getString(3)));
		}
		preparedStatement.close();
		return result;
	}

	public DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo) {
		return null;
	}
}
