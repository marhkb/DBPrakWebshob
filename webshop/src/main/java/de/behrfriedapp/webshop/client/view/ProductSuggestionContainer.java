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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 11.08.13
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class ProductSuggestionContainer extends ScrollPanel {
    private Label containerTitle;

    public ProductSuggestionContainer() {
        this.setStyleName("productSuggestionContainer");
        this.containerTitle = new Label("Weitere Produkte die Sie interessieren könnten:");
        this.add(this.containerTitle);

    }
    private void addClickHandler(Hyperlink tempLink) {

    }

}
