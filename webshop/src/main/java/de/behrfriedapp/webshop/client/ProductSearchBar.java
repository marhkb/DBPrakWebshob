package de.behrfriedapp.webshop.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;


/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class ProductSearchBar extends HorizontalPanel {
    private ListBox categoryBox;
    private SuggestBox suggestBox;
    private Button searchButton;

    public ProductSearchBar() {
        this.categoryBox = new ListBox();
        this.categoryBox.setWidth("150px");
        this.suggestBox = new SuggestBox();
        this.suggestBox.setWidth("200px");
        this.searchButton = new Button("Suchen");
        this.add(this.categoryBox);
        this.add(this.suggestBox);
        this.add(this.searchButton);
    }

    private void addClickHandlers() {
        this.searchButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

            }
        });
    }


}
