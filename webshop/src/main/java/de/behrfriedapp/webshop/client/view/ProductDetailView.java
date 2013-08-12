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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.behrfriedapp.webshop.client.Messages;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class ProductDetailView extends HorizontalPanel {
    VerticalPanel productSuggestions, productInfoPanel;
    HorizontalPanel productDetailContainer, imagePanel;
    Label searchedProductLabel, suggestedProductsLabel, producerLabel, priceLabel, inStockLabel;
    Button dummyBuyButton;
    String siteName;

    public ProductDetailView(DetailedProductInfo detailedProductInfo, Messages messages) {
        this.productSuggestions = new VerticalPanel();
        this.searchedProductLabel = new Label(messages.yourSearchProduct() + " " + detailedProductInfo.getName());
        this.suggestedProductsLabel = new Label(messages.moreInterestingProducts());
        this.producerLabel = new Label("Hergestellt von: " + detailedProductInfo.getManufactor());
        this.priceLabel = new Label("Preis: " + detailedProductInfo.getPrice() + " kostenlose Lieferung mit Wäpschob Prime");
        this.dummyBuyButton = new Button("In den Warenkorb legen!");
        this.inStockLabel = new Label("Es sind noch " + detailedProductInfo.getStock() + " im Lager vorhanden");
        this.productDetailContainer.setWidth("70%");
        this.productSuggestions.setWidth("30%");
        this.siteName = detailedProductInfo + "";
        this.productInfoPanel.add(this.searchedProductLabel);
        this.productInfoPanel.add(this.producerLabel);
        this.productInfoPanel.add(this.priceLabel);
        this.productInfoPanel.add(this.dummyBuyButton);
        this.productInfoPanel.add(this.inStockLabel);
        this.productDetailContainer.add(this.imagePanel);
        this.productDetailContainer.add(this.productInfoPanel);
        this.productSuggestions.add(this.suggestedProductsLabel);
        this.add(this.productDetailContainer);
        this.add(this.productSuggestions);
    }


}
