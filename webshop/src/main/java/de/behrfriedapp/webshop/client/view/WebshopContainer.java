package de.behrfriedapp.webshop.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import de.behrfriedapp.webshop.client.MainService;

import java.util.List;

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
    private final MainService mainService;

    @Inject
    public WebshopContainer(final MainService mainService) {
        this.webshopTitle = new Label("Wäpschob");
        this.productSearchBar = new ProductSearchBar();
        this.add(this.webshopTitle);
        this.add(this.productSearchBar);

        this.bindCategoryBox();
        this.mainService = mainService;
    }

    private void addClickHandler() {
        this.productSearchBar.getSearchButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                WebshopContainer.this.mainService.getAllProducts("TODO");
            }
        });
    }

    private void addChangeHandler() {
        this.productSearchBar.getSuggestBox().addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void bindCategoryBox() {
        this.productSearchBar.getCategoryBox().addItem("Alles");
       // List<String> productCategories = WebshopContainer.this.mainService.getProductGroups();
       // for(String str : productCategories) {
       //     this.productSearchBar.getCategoryBox().addItem(str);
        //}
    }
}
