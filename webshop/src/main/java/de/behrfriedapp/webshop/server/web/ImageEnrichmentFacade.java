package de.behrfriedapp.webshop.server.web;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

/**
 * @author marcus
 */
public class ImageEnrichmentFacade {

	/**
	 * {@link org.slf4j.Logger} for logging messages
	 */
	private final Logger logger = LoggerFactory.getLogger(ImageEnrichmentFacade.class);

	private final GImageSearchUrlCreator gImageSearchUrlCreator;
	private final HttpAccess httpAccess;
	private final GImageSearchUrlExtractor gImageSearchUrlExtractor;
	private final ImageUrlDownloader imageUrlDownloader;

	@Inject
	public ImageEnrichmentFacade(final GImageSearchUrlCreator gImageSearchUrlCreator, final HttpAccess httpAccess,
								 final GImageSearchUrlExtractor gImageSearchUrlExtractor,
								 final ImageUrlDownloader imageUrlDownloader) {
		this.gImageSearchUrlCreator = gImageSearchUrlCreator;
		this.httpAccess = httpAccess;
		this.gImageSearchUrlExtractor = gImageSearchUrlExtractor;
		this.imageUrlDownloader = imageUrlDownloader;
	}

	public String getImageData(String productName) {
		String imageData = null;
		try {
			imageData = this.imageUrlDownloader.downloadImageAsString(
					this.gImageSearchUrlExtractor.extractUrl(
						this.httpAccess.getResult(
							this.gImageSearchUrlCreator.createUrl(productName)
						)
					)
			);
		} catch(MalformedURLException e) {
			this.logger.error(e.getMessage(), e);
		}
		return imageData;
	}
}