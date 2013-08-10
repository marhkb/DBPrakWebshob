package de.behrfriedapp.webshop.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
public class ProductRow {
    private Image productImage;
    private Hyperlink productLink;
    private Label productPrice;
    private HorizontalPanel productRowContainer;

    public ProductRow() {
        this.productRowContainer = new HorizontalPanel();
        this.productImage = new Image();
        this.productLink = new Hyperlink();
        this.productPrice = new Label();
        this.productRowContainer.add(this.productImage);
        this.productRowContainer.add(this.productLink);
        this.productRowContainer.add(this.productPrice);
    }

    public Image getProductImage() {
        return productImage;
    }

    public Hyperlink getProductLink() {
        return productLink;
    }

    public Label getProductPrice() {
        return productPrice;
    }

    public HorizontalPanel getProductRowContainer() {
        return productRowContainer;
    }
}
