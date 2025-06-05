package com.alphag947.backend.logging;

public interface LoggerInterface {

    void log(String string); // logging

    void err(Exception e); // error

    void dbg(String string); // debugging

    void hlt(String string); // highlighting

    void ln(); // new line

}
