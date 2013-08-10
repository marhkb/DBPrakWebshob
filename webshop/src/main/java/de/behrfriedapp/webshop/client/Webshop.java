package de.behrfriedapp.webshop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import de.behrfriedapp.webshop.client.view.WebshopContainer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Webshop implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  final WebshopContainer webshopContainer = ((WsGinjector)GWT.create(WsGinjector.class)).getWebshopContainer();
	  RootPanel.get().add(webshopContainer);
  }
}
