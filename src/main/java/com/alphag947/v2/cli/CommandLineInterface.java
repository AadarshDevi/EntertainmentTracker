package com.alphag947.v2.cli;

import com.alphag947.api.Api;
import com.alphag947.api.ApiFactory;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.exception.EntertainmentIdNotFoundException;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class CommandLineInterface {

    private final Logger LOGGER = Logger.getLogger(CommandLineInterface.class);
    private final Api api;
    @Setter
    @Getter
    private boolean inCLI;
    private ArrayList<String> commandHistory = new ArrayList<>();

    public CommandLineInterface() {
        inCLI = false;
        api = ApiFactory.getApi();
    }

    public void sendCommand(String text) {
        LOGGER.info("\"" + text + "\"");
        String[] cmds = text.split(" ");
        for (int i = 0; i < cmds.length; i++) {
            LOGGER.info("Command " + (i + 1) + ": " + cmds[i]);


            // TODO: create @cmd method and "cmd" methods
            switch (cmds[i]) {
//                case "get=id":
////                    printData(cmds[i].substring(3));
//                    try {
//                        Entertainment entertainment = api.getEntertainmentById(Integer.parseInt(cmds[++i]));
//                        LOGGER.info(entertainment.getStageName());
//                        api.showInCliUI(entertainment);
//                    } catch (EntertainmentIdNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//                    break; // next commands help to get entertainment based on given info
//
//
//                case "enterview=true":
//                    api.setCliView();
//                    break; // go to cli view
//                case "exitview=false":
//                    api.resetMainUI();
//                    break; // exit cli view and go to homescreen
//                case "newui=true":
//                    break; // enter NewUI view
//                case "newui=false":
//                    break; // exit NewUI view and go to homescreen

                case "@entercli": // enters cli
                    break;
                case "@exitcli": // exits cli
                    break;
                case "@noui": // enterview=true
                    break;
                case "@yesui": // exitview=true
                    break;
                case "@exitapp": // exit and closes app
                    api.closeApp();
                    break;
                case "@enternewui": // goto NewUI
                    break;
                case "@exitnewui": // goto OldUI
                    break;

                default:
                    LOGGER.error("Command \"" + cmds[i] + "\" not found.");
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Command Error");
                    alert.setHeaderText(null);  // No header
                    alert.setContentText("Command \"" + cmds[i] + "\" not found.");
                    alert.showAndWait();
            }
        }
    }

//    private void printData(String substring, int id) {
//        switch (substring) {
//            case "id" ->
//        }
//    }

}
