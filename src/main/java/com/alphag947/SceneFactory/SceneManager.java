package com.alphag947.SceneFactory;

import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManager {

    private HashMap<Integer, Scene> scenes;

    public SceneManager(Scene homeScene) {
        scenes = new HashMap<>();
        addScene(0, homeScene);
    }

    public void addScene(int id, Scene scene) {
        scenes.put(id, scene);
    }

    public Scene getScene(int id) throws Exception {
        for (Integer integer : scenes.keySet())
            if (integer == id)
                return scenes.get(integer);

        throw new Exception("Scene with id: " + id + " does not exist.");
    }

    public void createScene(int id, Parent parent) {
        addScene(id, new Scene(parent));
    }

}
