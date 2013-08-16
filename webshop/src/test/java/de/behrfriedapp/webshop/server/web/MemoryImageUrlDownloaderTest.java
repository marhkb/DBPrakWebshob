package de.behrfriedapp.webshop.server.web;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marcus
 */
public class MemoryImageUrlDownloaderTest {

	private ImageUrlDownloader imageUrlDownloader;

	@Before
	public void setUp() {
		this.imageUrlDownloader = new MemoryImageUrlDownloader();
	}

	@Test
	public void testDownloadImage() throws Exception {
		byte[] image = this.imageUrlDownloader.downloadImageAsByteArr(
				"http://www.gonola.com/images/Grits.jpg"
		);
		Assert.assertNotNull(image);
		Assert.assertTrue(image.length > 0);
	}
}
