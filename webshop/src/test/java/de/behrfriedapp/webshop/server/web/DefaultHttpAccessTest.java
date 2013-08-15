package de.behrfriedapp.webshop.server.web;

import org.junit.Before;
import org.junit.Test;

/**
 * @author marcus
 */
public class DefaultHttpAccessTest {

	private HttpAccess httpAccess;

	@Before
	public void setUp() throws Exception {
		this.httpAccess = new DefaultHttpAccess();
	}

	@Test
	public void testGetResult() throws Exception {
		final String result = httpAccess.getResult("https://www.google.com/search?q=geld&qscrl=1&source=lnms&tbm=isch");

	}
}
