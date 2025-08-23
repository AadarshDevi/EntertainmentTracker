package com.alphag947.SceneFactory.exception;

public class SceneIdNotFoundException extends Exception {
    public SceneIdNotFoundException(int id) {
        super("Scene with id\"" + id + "\" is Empty");
    }
}
