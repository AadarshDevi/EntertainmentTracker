package com.alphag947.backend.logging;

public class Logger implements LoggerInterface {

    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_GREEN = "\u001B[32m";
    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String ANSI_YELLOW = "\u001b[33m";

    @Override
    public void log(String string) {
        System.out.println("[log] > " + string);
    }

    @Override
    public void err(Exception e) {
        System.err.println(ANSI_RED + "\n[ERROR] > " + e.getMessage() + "\n\t" + e.getCause() + ANSI_RESET);
    }

    @Override
    public void dbg(String string) {
        System.out.println(ANSI_GREEN + "[debug] >>> " + string + ANSI_RESET);
    }

    @Override
    public void hlt(String string) {
        System.out.println(ANSI_YELLOW + "[log] > " + string + ANSI_RESET);
    }

    @Override
    public void ln() {
        System.out.println();
    }
}
