package de.behrfriedapp.webshop.client.view;

import com.google.gwt.user.client.ui.*;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 14.08.13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class RatingView extends HorizontalPanel{
    VerticalPanel rateDescriptionPanel;
    VerticalPanel ratingPanel;
    Label rateThisProductLabel, customerNumberLabel, starRatingLabel, ratingDescriptionLabel;
    Button rateProductButton;
    TextBox customerNumberTextBox;
    TextArea ratingTextArea;
    boolean firstClick;

    public RatingView(DetailedProductInfo detailedProductInfo) {
        this.setStyleName("ratingView");
        this.firstClick = true;
        this.rateDescriptionPanel = new VerticalPanel();
        this.rateDescriptionPanel.setStyleName("ratedescriptionPanel");
        this.rateThisProductLabel = new Label("Bewerten Sie dieses Produkt! (nur Möglich wenn bereits gekauft)");
        this.rateThisProductLabel.setStyleName("rateThisProductLabel");
        this.customerNumberLabel = new Label("Ihre Kundennummer:");
        this.customerNumberLabel.setStyleName("customerNumberLabel");
        this.starRatingLabel = new Label("Bewertung:");
        this.starRatingLabel.setStyleName("starRatingLabel");
        this.ratingDescriptionLabel = new Label("Bewertungstext:");
        this.ratingDescriptionLabel.setStyleName("ratingDescriptionLabel");
        this.rateDescriptionPanel.add(this.rateThisProductLabel);
        this.rateDescriptionPanel.add(this.customerNumberLabel);
        this.rateDescriptionPanel.add(this.starRatingLabel);
        this.rateDescriptionPanel.add(this.ratingDescriptionLabel);

        this.ratingPanel = new VerticalPanel();
        this.ratingPanel.setStyleName("ratingPanel");
        this.customerNumberTextBox = new TextBox();
        this.customerNumberTextBox.setStyleName("customerNumberTextBox");
        this.ratingTextArea = new TextArea();
        this.ratingTextArea.setStyleName("ratingTextArea");
        this.rateProductButton = new Button("Bewertung abgeben");
        this.rateProductButton.setStyleName("rateProductButton");
        Label ratingLabel = new Label("(*)(*)(*)(*)(*)");
        ratingLabel.setStyleName("rateLabel");
        this.ratingPanel.add(this.customerNumberTextBox);
        this.ratingPanel.add(ratingLabel);
        this.ratingPanel.add(this.ratingTextArea);
        this.ratingPanel.add(this.rateProductButton);


        this.add(this.rateDescriptionPanel);
        this.add(this.ratingPanel);
    }


}
