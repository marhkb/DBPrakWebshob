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
import de.behrfriedapp.webshop.client.MainServiceAsync;
import de.behrfriedapp.webshop.client.Messages;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class ProductDetailView extends FlowPanel {

	private final static String RATING_IMG_WIDTH = "16px";

    VerticalPanel productInfoPanel, imagePanel;
    ProductSuggestionContainer productSuggestionContainer;
    Label searchedProductLabel, producerLabel, priceStringLabel, priceLabel, shippingLabel, inStockLabel, ratingLabel;
    Button dummyBuyButton;
    final MainServiceAsync mainService;
    VerticalPanel panel;
    Messages messages;
    HorizontalPanel pricePanel, ratePanel;
    RatingView ratingView;
    Image productImage;

    public ProductDetailView(DetailedProductInfo detailedProductInfo, Messages messages, MainServiceAsync mainService, VerticalPanel panel) {
        this.imagePanel = new VerticalPanel();
        this.mainService = mainService;
        this.panel = panel;
        this.messages = messages;


        this.productInfoPanel = new VerticalPanel();
        this.searchedProductLabel = new Label(detailedProductInfo.getName());
        this.searchedProductLabel.setStyleName("searchedProductLabel");
        this.producerLabel = new Label("von " + detailedProductInfo.getManufactor());

        this.pricePanel = new HorizontalPanel();
        this.pricePanel.setStyleName("pricePanel");
        this.priceStringLabel = new Label("Preis: ");
        this.priceStringLabel.setStyleName("priceStringLabel");
        this.priceLabel = new Label(NumberFormat.getCurrencyFormat().format(detailedProductInfo.getPrice()));
        this.priceLabel.setStyleName("priceLabel");
        this.shippingLabel = new Label(" kostenlose Lieferung mit Wäpschob Prime");
        this.shippingLabel.setStyleName("shippingLabel");
        this.pricePanel.add(this.priceStringLabel);
        this.pricePanel.add(this.priceLabel);
        this.pricePanel.add(this.shippingLabel);

        //TODO RATING!
        this.ratePanel = new HorizontalPanel();
        this.ratePanel.setStyleName("detRatePanel");
        this.ratingLabel = new Label(" (XX Bewertungen)");
        this.ratingLabel.setStyleName("ratingLabel");

		Image ratingImg = new Image("img/rated.png");
		ratingImg.setWidth(RATING_IMG_WIDTH);
        this.ratePanel.add(ratingImg);
		ratingImg = new Image("img/rated.png");
		ratingImg.setWidth(RATING_IMG_WIDTH);
		this.ratePanel.add(ratingImg);
		ratingImg = new Image("img/rated.png");
		ratingImg.setWidth(RATING_IMG_WIDTH);
		this.ratePanel.add(ratingImg);
		ratingImg = new Image("img/rated.png");
		ratingImg.setWidth(RATING_IMG_WIDTH);
		this.ratePanel.add(ratingImg);
		ratingImg = new Image("img/rated.png");
		ratingImg.setWidth(RATING_IMG_WIDTH);
		this.ratePanel.add(ratingImg);

        this.ratePanel.add(this.ratingLabel);

        this.dummyBuyButton = new Button("In den Warenkorb legen");
        this.dummyBuyButton.setStyleName("basketButton");
        this.inStockLabel = new Label("Noch " + detailedProductInfo.getStock() + " vorhanden");
        this.productInfoPanel.add(this.searchedProductLabel);
        this.productInfoPanel.add(this.producerLabel);
        this.productInfoPanel.add(this.pricePanel);
        this.productInfoPanel.add(this.ratePanel);
        this.productInfoPanel.add(this.dummyBuyButton);
        this.productInfoPanel.add(this.inStockLabel);

        this.productSuggestionContainer = new ProductSuggestionContainer();
        if(detailedProductInfo.getSimiliarProducts() != null) {
            int i = 0;
            for(final ShortProductInfo productInfo : detailedProductInfo.getSimiliarProducts()){
                VerticalPanel tmpPanel = new VerticalPanel();
                if(i%2==0) {

                }
                Hyperlink tmpLink = new Hyperlink(productInfo.getName(),"");
                tmpLink.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        ProductDetailView.this.mainService.getDetailedProductInfo(productInfo.getId(),
																				  new AsyncCallback<DetailedProductInfo>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                //To change body of implemented methods use File | Settings | File Templates.
                            }
                            @Override
                            public void onSuccess(DetailedProductInfo result) {
                                ProductDetailView.this.panel.clear();
                                ProductDetailView detailView = new ProductDetailView(result, ProductDetailView.this.messages, ProductDetailView.this.mainService, ProductDetailView.this.panel);
                                ProductDetailView.this.panel.add(detailView);
                            }
                        });

                    }
                });
                Label tmpLabel = new Label(" - "+ NumberFormat.getCurrencyFormat().format(productInfo.getPrice()));
                tmpPanel.add(tmpLink);
                tmpPanel.add(tmpLabel);
                ProductDetailView.this.productSuggestionContainer.add(tmpPanel);
            }
        }

        this.setStyleName("productDetailView");
        this.imagePanel.setStyleName("dImagePanel");
        this.productInfoPanel.setStyleName("dProductInfoPanel");
        this.ratingView = new RatingView(detailedProductInfo, this.mainService);
        this.productImage.setWidth("150px");
		this.imagePanel.add(this.productImage);
        this.add(this.imagePanel);
        this.add(this.productInfoPanel);
        this.add(this.productSuggestionContainer);
        this.add(this.ratingView);
        this.addClickHandler();
    }

    private void addClickHandler() {
        this.dummyBuyButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window.alert("Produkt wurde in ihren Warenkorb gelegt");
            }
        });
    }
}
