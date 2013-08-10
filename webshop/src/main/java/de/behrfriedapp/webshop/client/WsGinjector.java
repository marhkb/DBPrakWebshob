package de.behrfriedapp.webshop.client;

/**
 * @author marcus
 */

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import de.behrfriedapp.webshop.client.view.WebshopContainer;


@GinModules(WsGinModule.class)
public interface WsGinjector extends Ginjector {

	WebshopContainer getWebshopContainer();
}
