package de.behrfriedapp.webshop.server.web;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marcus
 */
public class DefaultImageUrlDownloaderTest {

	private ImageUrlDownloader imageUrlDownloader;

	@Before
	public void setUp() {
		this.imageUrlDownloader = new DefaultImageUrlDownloader();
	}

	@Test
	public void testDownloadImage() throws Exception {
		byte[] image = this.imageUrlDownloader.downloadImageAsByteArr(
				"http://www.dienstleistungen-24.net/export/0d83ce58348272a7c1c8d7daebbe3.jpg"
		);
		Assert.assertNotNull(image);
		Assert.assertTrue(image.length > 0);
	}
}
