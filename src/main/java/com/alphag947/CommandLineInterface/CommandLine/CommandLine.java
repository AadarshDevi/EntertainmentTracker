package com.alphag947.CommandLineInterface.CommandLine;

import java.util.ArrayList;
import java.util.Scanner;

import com.alphag947.CommandLineInterface.api.DataAPI;
import com.alphag947.CommandLineInterface.api.DataAPIFactory;

public class CommandLine implements Runnable {

    public DataAPI dAPI;

    public CommandLine() {
        dAPI = DataAPIFactory.getDataApi();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.print(" >>> ");
            String cmd = scanner.nextLine();

            String[] cmds = cmd.split(" ");
            String base_cmd = cmds[0];

            // String value = "";
            // if (cmds.length > 1)
            // value = cmds[1];

            switch (base_cmd) {
                case "exit":
                    System.out.println(" >>> Exiting CommandLineInterface...");
                    running = false;
                    break;

                // -s > status
                // -gp > get primary status
                // -gs > get secondary status
                // -sp > set primary status
                // -ss > set secondary status
                // -gpl > get primary status list: a list of entertainment with the given status
                case "-s":

                    String second_cmd = cmds[1];
                    String[] values = cmds[2].split("-");

                    int parent_value = Integer.parseInt(values[0]); // movieId > 1, showId > 1

                    int child_value = 0;

                    switch (second_cmd) {
                        case "-gp":
                            if (values.length > 1)
                                child_value = Integer.parseInt(values[1]); // episode > 1-1 :: showid-episodeNum
                            int primary_status = dAPI.getEntertainmentPrimaryStatus(parent_value, child_value);
                            System.out.println(" >>> Primary Status: " + primary_status);
                            break;
                        case "-gs":
                            break;
                        case "-sp":
                            if (values.length > 1) {
                                child_value = Integer.parseInt(values[1]); // episode > 1-1 :: showid-episodeNum
                            }
                            int new_primary_status = Integer.parseInt(cmds[3]);
                            dAPI.setEntertainmentPrimaryStatus(parent_value, child_value,
                                    new_primary_status);
                            break;
                        case "-ss":
                            break;

                        case "-gpl":
                            int val = Integer.parseInt(cmds[2]);
                            ArrayList<String> list = dAPI.getEntertainmentByPrimartStatus(val);
                            if (list.size() == 0) {
                                System.out.println(" >>> [Empty List]");
                                break;
                            }

                            for (int i = 0; i < list.size(); i++) {
                                System.out.print(list.get(i) + ",");
                                if (i % 20 == 0)
                                    System.out.println();
                            }
                            System.out.println();
                            break;

                        default:
                    }

                    break;
                default:
                    System.out.printf("Command \"%s\" does not exist.\n", base_cmd);
            }
        }
        scanner.close();
        System.out.println(" >>> Ended CommandLineInterface");
    }

}
