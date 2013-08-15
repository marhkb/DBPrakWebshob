package de.behrfriedapp.webshop.server.web;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author marcus
 */
public class FirstEntryGImageSearchUrlExtractorTest {

	private GImageSearchUrlExtractor gImageSearchUrlExtractor;

	@Before
	public void setUp() throws Exception {
		this.gImageSearchUrlExtractor = new FirstEntryGImageSearchUrlExtractor();
	}

	@Test
	public void testExtractUrl() throws Exception {
		final String src = "\"th\":230,\"tu\":\"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTwL5pXxjEn-5DGI-et9l9zN5zfz27F1annbgNtwzdLRFw4wopE7w\",\"tw\":219}</div></div><div class=\"rg_di\" i1527=\"118\" i3588=\"117\" ><a href=\"http://www.google.com/imgres?um=1&amp;safe=off&amp;sa=N&amp;qscrl=1&amp;hl=de&amp;biw=1600&amp;bih=788&amp;tbm=isch&amp;tbnid=-tW-5EbRRvlFGM:&amp;imgrefurl=http://www.duden.de/rechtschreibung/Wurst&amp;docid=WA4cKixFalcDNM&amp;imgurl=http://duden.de/_media_/full/W/Wurst-201100281498.jpg&amp;w=699&amp;h=464&amp;ei=QAUNUrHIIoHGtQaWsoGIBg&amp;zoom=1\" class=rg_l ><img class=rg_i name=-tW-5EbRRvlFGM: data-sz=f  onload=\"google.stb.csi.onTbn(1, this)\"></a><div class=rg_meta>{\"fd\":\"\",\"fn\":\"Wurst-201100281498.jpg\",\"id\":\"-tW-5EbRRvlFGM:\",\"is\":\"699\\u0026nbsp;\\u0026#215;\\u0026nbsp;464\",\"isu\":\"duden.de\",\"ity\":\"jpg\",\"lu\":\"/search?q=wurst\\u0026um=1\\u0026safe=off\\u0026sa=N\\u0026qscrl=1\\u0026hl=de\\u0026biw=1600\\u0026bih=788\\u0026tbm=isch#imgrc=-tW-5EbRRvlFGM%3A%3BWA4cKixFalcDNM%3Bhttp%253A%252F%252Fduden.de%252F_media_%252Ffull%252FW%252FWurst-201100281498.jpg%3Bhttp%253A%252F%252Fwww.";

		final String expected = "http://duden.de/_media_/full/W/Wurst-201100281498.jpg";
		final String actual = this.gImageSearchUrlExtractor.extractUrl(src);

		Assert.assertEquals(expected, actual);
	}
}
