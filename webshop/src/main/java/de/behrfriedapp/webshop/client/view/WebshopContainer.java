package de.behrfriedapp.webshop.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import de.behrfriedapp.webshop.client.MainServiceAsync;

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
    private final MainServiceAsync mainService;

    @Inject
    public WebshopContainer(final MainServiceAsync mainService) {
        this.webshopTitle = new Label("Wäpschob");
        this.productSearchBar = new ProductSearchBar();
        this.add(this.webshopTitle);
        this.add(this.productSearchBar);

        this.mainService = mainService;
        this.bind();
        this.addClickHandler();
        this.addChangeHandler();
    }

    private void addClickHandler() {
        this.productSearchBar.getSearchButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                //WebshopContainer.this.mainService.getAllProducts();
            }
        });
    }

    private void addChangeHandler() {
        this.productSearchBar.getSuggestBox().addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                if (WebshopContainer.this.productSearchBar.getSuggestBox().getValue().equals("")) {
                    WebshopContainer.this.productSearchBar.getSearchButton().setEnabled(false);
                } else {
                    WebshopContainer.this.productSearchBar.getSearchButton().setEnabled(true);
                }
            }
        });
    }

    private void bind() {
        this.productSearchBar.getCategoryBox().addItem("Alles");
        //List<WCategoryInfo> productCategories = WebshopContainer.this.mainService.getAllCategories();
//        for(WCategoryInfo str : productCategories) {
//            this.productSearchBar.getCategoryBox().addItem(str.getName());
//       }
        MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
        //List<ShortProductInfo> suggestion = WebshopContainer.this.mainService.getAllProducts();
//        for(ShortProductInfo info : suggestion) {
//            oracle.add(info.getName());
//        }
        this.productSearchBar.setSuggestBox(new SuggestBox(oracle));
    }
}
