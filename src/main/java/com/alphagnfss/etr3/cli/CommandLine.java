package com.alphagnfss.etr3.cli;

import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class CommandLine {

    public void run() {

    }

    public String[] parseInput(String input) {
        try (Terminal terminal = TerminalBuilder.builder().system(true).build()) {

            Completer completer = new StringsCompleter(
                    "create", "update", "delete", "list",
                    "--help",
                    "--status", "--type", "--title",
                    "--date",
                    "exit"
            );

            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .parser(new DefaultParser())
                    .completer(completer)
                    .history(new DefaultHistory())
                    .build();

            while (true) {
                String line;
                try {
                    System.out.print(" >>> Entertainment Tracker ~ ");
                    line = reader.readLine();

                    if (line.trim().isBlank()) continue;

                    System.out.println(" >>> Output ~ " + line);
                } catch (UserInterruptException | EndOfFileException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
