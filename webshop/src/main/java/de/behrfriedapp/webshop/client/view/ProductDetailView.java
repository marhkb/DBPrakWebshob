package de.behrfriedapp.webshop.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class ProductDetailView extends HorizontalPanel {
VerticalPanel productDetailContainer, productSuggestions;
    Label searchedProductLabel, suggestedProductsLabel;
    String siteName;

    public ProductDetailView(DetailedProductInfo detailedProductInfo) {
        this.productDetailContainer = new VerticalPanel();
        this.productSuggestions = new VerticalPanel();
        this.searchedProductLabel = new Label("Ihr gesuchtes Produkt "+detailedProductInfo);
        this.suggestedProductsLabel = new Label("Weitere Produkte die sie interessieren könnten:");
        this.productDetailContainer.setWidth("70%");
        this.productSuggestions.setWidth("30%");
        this.siteName = detailedProductInfo+"";
        this.add(this.productDetailContainer);
        this.add(this.productSuggestions);
    }


}
