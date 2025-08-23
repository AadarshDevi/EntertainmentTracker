package com.alphag947.v2.scene;

import javafx.scene.Scene;

public class SceneFactory {
    public static SceneManager sceneManager;

    public static SceneManager getSceneManager(Scene baseScene) {
        if (sceneManager == null) sceneManager = new SceneManager();
        return sceneManager;
    }
}
