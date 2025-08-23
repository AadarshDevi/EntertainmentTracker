package com.alphag947.v2.cli;

import javafx.scene.control.Alert;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class CommandLineInterface {

    private final Logger LOGGER = Logger.getLogger(CommandLineInterface.class);
    private boolean inCLI;
    private ArrayList<String> commandHistory = new ArrayList<>();

    public CommandLineInterface() {
        inCLI = false;
    }

    public void sendCommand(String text) {
        LOGGER.info("\"" + text + "\"");
        String[] cmds = text.split(" ");
        for (int i = 0; i < cmds.length; i++) {
            LOGGER.info("Command " + (i + 1) + ": " + cmds[i]);
            switch (cmds[i]) {
                case "get=entertainment": break; // next commands help to get entertainment based on given info
                case "enterview=true": break; // go to cli view
                case "enterview=false": break; // exit cli view and go to homescreen
                default:
                    LOGGER.error("Command \"" + cmds[i] + "\" not found.");
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Command Error");
                    alert.setHeaderText(null);  // No header
                    alert.setContentText("Command \"" + cmds[i] + "\" not found.");
                    alert.showAndWait();

            }
        }
    }

    public boolean isInCLI() {
        return inCLI;
    }

    public void setInCLI(boolean bool) {
        inCLI = bool;
    }
}
