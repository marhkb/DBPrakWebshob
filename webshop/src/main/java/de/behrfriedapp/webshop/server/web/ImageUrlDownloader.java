package de.behrfriedapp.webshop.server.web;

import java.net.MalformedURLException;

/**
 * @author marcus
 */
public interface ImageUrlDownloader {

	byte[] downloadImageAsByteArr(String imageUrl) throws MalformedURLException;

	String downloadImageAsString(String imageUrl) throws MalformedURLException;
}