package de.behrfriedapp.webshop.server;

import de.behrfriedapp.webshop.client.MainService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements
														  MainService {

  public String greetServer(String input) throws IllegalArgumentException {
    return null;
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    return null;
  }
}
