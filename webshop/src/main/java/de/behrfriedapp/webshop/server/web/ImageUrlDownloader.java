package de.behrfriedapp.webshop.server.web;

import java.net.MalformedURLException;

/**
 * @author marcus
 */
public interface ImageUrlDownloader {

	byte[] downloadImage(String imageUrl) throws MalformedURLException;
}
