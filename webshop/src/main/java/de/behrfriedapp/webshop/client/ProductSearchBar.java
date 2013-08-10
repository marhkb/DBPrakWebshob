package de.behrfriedapp.webshop.client;

import com.google.gwt.user.client.ui.*;


/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class ProductSearchBar {
    private HorizontalPanel searchContainer;
    private Label webshopLabel;
    private ListBox categoryBox;
    private SuggestBox suggestBox;
    private Button searchButton;

    public ProductSearchBar() {
        this.searchContainer = new HorizontalPanel();
        this.webshopLabel = new Label("Webshop");
        this.searchContainer.add(this.webshopLabel);

        this.searchContainer = new HorizontalPanel();
        this.categoryBox = new ListBox();
        this.suggestBox = new SuggestBox();
        this.searchButton = new Button();
        this.searchContainer.add(this.categoryBox);
        this.searchContainer.add(this.suggestBox);
        this.searchContainer.add(this.searchButton);
    }
}
