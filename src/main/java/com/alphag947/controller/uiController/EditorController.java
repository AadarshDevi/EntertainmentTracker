package com.alphag947.controller.uiController;

import com.alphag947.controller.ParentController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EditorController extends ParentController {

    @FXML private Label label_basic;

    @FXML private HBox container_type;
    @FXML private ComboBox<Label> cb_type;
    @SuppressWarnings("unused") private Label lbl_movie;
    @SuppressWarnings("unused") private Label lbl_special;
    @SuppressWarnings("unused") private Label lbl_anime;
    @SuppressWarnings("unused") private Label lbl_tvshow;
    @SuppressWarnings("unused") private Label lbl_episode;

    @FXML private HBox container_franchise;
    @FXML private ComboBox<Label> cb_franchise;
    @FXML private CheckBox chbx_franchise; // new
    @FXML private TextField txf_franchise;

    @FXML private HBox container_title;
    @FXML private ComboBox<Label> cb_title;
    @FXML private CheckBox chbx_title_none; // none
    @FXML private CheckBox chbx_title_new; // new
    @FXML private TextField txf_title;

    @FXML private HBox container_date;
    @FXML private DatePicker dp_date;

    @FXML private HBox container_duration;
    @FXML private TextField txf_duration;
    @FXML private Button btn_duration_min_11;
    @FXML private Button btn_duration_min_22;

    @FXML private Label label_status;

    @FXML private HBox container_status_primary;
    @FXML private CheckBox prs_c; // completed
    @FXML private CheckBox prs_r; // released
    @FXML private CheckBox prs_u; // upcoming

    @FXML private HBox container_status_secondary;
    @FXML private CheckBox ses_sp; // special
    @FXML private CheckBox ses_p; // pilot

    @FXML private Label label_season;

    @FXML private HBox container_season_id;
    @FXML private TextField txf_season_id;

    @FXML private HBox container_season_num;
    @FXML private TextField txf_season_num;

    @FXML private HBox container_season_name;
    @FXML private TextField txf_season_name;
    @FXML private CheckBox chbx_season_name; // none

    @FXML private Label label_episode;

    @FXML private HBox container_episode_num;
    @FXML private TextField txf_episode_num;

    @FXML private HBox container_episode_name;
    @FXML private TextField txf_episode_name;
    @FXML private CheckBox chbx_episode_name; // none

    @SuppressWarnings("unchecked")
    public void initialize() {

        Label[] type_lbls = {
                new Label("Movie"),
                new Label("Special"),
                new Label("Anime"),
                new Label("TV Show"),
                new Label("Episode")
        };
        cb_type.getItems().addAll(type_lbls);

        settingsComboBox(cb_franchise, cb_title, cb_type);
    }

    /**
     * when new is clicked, the combo box is disabled and then the textfield is
     * enabled
     */
    public void checkBoxNewDisabler() {

    }

    /**
     * when none is clicked, the its parent is disabled
     */

}
