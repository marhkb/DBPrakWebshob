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

import com.google.gwt.user.client.ui.*;


/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class ProductSearchBar extends HorizontalPanel {

    private boolean searchStatus;
	private MultiWordSuggestOracle oracle;
    private SuggestBox suggestBox;
    private ListBox categoryBox;
    private Button searchButton;
    private String searchString;
    private Label searchCategoryLabel;

    public ProductSearchBar() {
        this.searchStatus = false;
        this.searchCategoryLabel = new Label("Suchkategorie: ");
        this.categoryBox = new ListBox();
        this.categoryBox.setWidth("150px");
        this.suggestBox = new SuggestBox(this.oracle = new MultiWordSuggestOracle());
        this.suggestBox.setWidth("200px");
        this.searchStatus = false;
        this.searchString = "";
        this.searchButton = new Button("Suchen");
        this.searchButton.setEnabled(false);
        this.add(this.searchCategoryLabel);
        this.add(this.categoryBox);
        this.add(this.suggestBox);
        this.add(this.searchButton);
    }

    public ListBox getCategoryBox() {
        return categoryBox;
    }

    public SuggestBox getSuggestBox() {
        return suggestBox;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public boolean isSearchStatus() {
        return searchStatus;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchStatus(boolean searchStatus) {
        this.searchStatus = searchStatus;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

	public MultiWordSuggestOracle getOracle() {
		return oracle;
	}

    public void setOracle(MultiWordSuggestOracle oracle) {
        this.oracle = oracle;
    }
}
