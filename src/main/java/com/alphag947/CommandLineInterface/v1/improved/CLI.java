package com.alphag947.CommandLineInterface.v1.improved;

import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.controller.uiController.MainframeController;

/**
 * This is the new CLI for the Entertainment Tracker. This runs in the app itself
 * instead of running it in the terminal where it will be interrupted by logs.
 * <p>
 * to enter CLI, enter @cli enter=true in the search bar in OldUI
 *
 * @see MainframeController
 * @ui OldUI.Main.MainFrameUI
 */
public class CLI {
    public CLI() {
    }

    // TODO: create commands, like an api to get the information

    public Entertainment get(int entertainmentId) {
        return null;
    }

    public Entertainment get(String phrase) {
        return null;
    }
}
