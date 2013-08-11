package de.behrfriedapp.webshop.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
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

    public ProductDetailView(DetailedProductInfo detailedProductInfo) {
        productDetailContainer = new VerticalPanel();
        productSuggestions = new VerticalPanel();
        productDetailContainer.setWidth("70%");
        productSuggestions.setWidth("30%");
        this.add(this.productDetailContainer);
        this.add(this.productSuggestions);
    }
}
