package de.behrfriedapp.webshop.server.data;

import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
	public void testGetAllCategories() throws Exception {
		final List<WProductGroupInfo> result = this.serverDataAccess.getAllProductGroups();

		Assert.assertNotNull(result);
		Assert.assertFalse(result.size() == 0);
	}

	@Test
	public void testGetAllCategories_limit() throws Exception {
		final List<WProductGroupInfo> result = this.serverDataAccess.getAllProductGroups(10);

		Assert.assertNotNull(result);
		Assert.assertEquals(10, result.size());;
	}

	@Test
	public void testGetAllProducts() throws Exception {
		final List<ShortProductInfo> result = this.serverDataAccess.getAllProducts();

		Assert.assertNotNull(result);
		Assert.assertFalse(result.size() == 0);
	}

	@Test
	public void testGetAllProducts_limit() throws Exception {
		final List<ShortProductInfo> result = this.serverDataAccess.getAllProducts(10);

		Assert.assertNotNull(result);
		Assert.assertEquals(10, result.size());
	}

	@Test
	public void testGetAllProducts_wCategory() throws Exception {
		final List<ShortProductInfo> result = this.serverDataAccess.getAllProducts(new WProductGroupInfo(11, 12, "Wurst", "Schinken"));

		Assert.assertNotNull(result);
		Assert.assertFalse(result.size() == 0);
	}

	@Test
	public void testGetAllProducts_wCategory_limit() throws Exception {
		final List<ShortProductInfo> result = this.serverDataAccess.getAllProducts(new WProductGroupInfo(11, 12, "Wurst", "Schinken"), 4);

		Assert.assertNotNull(result);
		Assert.assertEquals(4, result.size());
	}

	@Test
	public void testGetDetailedProductInfo() throws Exception {

	}
}
