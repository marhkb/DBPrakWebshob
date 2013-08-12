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

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import de.behrfriedapp.webshop.client.MainServiceAsync;
import de.behrfriedapp.webshop.client.Messages;
import de.behrfriedapp.webshop.shared.data.DetailedProductInfo;
import de.behrfriedapp.webshop.shared.data.ShortProductInfo;
import de.behrfriedapp.webshop.shared.data.WProductGroupInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rofriedr
 * Date: 10.08.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class WebshopContainer extends VerticalPanel {

    private VerticalPanel webshobContainer;
    private Label webshopTitle;
    private ProductSearchBar productSearchBar;
    private SearchedProductView searchedProductView;
    private final MainServiceAsync mainService;
	private final Messages messages;

    @Inject
    public WebshopContainer(final MainServiceAsync mainService, final Messages messages) {
		this.mainService = mainService;
		this.messages = messages;

        this.setWidth("70%");
        this.webshopTitle = new Label(this.messages.projectName());
        this.productSearchBar = new ProductSearchBar(messages);
        this.searchedProductView = new SearchedProductView();
        this.setHorizontalAlignment(ALIGN_CENTER);
        this.add(this.webshopTitle);
        this.add(this.productSearchBar);
        this.add(this.searchedProductView);

        this.addClickHandler();
        this.addSearchStringChangedHandler();
        this.addCategoryBoxChangedHandler();
        this.bindCategoryBox();
        this.bindSuggestBox();
    }

    private void addClickHandler() {
        this.productSearchBar.getSearchButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (WebshopContainer.this.productSearchBar.getCategoryBox().getItemText(WebshopContainer.this.productSearchBar.getCategoryBox().getSelectedIndex()).equals("Alles")) {
                    WebshopContainer.this.mainService.getAllProducts(WebshopContainer.this.productSearchBar.getSuggestBox().getValue(), new AsyncCallback<List<ShortProductInfo>>() {
                        public void onFailure(Throwable caught) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        public void onSuccess(List<ShortProductInfo> result) {
                            WebshopContainer.this.searchedProductView.clear();
                            if (result.isEmpty()) {
                                Label noEntry = new Label(messages.noEntryFound());
                                noEntry.setHorizontalAlignment(ALIGN_CENTER);
                                WebshopContainer.this.searchedProductView.add(noEntry);
                            } else {
                                for (ShortProductInfo productInfo : result) {
                                    Window.alert("trololol");
                                    final ShortProductInfo pInfo = productInfo;
                                    ProductRow tmpRow = new ProductRow(pInfo.getName(), pInfo.getName(), pInfo.getPrice());
                                    tmpRow.getProductLink().addClickHandler(new ClickHandler() {
                                        @Override
                                        public void onClick(ClickEvent event) {
                                            WebshopContainer.this.mainService.getDetailedProductInfo(pInfo, new AsyncCallback<DetailedProductInfo>() {
                                                @Override
                                                public void onFailure(Throwable caught) {
                                                    //To change body of implemented methods use File | Settings | File Templates.
                                                }

                                                @Override
                                                public void onSuccess(DetailedProductInfo result) {
                                                    WebshopContainer.this.searchedProductView.clear();
                                                    ProductDetailView detailView = new ProductDetailView(result, messages);
                                                    WebshopContainer.this.searchedProductView.add(detailView);
                                                }
                                            });
                                        }
                                    });
                                    WebshopContainer.this.searchedProductView.add(tmpRow);
                                }
                            }
                        }
                    });
                } else {
                    WebshopContainer.this.mainService.getAllGroupProducts(WebshopContainer.this.productSearchBar.getCategoryBox().getValue(WebshopContainer.this.productSearchBar.getCategoryBox().getSelectedIndex()), WebshopContainer.this.productSearchBar.getSuggestBox().getValue(), new AsyncCallback<List<ShortProductInfo>>() {
                        public void onFailure(Throwable caught) {
                            //To change body of implemented methods use File | Settings | File Templates.
                        }

                        public void onSuccess(List<ShortProductInfo> result) {
                            WebshopContainer.this.searchedProductView.clear();
                            if (result.isEmpty()) {
                                Label noEntry = new Label(messages.noEntryFound());
                                noEntry.setHorizontalAlignment(ALIGN_CENTER);
                                WebshopContainer.this.searchedProductView.add(noEntry);
                            } else {
                                for (ShortProductInfo productInfo : result) {
                                    final ShortProductInfo pInfo = productInfo;
                                    ProductRow tmpRow = new ProductRow(pInfo.getName(), pInfo.getName(), pInfo.getPrice());
                                    tmpRow.getProductLink().addClickHandler(new ClickHandler() {
                                        @Override
                                        public void onClick(ClickEvent event) {
                                            WebshopContainer.this.mainService.getDetailedProductInfo(pInfo, new AsyncCallback<DetailedProductInfo>() {
                                                @Override
                                                public void onFailure(Throwable caught) {
                                                    //To change body of implemented methods use File | Settings | File Templates.
                                                }

                                                @Override
                                                public void onSuccess(DetailedProductInfo result) {
                                                    WebshopContainer.this.searchedProductView.clear();
                                                    ProductDetailView detailView = new ProductDetailView(result, messages);
                                                    WebshopContainer.this.searchedProductView.add(detailView);
                                                }
                                            });
                                        }
                                    });
                                    WebshopContainer.this.searchedProductView.add(tmpRow);
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void addSearchStringChangedHandler() {
        this.productSearchBar.getSuggestBox().addKeyUpHandler(
                new HandlesAllKeyEvents() {
                    @Override
                    public void onKeyDown(KeyDownEvent event) {
                    }

                    @Override
                    public void onKeyPress(KeyPressEvent event) {
                    }

                    @Override
                    public void onKeyUp(KeyUpEvent event) {
                        if (WebshopContainer.this.productSearchBar.getSuggestBox().getValue().equals("")) {
                            WebshopContainer.this.productSearchBar.getSearchButton().setEnabled(false);
                        } else {
                            WebshopContainer.this.productSearchBar.getSearchButton().setEnabled(true);
                        }
                    }
                }
        );
    }

    private void addCategoryBoxChangedHandler() {
        this.productSearchBar.getCategoryBox().addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
                bindSuggestBox();
            }
        });
    }


    private void bindCategoryBox() {
        this.productSearchBar.getCategoryBox().addItem(this.messages.allCategoriesEntry());
        WebshopContainer.this.mainService.getAllProductGroups(new AsyncCallback<List<WProductGroupInfo>>() {
            public void onFailure(Throwable caught) {
                Window.alert(caught.toString());
            }

            public void onSuccess(List<WProductGroupInfo> result) {
                for (WProductGroupInfo str : result) {
                    for (int i = 0; i < productSearchBar.getCategoryBox().getItemCount(); i++) {
                        if (productSearchBar.getCategoryBox().getItemText(i).equals(str.getGroupName())) {
                            break;
                        } else if (productSearchBar.getCategoryBox().getItemCount() - 1 == i) {
                            productSearchBar.getCategoryBox().addItem(str.getGroupName());
                        }

                    }
                }
            }
        });
    }

    private void bindSuggestBox() {
        String category = WebshopContainer.this.productSearchBar.getCategoryBox().getValue(WebshopContainer.this.productSearchBar.getCategoryBox().getSelectedIndex());
        if (!category.equals(this.messages.allCategoriesEntry())) {
            WebshopContainer.this.mainService.getAllGroupProducts(category, new AsyncCallback<List<ShortProductInfo>>() {
                public void onFailure(Throwable caught) {
                    Window.alert(caught.toString());
                }

                public void onSuccess(List<ShortProductInfo> result) {
                    final MultiWordSuggestOracle oracle = productSearchBar.getOracle();
                    oracle.clear();
                    for (ShortProductInfo info : result) {
                        oracle.add(info.getName());
                    }
                }
            });
        } else {
            WebshopContainer.this.mainService.getAllProducts(new AsyncCallback<List<ShortProductInfo>>() {
                public void onFailure(Throwable caught) {
                    Window.alert(caught.toString());
                }

                public void onSuccess(List<ShortProductInfo> result) {
                    MultiWordSuggestOracle oracle = productSearchBar.getOracle();
                    oracle.clear();
                    for (ShortProductInfo info : result) {
                        oracle.add(info.getName());
                    }
                }
            });
        }
    }
}
