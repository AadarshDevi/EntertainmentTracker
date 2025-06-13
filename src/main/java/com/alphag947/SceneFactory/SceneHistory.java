package com.alphag947.SceneFactory;

import java.util.ArrayList;

import javafx.scene.Scene;

public class SceneHistory {
    private ArrayList<Scene> sceneHistory = new ArrayList<>();

    public Scene getPreviousScene() throws Exception {
        if (sceneHistory.size() > 0) {
            Scene scene = sceneHistory.get(sceneHistory.size() - 2);
            sceneHistory.removeLast();
            return scene;
        }
        throw new Exception("SceneHistory is Empty");
    }

    public void add(Scene scene) {
        sceneHistory.add(scene);
    }

    public void clear() {
        sceneHistory.clear();
    }

    public Scene getCurrentScene() throws Exception {
        if (sceneHistory.size() > 0) {
            Scene scene = sceneHistory.getLast();
            return scene;
        }
        throw new Exception("SceneHistory is Empty");
    }
}
