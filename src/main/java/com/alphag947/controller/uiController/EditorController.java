package com.alphag947.controller.uiController;

import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import com.alphag947.controller.ParentController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorController extends ParentController {

    // creator entertainment type ribbon
    @FXML
    private Label creator_label_entertainment_type;
    @FXML
    private Button creator_button_entertainment_type_movie;
    @FXML
    private Button creator_button_entertainment_type_tv_show;
    @FXML
    private Button creator_button_entertainment_type_anime;
    @FXML
    private Button creator_button_entertainment_type_episode;

    // franchise
    @FXML
    private HBox creator_container_franchise;
    @FXML
    private Label creator_info_label_franchise;
    @FXML
    private TextField creator_textfield_franchise;
    @FXML
    private CheckBox creator_check_box_include_title;

    // title
    @FXML
    private HBox creator_container_title; // also ep name
    @FXML
    private Label creator_info_label_title; // ep name
    @FXML
    private Label creator_label_title; // ep name
    @FXML
    private TextField creator_textfield_title;
    @FXML
    private CheckBox creator_check_box_title_none;

    // stage name
    @FXML
    private HBox creator_container_stage_name;
    @FXML
    private Label creator_label_entertainment_type_name;
    @FXML
    private Label creator_label_stage_name;

    // primary status
    @FXML
    private HBox creator_container_primary_status;
    @FXML
    private Label creator_info_label_primary_status;
    //    @FXML private HBox creator_check_box_group_primary_status;
    @FXML
    private CheckBox creator_check_box_primary_status_completed;
    @FXML
    private CheckBox creator_check_box_primary_status_released;
    @FXML
    private CheckBox creator_check_box_primary_status_ongoing;
    @FXML
    private CheckBox creator_check_box_primary_status_upcoming;

    // additional status
    @FXML
    private HBox creator_container_additional_status;
    @FXML
    private Label creator_info_label_additional_status;
    @FXML
    private CheckBox creator_check_box_additional_status_special;
    @FXML
    private CheckBox creator_check_box_additional_status_pilot;

    // extra status
    @FXML
    private HBox creator_container_extra_status;
    @FXML
    private Label creator_info_label_extra_status;
    @FXML
    private CheckBox creator_check_box_extra_status_favorite;
    @FXML
    private CheckBox creator_check_box_extra_status_reviewed;

    // duration
    @FXML
    private HBox creator_container_duration;
    @FXML
    private Label creator_info_label_duration;
    @FXML
    private TextField creator_textfield_duration;
    //    @FXML private HBox creator_check_box_group_duration;
    @FXML
    private CheckBox creator_check_box_duration_12_min;
    @FXML
    private CheckBox creator_check_box_duration_24_min;

    // date
    @FXML
    private HBox creator_container_date;
    @FXML
    private Label creator_info_label_date;
    @FXML
    private DatePicker creator_date_picker;
    @FXML
    private Label creator_label_date;

    // listview container
    @FXML
    private VBox creator_hbox_tags_production_companies;

    // tags
    @FXML
    private HBox creator_container_tags;
    @FXML
    private ListView<Label> creator_list_view_tags;
    @FXML
    private Button creator_button_tags;

    // production companies
    @FXML
    private HBox creator_container_production_companies;
    @FXML
    private ListView<Label> creator_list_view_production_companies;
    @FXML
    private Button creator_button_production_companies;

    // season num + id container
    @FXML
    private VBox creator_hbox_season_num_and_id;

    // season num
    @FXML
    private HBox creator_container_season_num;
    @FXML
    private Label creator_info_label_season_num;
    @FXML
    private TextField creator_textfield_season_num;

    // season id
    @FXML
    private HBox creator_container_season_id;
    @FXML
    private Label creator_info_label_season_id;
    @FXML
    private Label creator_label_season_id;

    // total episodes
    @FXML
    private HBox creator_container_total_episodes;
    @FXML
    private Label creator_info_label_total_episodes;
    @FXML
    private TextField creator_textfield_total_episodes;
    @FXML
    private CheckBox creator_check_box_total_episodes_unknown;

    // ep num
    @FXML
    private HBox creator_container_episode_num;
    @FXML
    private Label creator_info_label_episode_num;
    @FXML
    private TextField creator_textfield_episode_num;

    // creator data save ribbon
    @FXML
    private Button creator_button_reset;
    @FXML
    private Button creator_button_save;
    @FXML
    private Button creator_button_create;
    @FXML
    private Button creator_button_exit;

    public void initialize() {
    }

    public void setEditor(EntertainmentType entertainmentType) {

        // TODO: Set creator_label_entertainment_type_name and creator_label_stage_name
        switch (entertainmentType) {
            case MOVIE:
                resetUI();
                creator_hbox_season_num_and_id.setManaged(false);
                creator_container_total_episodes.setManaged(false);
                creator_container_episode_num.setManaged(false);
                break;
            case TVSHOW:
                resetUI();
                showUI();
                break;
            case ANIME:
                resetUI();
                showUI();
                break;
            case EPISODE:
                resetUI();
                // TODO: set creator_label_title
                // TODO: use creator_label_stage_name to show the show stage name with show id #
                creator_container_franchise.setManaged(false);
                creator_container_production_companies.setManaged(false);
                creator_container_season_num.setManaged(false);
                creator_container_total_episodes.setManaged(false);
                break;
            default:
        }
    }

    private void showUI() {
        creator_container_duration.setManaged(false);
        creator_container_production_companies.setManaged(false);
        creator_container_episode_num.setManaged(false);
    }

    // resets UI
    private void resetUI() {}

    // does resetUI and resets all vals
    private void fullReset() {}


}
