package de.behrfriedapp.webshop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class webshop implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  RootPanel.get().add(new WebshopContainer());
  }
}
