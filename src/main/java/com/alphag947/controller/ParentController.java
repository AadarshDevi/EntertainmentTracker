package com.alphag947.controller;

import com.alphag947.api.AppApi;
import com.alphag947.api.AppApiFactory;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class ParentController implements ParentControllerInterface {

    Entertainment entertainment;

    public void setEntertainment(Entertainment entertainment) {
        this.entertainment = entertainment;
    }

    public Entertainment getEntertainment() {
        return entertainment;
    }

    protected AppApi api;
    protected ConsoleLogger cl;

    public AppApi getAppApi() {
        return api;
    }

    public ParentController() {
        cl = LoggerFactory.getConsoleLogger();
        connectAPI();
    }

    @Override
    public void connectAPI() {
        api = AppApiFactory.getApi();
        // cl.log(this, "Connected to AppApi");
    }

    @Override
    public void disconnectAPI() {
        api = null;
        // cl.log("Disconnected from AppApi");
    }

    @SuppressWarnings("unchecked")
    protected void settingsComboBox(ComboBox<Label>... cbs) {
        for (ComboBox<Label> comboBox : cbs) {

            comboBox.setCellFactory(new Callback<ListView<Label>, ListCell<Label>>() {
                @Override
                public ListCell<Label> call(ListView<Label> param) {
                    return new ListCell<Label>() {
                        @Override
                        protected void updateItem(Label item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item == null || empty) {
                                setText(null);
                            } else {
                                setText(item.getText()); // Display the text of the Label
                            }
                        }
                    };
                }
            });

            // Set a custom button cell to display the selected item's text
            comboBox.setButtonCell(new ListCell<Label>() {
                @Override
                protected void updateItem(Label item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getText()); // Display the text of the selected Label
                    }
                }
            });
        }
    }

    protected void cellFactoryListView(@SuppressWarnings("unchecked") ListView<BorderPane>... listviews) {

        for (ListView<BorderPane> listView : listviews) {
            listView.setCellFactory(new Callback<ListView<BorderPane>, ListCell<BorderPane>>() {

                @Override
                public ListCell<BorderPane> call(ListView<BorderPane> param) {
                    return new ListCell<BorderPane>() {
                        @Override
                        protected void updateItem(BorderPane item, boolean isEmpty) {
                            super.updateItem(item, isEmpty);

                            if (isEmpty || item == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                item.prefWidthProperty().bind(getListView().widthProperty().subtract(20));
                                updateBorderPaneContent(item);
                                setGraphic(item);
                            }

                        }

                        // FIX ME: set the text by getting the information
                        private void updateBorderPaneContent(BorderPane pane) {
                            // Update labels
                            Label moduleIdLabel = (Label) pane.lookup("#module_id");
                            if (moduleIdLabel != null) {
                                moduleIdLabel.setText("ID. "); // FIX ME:
                            }

                            Label moduleNameLabel = (Label) pane.lookup("#module_name");
                            if (moduleNameLabel != null) {
                                moduleNameLabel.setText("Franchise + Title");// FIX ME:
                            }

                            Label moduleInfoLeftLabel = (Label) pane.lookup("#module_info_left");
                            if (moduleInfoLeftLabel != null) {
                                moduleInfoLeftLabel.setText("Runtime");// FIX ME:
                            }

                            Label moduleInfoRightLabel = (Label) pane.lookup("#module_info_right");
                            if (moduleInfoRightLabel != null) {
                                moduleInfoRightLabel.setText("Date");// FI XME:
                            }

                            Label moduleseasoninfo = (Label) pane.lookup("#module_season_number");
                            if (moduleseasoninfo != null) {
                                moduleseasoninfo.setText("Season ? Episodes: ?");// FIX ME:
                            }

                            // Update button with PNG image
                            Button pngButton = (Button) pane.lookup("#pngButton"); // Use the actual ID of your button
                            if (pngButton != null) {
                                Image buttonImage = new Image("null");// FIX ME:
                                ImageView imageView = new ImageView(buttonImage);
                                imageView.setFitHeight(20); // Adjust size as needed
                                imageView.setFitWidth(20);
                                pngButton.setGraphic(imageView);
                            }

                        }
                    };
                }
            });
        }
    }
}
