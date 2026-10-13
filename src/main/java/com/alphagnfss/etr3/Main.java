package com.alphagnfss.etr3;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Show;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.database.nosql.MongoDB;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) {
        LOGGER.info("Starting Main");

        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1?connectTimeoutMS=5000&serverSelectionTimeoutMS=5000";
        MongoDB database = new MongoDB(uri);
        Response response = database.connect();

        Show show = Show.builder().title("Avatar: The Last Airbender").type(EntertainmentType.TV_SHOW).build();
        ObjectMapper mapper = JsonMapper.builder().build();

        try {
            String string = mapper.writeValueAsString(show);
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }

        database.disconnect();
        System.exit(0);
    }
}