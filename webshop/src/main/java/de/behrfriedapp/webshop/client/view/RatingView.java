package de.behrfriedapp.webshop.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import de.behrfriedapp.webshop.client.MainServiceAsync;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 14.08.13
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class RatingView extends HorizontalPanel {

	private final static String RATING_IMG_WIDTH = "16px";

	private final MainServiceAsync mainService;

    VerticalPanel rateDescriptionPanel;
    VerticalPanel ratingPanel;
    Label rateThisProductLabel, customerNumberLabel, starRatingLabel, ratingDescriptionLabel;
    Button rateProductButton;
    TextBox customerNumberTextBox;
    TextArea ratingTextArea;
    boolean firstClick;

	private int currentRating = 0;

    public RatingView(DetailedProductInfo detailedProductInfo, final MainServiceAsync mainService) {
		this.mainService = mainService;

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
		this.rateProductButton.setEnabled(false);
        this.rateProductButton.setStyleName("rateProductButton");

        final HorizontalPanel starsPanel = new HorizontalPanel();
		final Image[] ratingImgs = new Image[5];
		ratingImgs[0] = new Image("img/unrated.png");
		ratingImgs[0].setWidth(RATING_IMG_WIDTH);
		ratingImgs[0].addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ratingImgs[0].setUrl("img/rated.png");
				for(int i = 1; i < 5; i++) {
					ratingImgs[i].setUrl("img/unrated.png");
				}
				currentRating = 1;
				rateProductButton.setEnabled(true);
			}
		});
		starsPanel.add(ratingImgs[0]);

		ratingImgs[1] = new Image("img/unrated.png");
		ratingImgs[1].setWidth(RATING_IMG_WIDTH);
		ratingImgs[1].addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for(int i = 0; i < 2; i++) {
					ratingImgs[i].setUrl("img/rated.png");
				}
				for(int i = 2; i < 5; i++) {
					ratingImgs[i].setUrl("img/unrated.png");
				}
				currentRating = 2;
				rateProductButton.setEnabled(true);
			}
		});
		starsPanel.add(ratingImgs[1]);

		ratingImgs[2] = new Image("img/unrated.png");
		ratingImgs[2].setWidth(RATING_IMG_WIDTH);
		ratingImgs[2].addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for(int i = 0; i < 3; i++) {
					ratingImgs[i].setUrl("img/rated.png");
				}
				for(int i = 3; i < 5; i++) {
					ratingImgs[i].setUrl("img/unrated.png");
				}
				currentRating = 3;
				rateProductButton.setEnabled(true);
			}
		});
		starsPanel.add(ratingImgs[2]);

		ratingImgs[3] = new Image("img/unrated.png");
		ratingImgs[3].setWidth(RATING_IMG_WIDTH);
		ratingImgs[3].addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for(int i = 0; i < 4; i++) {
					ratingImgs[i].setUrl("img/rated.png");
				}
				for(int i = 4; i < 5; i++) {
					ratingImgs[i].setUrl("img/unrated.png");
				}
				currentRating = 4;
				rateProductButton.setEnabled(true);
			}
		});
		starsPanel.add(ratingImgs[3]);

		ratingImgs[4] = new Image("img/unrated.png");
		ratingImgs[4].setWidth(RATING_IMG_WIDTH);
		ratingImgs[4].addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for(int i = 0; i < 5; i++) {
					ratingImgs[i].setUrl("img/rated.png");
				}
				currentRating = 5;
				rateProductButton.setEnabled(true);
			}
		});
		starsPanel.add(ratingImgs[4]);

        this.ratingPanel.add(this.customerNumberTextBox);
        this.ratingPanel.add(starsPanel);
        this.ratingPanel.add(this.ratingTextArea);
        this.ratingPanel.add(this.rateProductButton);


        this.add(this.rateDescriptionPanel);
        this.add(this.ratingPanel);

		this.rateProductButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			}
		});
    }


}
