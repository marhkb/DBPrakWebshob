/*
 * Copyright 2013 Marcus Behrendt, Gerrit Appeler & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.behrfriedapp.webshop.server.data;

import com.google.inject.Inject;
import de.behrfriedapp.webshop.server.service.FileContentWriter;
import de.behrfriedapp.webshop.server.web.ImageEnrichmentFacade;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcus
 */
public class DefaultServerDataAccess implements ServerDataAccess {

	private final static boolean DEBUG = false;
	private final static String IMG_DIR = "img/";
	private final static String CONNECTION_URL = "jdbc:oracle:thin:@//134.106.56.57:1521/pw2.offis.uni-oldenburg.de";
	private final static String USER = "uebung01";
	private final static String PASSW = "geheim";
	private final Logger logger = LoggerFactory.getLogger(DefaultServerDataAccess.class);
	private final ImageEnrichmentFacade imageEnrichmentFacade;
	private final FileContentWriter fileContentWriter;
	private Connection conn;

	@Inject
	public DefaultServerDataAccess(final ImageEnrichmentFacade imageEnrichmentFacade,
								   final FileContentWriter fileContentWriter) {
		this.imageEnrichmentFacade = imageEnrichmentFacade;
		this.fileContentWriter = fileContentWriter;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSW);
			this.conn.setAutoCommit(false);
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
		} catch(ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<WProductGroupInfo> getAllProductGroups() {
		List<WProductGroupInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT W_GRUPPE.ID, W_GRUPPE.BEZEICHNUNG, W_KATEGORIE.ID, W_KATEGORIE.BEZEICHNUNG " +
					"FROM W_GRUPPE, W_KATEGORIE " +
					"WHERE W_GRUPPE.ID=W_KATEGORIE.FK_GRUPPE_ID"
			);
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
					"SELECT W_GRUPPE.ID, W_GRUPPE.BEZEICHNUNG, W_KATEGORIE.ID, W_KATEGORIE.BEZEICHNUNG " +
					"FROM W_KATEGORIE " +
					"WHERE ROWNUM<=?"
			);
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

	public List<String> getAllProducts() {
		List<String> result = new ArrayList<String>();
		if(DEBUG) {
			return result;
		}
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT * FROM PRODUKT"
			);
			final ResultSet rset = stmt.executeQuery();
			while(rset.next()) {
				result.add(rset.getString(3));
			}
			stmt.close();
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllProducts(int limit) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT * FROM PRODUKT WHERE ROWNUM<=?"
			);
			stmt.setInt(1, limit);
			result = this.getShortProductInfos(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllProducts(WProductGroupInfo wGroup) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT * FROM PRODUKT WHERE W_GRUPPE=?"
			);
			stmt.setInt(1, wGroup.getGroupId());
			result = this.getShortProductInfos(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllProducts(String searchedProduct) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT * FROM PRODUKT " +
					"WHERE REGEXP_LIKE (BEZEICHNUNG, ?, 'i')"
			);
			stmt.setString(1, searchedProduct);
			result = this.getShortProductInfos(stmt);
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
					"AND REGEXP_LIKE (PRODUKT.BEZEICHNUNG, ?, 'i')"
			);
			stmt.setString(1, searchedCategory);
			stmt.setString(2, searchedProduct);
			result = this.getShortProductInfos(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllGroupProducts(String searchedCategory) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT * FROM PRODUKT, W_KATEGORIE, W_GRUPPE " +
					"WHERE W_KATEGORIE.ID=PRODUKT.W_KATEGORIE " +
					"AND W_KATEGORIE.FK_GRUPPE_ID=W_GRUPPE.ID " +
					"AND W_GRUPPE.BEZEICHNUNG=? "
			);
			stmt.setString(1, searchedCategory);
			result = this.getShortProductInfos(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<ShortProductInfo> getAllProducts(WProductGroupInfo wGroup, int limit) {
		List<ShortProductInfo> result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT * FROM PRODUKT " +
					"WHERE W_GRUPPE=? AND ROWNUM<=?"
			);
			stmt.setInt(1, wGroup.getGroupId());
			stmt.setInt(2, limit);
			result = this.getShortProductInfos(stmt);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	private List<ShortProductInfo> getShortProductInfos(final PreparedStatement preparedStatement) throws
			SQLException {

		final List<ShortProductInfo> result = new ArrayList<ShortProductInfo>();
		final ResultSet rset = preparedStatement.executeQuery();
		while(rset.next()) {
			if(new File(IMG_DIR + rset.getInt(1)).exists()) {

			}
//			final byte[] imageData = this.imageEnrichmentFacade.getImageData(rset.getString(3));
//			if(imageData != null) {
//				try {
//					this.fileContentWriter.write(imageData, IMG_DIR + rset.getInt(1) + ".jpg");
//				} catch(FileContentWriteException e) {
//					this.logger.error(e.getMessage(), e);
//				}
//			}
			result.add(
					new ShortProductInfo(
							rset.getString(3),
							rset.getDouble(6),
							rset.getInt(1),
							this.imageEnrichmentFacade.getImageData(rset.getString(3))

					)
			);
		}
		preparedStatement.close();
		return result;
	}

	public DetailedProductInfo getDetailedProductInfo(ShortProductInfo shortProductInfo) {
		DetailedProductInfo result = null;
		try {
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT HERSTELLER.NAME, PRODUKTIMBESTAND.ANZAHL " +
					"FROM PRODUKT, HERSTELLER, PRODUKTIMBESTAND " +
					"WHERE PRODUKT.P_ID=? AND PRODUKT.HERSTELLER=HERSTELLER.ID AND PRODUKTIMBESTAND.PRODUKT=?"
			);
			stmt.setInt(1, shortProductInfo.getId());
			stmt.setInt(2, shortProductInfo.getId());
			final ResultSet rset = stmt.executeQuery();
			rset.next();
			result = new DetailedProductInfo(
					shortProductInfo.getName(),
					shortProductInfo.getPrice(),
					shortProductInfo.getId(),
					shortProductInfo.getImageData(),
					rset.getString(1),
					rset.getInt(2),
					null
			);
			stmt.close();
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}
}
