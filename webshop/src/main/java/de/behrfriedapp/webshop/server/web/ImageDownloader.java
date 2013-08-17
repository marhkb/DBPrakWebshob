package de.behrfriedapp.webshop.server.web;

import java.net.MalformedURLException;

/**
 * @author marcus
 */
public interface ImageDownloader {

	public final class ImageData {

		private final String data;
		private final String ext;
		private final String gwtImageData;

		public ImageData(String data, String ext) {
			this.data = data;
			this.ext = ext;
			this.gwtImageData = this.toGwtImageData(this.data, this.ext);
		}

		public String getData() {
			return data;
		}

		public String getExt() {
			return ext;
		}

		public String getGwtImageData() {
			return gwtImageData;
		}

		public String toString() {
			return toGwtImageData(this.data, this.ext);
		}

		public static String toGwtImageData(final String data, final String ext) {
			return "data:image/" + ext + " ;base64," + data;
		}
	}

	byte[] downloadImageAsByteArr(String imageUrl) throws MalformedURLException;

	ImageData downloadImageAsString(String imageUrl) throws MalformedURLException;
}