package de.behrfriedapp.webshop.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author marcus
 */
public class DefaultImageUrlDownloader implements ImageUrlDownloader {

	private final Logger logger = LoggerFactory.getLogger(DefaultImageUrlDownloader.class);

	@Override
	public byte[] downloadImage(String imageUrl) throws MalformedURLException {
		final URL url = new URL(imageUrl);
		InputStream is = null;
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			is = url.openStream();
			int length;
			while ((length = is.read()) != -1) {
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
}
