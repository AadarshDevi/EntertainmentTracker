package com.alphag947.v2.cli;

import com.alphag947.api.Api;
import com.alphag947.api.ApiFactory;
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

    private String commands =
            """
                    
                        @cli
                        @exitapp
                    
                        enter=true
                        exit=true
                    
                        cliview=true
                        cliview=false
                    
                        newui=true
                        newui=false
                    
                    """;

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

                case "@cli":
                    LOGGER.info("CLI Command");
                    break;
                case "@exitapp":
                    api.closeApp();
                    break;

                case "cliview=true":
                    setInCLI(true);
                    api.setCliView();
                    break;
                case "cliview=false":
                    setInCLI(false);
                    api.resetMainUI();
                    break;

                case "newui=true":
                    api.test();
                    break;
                case "newui=false":
                    api.resetMainUI();
                    break;


                case "enter=true":
                    setInCLI(true);
                    break;
                case "exit=true":
                    setInCLI(false);
                    break;

                default:
                    LOGGER.error("Command \"" + cmds[i] + "\" not found.");
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Command Error");
                    alert.setHeaderText(null);  // No header
                    alert.setContentText("Command \"" + cmds[i] + "\" not found." + "\n" + commands);
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
