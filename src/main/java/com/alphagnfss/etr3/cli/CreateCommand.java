package com.alphagnfss.etr3.cli;

import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import picocli.CommandLine.Command;

import java.util.Map;
import java.util.concurrent.Callable;

@Command(
        name = "create",
        description = "create a new media",
        mixinStandardHelpOptions = true
)
public class CreateCommand implements Callable<Integer> {

    private EntertainmentStatus status;
    private EntertainmentType type;
    private String title;
    private Map<String, String> args;

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
