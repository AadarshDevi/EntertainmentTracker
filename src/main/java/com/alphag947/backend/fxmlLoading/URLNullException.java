package com.alphag947.backend.fxmlLoading;

public class URLNullException extends Exception {
    public URLNullException(String filepath) {
        super("File with Url not found: " + filepath);
    }
}
