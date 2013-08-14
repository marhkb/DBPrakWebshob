package de.behrfriedapp.webshop.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author marcus
 */
public class DefaultHttpAccess implements HttpAccess {
	private final Logger logger = LoggerFactory.getLogger(DefaultHttpAccess.class);

	public String getResult(final String query) {
		InputStream inputStream = null;
		try {
			inputStream = new URL(query).openStream();
			int r = 0;
			final StringBuilder builder = new StringBuilder();
			while((r = inputStream.read()) != -1) {
				builder.append((char)r);
			}
			return builder.toString();
		} catch(final MalformedURLException e1) {
			this.logger.error(e1.getMessage(), e1);
		} catch(final IOException e2) {
			this.logger.error(e2.getMessage(), e2);
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch(final IOException e) {
					this.logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}
}
