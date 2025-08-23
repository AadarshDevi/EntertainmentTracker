package com.alphag947.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class TestController extends ParentController {
    @FXML
    public ListView<BorderPane> list_view;
    @FXML
    public MenuBar menubar;
    public BorderPane home;
}
