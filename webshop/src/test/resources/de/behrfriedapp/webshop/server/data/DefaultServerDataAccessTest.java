package de.behrfriedapp.webshop.server.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marcus
 */
public class DefaultServerDataAccessTest {

	private DefaultServerDataAccess serverDataAccess;

	@Before
	public void setUp() throws Exception {
		this.serverDataAccess = new DefaultServerDataAccess();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetAllProducts() throws Exception {

	}

	@Test
	public void testGetAllProducts_limit() throws Exception {
		this.serverDataAccess.getAllProducts(10);
	}

	@Test
	public void testGetAllProducts_wCategory() throws Exception {

	}

	@Test
	public void testGetAllProducts_wCategory_limit() throws Exception {

	}

	@Test
	public void testGetDetailedProductInfo() throws Exception {

	}
}
