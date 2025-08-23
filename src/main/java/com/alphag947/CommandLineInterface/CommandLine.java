package com.alphag947.CommandLineInterface;

import java.util.ArrayList;
import java.util.Scanner;

import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.logging.LoggerFactory;

public class CommandLine implements Runnable {

    public DataAPI dAPI;
    boolean running = true;

    public CommandLine() {
        dAPI = new DataAPI();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.print(" >>> ");
            String cmd = scanner.nextLine();

            String[] cmds = cmd.split(" ");
            String base_cmd = cmds[0];

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
                case "status":

                    String second_cmd = cmds[1];
                    String[] values = cmds[2].split("-");

                    int parent_value = Integer.parseInt(values[0]); // movieId > 1, showId > 1

                    int child_value = 0;

                    switch (second_cmd) {

                        // -s -gp <id>
                        // -s -gp <id>-<episode_id>
                        case "-gp":
                        case "getprimary":
                            if (values.length > 1)
                                child_value = Integer.parseInt(values[1]); // episode > 1-1 :: showid-episodeNum
                            EntertainmentStatus primary_status = dAPI.getEntertainmentPrimaryStatus(parent_value,
                                    child_value);
                            System.out.println(" >>> Primary Status: " + primary_status);
                            break;
                        // case "-gs":
                        // case "getsecondary":
                        // break;
                        case "-sp":
                            if (values.length > 1) {
                                child_value = Integer.parseInt(values[1]); // episode > 1-1 :: showid-episodeNum
                            }
                            int new_primary_status = Integer.parseInt(cmds[3]);
                            switch (new_primary_status) {
                                case 1:
                                    dAPI.setEntertainmentPrimaryStatus(parent_value, child_value,
                                            EntertainmentStatus.COMPLETED);
                                    break;
                                case 2:
                                    dAPI.setEntertainmentPrimaryStatus(parent_value, child_value,
                                            EntertainmentStatus.RELEASED);
                                    break;
                                case 3:
                                    dAPI.setEntertainmentPrimaryStatus(parent_value, child_value,
                                            EntertainmentStatus.UPCOMING);
                                    break;
                                case 9:
                                    dAPI.setEntertainmentPrimaryStatus(parent_value, child_value,
                                            EntertainmentStatus.ONGOING);
                                    break;
                                default:
                                    LoggerFactory.getConsoleLogger().err(new Exception(String
                                            .format("Primary Status \"%d\" does not exist.", new_primary_status)));
                            }
                            break;
                        // case "-ss":
                        // break;

                        case "-gpl":
                        case "get primary list":
                            int get_primary_status = Integer.parseInt(cmds[2]);
                            ArrayList<String> list = new ArrayList<>();
                            switch (get_primary_status) {
                                case 1:
                                    list = dAPI.getEntertainmentByPrimartStatus(EntertainmentStatus.COMPLETED);
                                    break;
                                case 2:
                                    list = dAPI.getEntertainmentByPrimartStatus(EntertainmentStatus.RELEASED);
                                    break;
                                case 3:
                                    list = dAPI.getEntertainmentByPrimartStatus(EntertainmentStatus.UPCOMING);
                                    break;
                                case 9:
                                    list = dAPI.getEntertainmentByPrimartStatus(EntertainmentStatus.ONGOING);
                                    break;
                                default:
                                    LoggerFactory.getConsoleLogger().err(new Exception(String
                                            .format("Primary Status \"%d\" does not exist.", get_primary_status)));
                            }

                            // ArrayList<String> list = dAPI.getEntertainmentByPrimartStatus(val);
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
                            System.out.println("type -lcd for all commands");
                    }
                    break;

                // -id > get entertainment by id
                case "-id":
                    // int entertainmentId = Integer.parseInt(cmds[1]);
                    // Entertainment entertainment = dAPI.getEntertainmentById(entertainmentId);
                    // if (entertainment != null)
                    // System.out
                    // .println("Entertainment: " +
                    // dAPI.getEntertainmentById(entertainmentId).getStageName());
                    break;

                case "-lcd":
                    System.out.println(
                            "Commands:\n" +
                                    "> -s\n" +
                                    "  > -gp\n" +
                                    "  > -gs\n" +
                                    "  > -sp\n" +
                                    "  > -ss\n" +
                                    "  > -gpl\n" +
                                    "> -exit");
                    break;

                default:
                    System.out.printf("Command \"%s\" does not exist.\n", base_cmd);
            }
        }
        scanner.close();
        System.out.println(" >>> Ended CommandLineInterface");
    }

}
