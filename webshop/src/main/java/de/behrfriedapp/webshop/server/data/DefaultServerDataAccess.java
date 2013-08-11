package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;
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

    public List<WProductGroupInfo> getAllProductGroups() {
        List<WProductGroupInfo> result = null;
        try {
            PreparedStatement stmt = this.conn.prepareStatement(
                    "SELECT W_GRUPPE.ID, W_GRUPPE.BEZEICHNUNG, W_KATEGORIE.ID, W_KATEGORIE.BEZEICHNUNG FROM W_GRUPPE, W_KATEGORIE " +
                            "WHERE W_GRUPPE.ID=W_KATEGORIE.FK_GRUPPE_ID");
            result = this.getProductGroups(stmt);
        } catch(Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return result;
    }

	public List<WProductGroupInfo> getAllProductGroups(int limit) {
		List<WProductGroupInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
                    "SELECT W_GRUPPE.ID, W_GRUPPE.BEZEICHNUNG, W_KATEGORIE.ID, W_KATEGORIE.BEZEICHNUNG FROM W_KATEGORIE " +
                            "WHERE ROWNUM<=?");
			stmt.setInt(1, limit);
			result = this.getProductGroups(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	private List<WProductGroupInfo> getProductGroups(final PreparedStatement preparedStatement) throws
			SQLException {

		final List<WProductGroupInfo> result = new ArrayList<WProductGroupInfo>();
		final ResultSet rset = preparedStatement.executeQuery();
		while(rset.next()) {
			result.add(new WProductGroupInfo(rset.getInt(1), rset.getInt(3), rset.getString(2), rset.getString(4)));
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

	public List<ShortProductInfo> getAllProducts(WProductGroupInfo wGroup) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM PRODUKT WHERE W_GRUPPE=?");
			stmt.setInt(1, wGroup.getGroupId());
			result = this.getProducts(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

    public List<ShortProductInfo> getAllProducts(String searchedProduct) {
        List<ShortProductInfo> result = null;
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM PRODUKT " +
                    "WHERE REGEXP_LIKE (BEZEICHNUNG, '[A-Za-z0-9]*?[A-Za-z0-9]*'");
            stmt.setString(1, searchedProduct);
            result = this.getProducts(stmt);
        } catch(Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return result;
    }

    public List<ShortProductInfo> getAllGroupProducts(String searchedCategory, String searchedProduct) {
        List<ShortProductInfo> result = null;
        try {
            PreparedStatement stmt = this.conn.prepareStatement(
                    "SELECT * FROM PRODUKT, W_KATEGORIE, W_GRUPPE " +
                    "WHERE W_KATEGORIE.ID=PRODUKT.W_KATEGORIE " +
                        "AND W_KATEGORIE.FK_GRUPPE_ID=W_GRUPPE.ID " +
                            "AND W_GRUPPE.BEZEICHNUNG=? " +
                                "AND REGEXP_LIKE (PRODUKT.BEZEICHNUNG, '[A-Za-z0-9]*?[A-Za-z0-9]*'");
            stmt.setString(1, searchedCategory);
            stmt.setString(2, searchedProduct);
            result = this.getProducts(stmt);
        } catch(Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return result;
    }

	public List<ShortProductInfo> getAllProducts(WProductGroupInfo wGroup, int limit) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM PRODUKT WHERE W_GRUPPE=? AND ROWNUM<=?");
			stmt.setInt(1, wGroup.getGroupId());
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
