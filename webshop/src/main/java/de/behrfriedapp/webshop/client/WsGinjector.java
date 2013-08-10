package de.behrfriedapp.webshop.client;

/**
 * @author marcus
 */

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;


@GinModules(WsGinModule.class)
public interface WsGinjector extends Ginjector {

	/**
	 * Returns an implementation of {@link ShellView} as declared in
	 * {@link WaGinModule}'s 'bind' method.
	 *
	 * @return an implementation of {@link ShellView} as declared in
	 *         {@link WaGinModule}'s 'bind' method
	 */
	Object getShellView();
}
