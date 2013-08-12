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
    private HorizontalPanel productRowContainer;

    public ProductRow() {}

    public ProductRow(String imageURL, String hyperlink, double price) {
        this.productImage = new Image(imageURL);
        this.productLink = new Hyperlink(hyperlink, "");

        this.productPrice = new Label("  -  " + NumberFormat.getCurrencyFormat().format(price));
        this.productPrice.setWidth("60px");
        this.add(this.productImage);
        this.add(this.productLink);
        this.add(this.productPrice);
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
