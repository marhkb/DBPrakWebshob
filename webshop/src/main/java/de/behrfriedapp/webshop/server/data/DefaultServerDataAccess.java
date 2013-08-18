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
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import de.behrfriedapp.webshop.server.web.ImageDownloader;
import de.behrfriedapp.webshop.server.web.ImageEnrichmentFacade;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private Connection conn;

	@Inject
	public DefaultServerDataAccess(final ImageEnrichmentFacade imageEnrichmentFacade) {
		this.imageEnrichmentFacade = imageEnrichmentFacade;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch(ClassNotFoundException e) {
			this.logger.error(e.getMessage(), e);
		}
		this.connect();
	}

	private void connect() {
		try {
			this.conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSW);
			this.conn.setAutoCommit(false);
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void attemptReconnect() throws SQLException {
		if(this.conn == null || this.conn.isClosed()) {
			synchronized(this) {
				if(this.conn == null || this.conn.isClosed()) {
					this.connect();
				}
			}
		}
	}

	public List<WProductGroupInfo> getAllProductGroups() {
		List<WProductGroupInfo> result = null;
		try {
			this.attemptReconnect();
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
			this.attemptReconnect();
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
			this.attemptReconnect();
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT DISTINCT BEZEICHNUNG FROM PRODUKT"
			);
			final ResultSet rset = stmt.executeQuery();
			while(rset.next()) {
				result.add(rset.getString(1));
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
			this.attemptReconnect();
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT P_ID, BEZEICHNUNG, PREIS, BILD, BILD_FORMAT " +
					"FROM PRODUKT " +
					"WHERE ROWNUM<=?"
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
			this.attemptReconnect();
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT DISTINCT P_ID, BEZEICHNUNG, PREIS, BILD, BILD_FORMAT, BEWERTUNG " +
					"FROM PRODUKT WHERE W_GRUPPE=?"
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
			this.attemptReconnect();
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT P_ID, BEZEICHNUNG, PREIS, BILD, BILD_FORMAT, BEWERTUNG " +
					"FROM PRODUKT " +
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
			this.attemptReconnect();
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT P_ID, BEZEICHNUNG, PREIS, BILD, BILD_FORMAT, BEWERTUNG " +
					"FROM PRODUKT, W_KATEGORIE, W_GRUPPE " +
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
			this.attemptReconnect();
			PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT P_ID, BEZEICHNUNG, PREIS, BILD, BILD_FORMAT, BEWERTUNG " +
					"FROM PRODUKT, W_KATEGORIE, W_GRUPPE " +
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
			this.attemptReconnect();
			final PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT P_ID, BEZEICHNUNG, PREIS, BILD, BILD_FORMAT, BEWERTUNG " +
					"FROM PRODUKT " +
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
		final List<Object[]> buffer = new ArrayList<Object[]>();
		while(rset.next()) {
			final Object[] data = new Object[5];
			data[0] = rset.getString(2);
			data[1] = rset.getDouble(3);
			data[2] = rset.getInt(1);
			data[4] = rset.getDouble(6);
			final Blob blob = rset.getBlob(4);
			if(blob != null) {
				result.add(
						new ShortProductInfo(
								(String)data[0],
								(Double)data[1],
								(Integer)data[2],
								ImageDownloader.ImageData.toGwtImageData(
										Base64.encode(
												blob.getBytes(1, (int)blob.length())
										), rset.getString(5)
								),
								(Double)data[4]
						)
				);
			} else {
				buffer.add(data);
			}
		}
		final List<Thread> threads = new ArrayList<Thread>();
		for(final Object[] data : buffer) {
			final Thread thread = new Thread() {
				@Override
				public void run() {
					data[3] = imageEnrichmentFacade.getImageData((String)data[0]);
					result.add(
							new ShortProductInfo(
									(String)data[0],
									(Double)data[1],
									(Integer)data[2],
									((ImageDownloader.ImageData)data[3]).toString(),
									(Double)data[4]
							)
					);
				}
			};
			threads.add(thread);
			thread.start();

			if(threads.size() == 16) {
				for(final Thread t : threads) {
					try {
						t.join();
					} catch(InterruptedException e) {
						this.logger.error(e.getMessage(), e);
					}
				}
				threads.clear();
			}
		}
		for(final Thread t : threads) {
			try {
				t.join();
			} catch(InterruptedException e) {
				this.logger.error(e.getMessage(), e);
			}
		}

		for(final Object[] data : buffer) {
			final PreparedStatement stmt = this.conn.prepareStatement(
					"UPDATE PRODUKT " +
					"SET BILD=?, BILD_FORMAT=? " +
					"WHERE P_ID=?"
			);
			final ImageDownloader.ImageData imageData = (ImageDownloader.ImageData)data[3];

			final byte[] value = Base64.decode(imageData.getData());
			ByteArrayInputStream bais1 = new ByteArrayInputStream(value);
			stmt.setBinaryStream(1, bais1, value.length);

			stmt.setString(2, imageData.getExt());
			stmt.setInt(3, (Integer)data[2]);
			stmt.executeUpdate();
			stmt.close();
		}
		this.conn.commit();

		Collections.sort(
				result, new Comparator<ShortProductInfo>() {
			@Override
			public int compare(ShortProductInfo o1, ShortProductInfo o2) {
				return o1.getName().compareTo(o2.getName());
			}
		}
		);
		preparedStatement.close();
		return result;
	}

	public DetailedProductInfo getDetailedProductInfo(int pId) {
		DetailedProductInfo result = null;
		try {
			this.attemptReconnect();
			final PreparedStatement stmt = this.conn.prepareStatement(
					"SELECT PRODUKT.BEZEICHNUNG, PRODUKT.PREIS, PRODUKT.P_ID, PRODUKT.BILD, " +
					"PRODUKT.BILD_FORMAT, PRODUKT.BEWERTUNG, HERSTELLER.NAME, PRODUKTIMBESTAND.ANZAHL " +
					"FROM PRODUKT, HERSTELLER, PRODUKTIMBESTAND " +
					"WHERE PRODUKT.P_ID=? AND PRODUKT.HERSTELLER=HERSTELLER.ID AND PRODUKTIMBESTAND.PRODUKT=?"
			);
			stmt.setInt(1, pId);
			stmt.setInt(2, pId);
			final ResultSet rset = stmt.executeQuery();
			rset.next();

			final Blob blob = rset.getBlob(4);
			result = new DetailedProductInfo(
					rset.getString(1),
					rset.getDouble(2),
					rset.getInt(3),
					ImageDownloader.ImageData.toGwtImageData(
							Base64.encode(
									blob.getBytes(1, (int)blob.length())
							), rset.getString(5)
					),
					rset.getDouble(6),
					rset.getString(7),
					rset.getInt(8),
					null
			);
			stmt.close();
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public void rateProduct(int kId, int pId, int rating, String text) {
		try {
			this.attemptReconnect();
			final PreparedStatement stmt = this.conn.prepareStatement(
					"MERGE " +
					"    INTO PRODUKTBEWERTUNG tgt " +
					"    USING DUAL " +
					"    ON (tgt.FK_KUNDE_ID = ? AND tgt.FK_PRODUKT_ID = ?) " +
					"    WHEN MATCHED THEN " +
					"      UPDATE SET tgt.BEWERTUNG = ?, tgt.BESCHREIBUNG = ? " +
					"    WHEN NOT MATCHED THEN " +
					"      INSERT VALUES ( ?, ?, ?, ?)"
			);
			stmt.setInt(1, kId);
			stmt.setInt(2, pId);
			stmt.setInt(3, rating);
			stmt.setString(4, text);

			stmt.setInt(5, kId);
			stmt.setInt(6, pId);
			stmt.setInt(7, rating);
			stmt.setString(8, text);

			stmt.execute();
			stmt.close();

			this.conn.commit();
		} catch(SQLException e) {
			this.logger.error(e.getMessage(), e);
		}
	}
}
