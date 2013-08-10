package de.behrfriedapp.webshop.client.view;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class WebshopContainer extends VerticalPanel {

    private VerticalPanel webshobContainer;
    private Label webshopTitle;
    private ProductSearchBar productSearchBar;

    public WebshopContainer() {
        this.webshopTitle = new Label("Wäpschob");
        this.productSearchBar = new ProductSearchBar();
        this.add(this.webshopTitle);
        this.add(this.productSearchBar);
    }

}
