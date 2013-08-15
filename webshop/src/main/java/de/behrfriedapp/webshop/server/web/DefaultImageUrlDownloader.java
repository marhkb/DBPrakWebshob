package de.behrfriedapp.webshop.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author marcus
 */
public class DefaultImageUrlDownloader implements ImageUrlDownloader {

	/**
	 * {@link org.slf4j.Logger} for logging messages
	 */
	private final Logger logger = LoggerFactory.getLogger(DefaultImageUrlDownloader.class);

	@Override
	public byte[] downloadImageAsByteArr(String imageUrl) throws MalformedURLException {
		final URL url = new URL(imageUrl);
		InputStream is = null;
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty(
					"user-agent",
					"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
					"Ubuntu Chromium/28.0.1500.71 Chrome/28.0.1500.71 Safari/537.36"
			);
			conn.setRequestProperty("Referer", "http://" + url.getHost());
			this.logger.info(url.getHost());
			conn.connect();
			is = conn.getInputStream();
			int length;
			while((length = is.read()) != -1) {
				os.write(length);
			}
		} catch(IOException e) {
			this.logger.error(e.getMessage(), e);
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch(IOException e) {
					this.logger.error(e.getMessage(), e);
				}
			}
		}
		final byte[] result = os.toByteArray();
		try {
			os.close();
		} catch(IOException e) {
			this.logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public String downloadImageAsString(String imageUrl) throws MalformedURLException {
		return new String(this.downloadImageAsByteArr(imageUrl));
	}
}
