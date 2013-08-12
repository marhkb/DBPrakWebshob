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
