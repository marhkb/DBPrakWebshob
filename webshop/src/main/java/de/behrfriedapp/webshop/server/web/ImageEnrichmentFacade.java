package de.behrfriedapp.webshop.server.web;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

/**
 * @author marcus
 */
public class ImageEnrichmentFacade {


	/**
	 * {@link org.slf4j.Logger} for logging messages
	 */
	private final Logger logger = LoggerFactory.getLogger(ImageEnrichmentFacade.class);
	private final ImageSearchUrlCreator imageSearchUrlCreator;
	private final HttpAccess httpAccess;
	private final ImageSearchUrlExtractor imageSearchUrlExtractor;
	private final ImageDownloader imageDownloader;

	@Inject
	public ImageEnrichmentFacade(final ImageSearchUrlCreator imageSearchUrlCreator, final HttpAccess httpAccess,
								 final ImageSearchUrlExtractor imageSearchUrlExtractor,
								 final ImageDownloader imageDownloader) {
		this.imageSearchUrlCreator = imageSearchUrlCreator;
		this.httpAccess = httpAccess;
		this.imageSearchUrlExtractor = imageSearchUrlExtractor;
		this.imageDownloader = imageDownloader;
	}

	public ImageDownloader.ImageData getImageData(String productName) {
		ImageDownloader.ImageData imageData = null;
		try {
			final List<String> urls = this.imageSearchUrlExtractor.extractUrls(
					this.httpAccess.getResult(
							this.imageSearchUrlCreator.createUrl(productName)
					)
			);
			final Iterator<String> urlsIt = urls.iterator();
			while(imageData == null && urlsIt.hasNext()) {
				imageData = this.imageDownloader.downloadImageAsString(
						urlsIt.next()
				);
			}
		} catch(MalformedURLException e) {
			this.logger.error(e.getMessage(), e);
		}
		return imageData;
	}
}
