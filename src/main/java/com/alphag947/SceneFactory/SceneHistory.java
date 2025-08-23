package com.alphag947.SceneFactory;

import java.util.ArrayList;
import javafx.scene.Scene;

import com.alphag947.SceneFactory.exception.SceneHistoryEmptyException;


public class SceneHistory {
    private final ArrayList<Scene> sceneHistory;

    public SceneHistory() {
        sceneHistory = new ArrayList<>();
    }

    public Scene getPreviousScene() throws SceneHistoryEmptyException {
        if (!sceneHistory.isEmpty()) {
            Scene scene = sceneHistory.get(sceneHistory.size() - 2);
            sceneHistory.remove(sceneHistory.size() - 2);
            return scene;
        }
        throw new SceneHistoryEmptyException();
    }

    public void add(Scene scene) {
        sceneHistory.add(scene);
    }

    public void clear() {
        sceneHistory.clear();
    }

    public Scene getCurrentScene() throws SceneHistoryEmptyException {
        if (!sceneHistory.isEmpty()) {
            return sceneHistory.get(sceneHistory.size() - 1);
        }
        throw new SceneHistoryEmptyException();
    }
}
