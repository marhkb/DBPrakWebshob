/*
 * Copyright 2013 Marcus Behrendt, Gerrit Appeler & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.behrfriedapp.webshop.client.view;

import com.google.gwt.i18n.client.NumberFormat;
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
public class ProductRow extends HorizontalPanel {
    private Image productImage;
    private Hyperlink productLink;
    private Label productPrice;
    private HorizontalPanel imgPanel, productNamePanel, productPricePanel;

    public ProductRow() {}

    public ProductRow(String imageURL, String hyperlink, double price) {
        this.imgPanel = new HorizontalPanel();
        this.productImage = new Image("/img/muffin.jpg");
        this.productImage.setWidth("80px");
        this.productImage.setHeight("80px");
        this.imgPanel.add(this.productImage);
        this.imgPanel.setStyleName("overviewImgPanel");

        this.productNamePanel = new HorizontalPanel();
        this.productLink = new Hyperlink(hyperlink, "");
        this.productLink.setStyleName("hylink");
        this.productNamePanel.add(this.productLink);
        this.productNamePanel.setStyleName("overviewProductNamePanel");

        this.productPricePanel = new HorizontalPanel();
        this.productPrice = new Label(NumberFormat.getCurrencyFormat().format(price));
        this.productPricePanel.add(this.productPrice);
        this.productPricePanel.setStyleName("overviewProductPricePanel");
        this.add(this.imgPanel);
        this.add(this.productNamePanel);
        this.add(this.productPricePanel);
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
}
