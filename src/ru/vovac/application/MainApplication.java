package ru.vovac.application;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.system.AppSettings;
import ru.vovac.game.controllers.CameraController;
import ru.vovac.game.scene.GameField;

import java.io.File;

public class MainApplication extends SimpleApplication {
    private static MainApplication app;
    public static GameField gameFieldBuilder;
    CameraController cameraController;

    public static void main(String[] args) {
        app = new MainApplication();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("CardsGame");
        settings.setResolution(1200, 900);
        settings.setFullscreen(true);
        settings.setResizable(true);
        app.setSettings(settings);
        app.setShowSettings(true);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Register assets locator
        assetManager.registerLocator(System.getProperty("user.dir") + File.separator + "assets" + File.separator, FileLocator.class);
        // Creates a GameFieldBuilder
        gameFieldBuilder = new GameField(app, 12, 8, 1);
        gameFieldBuilder.init();
        // Creates a CameraController
        cameraController = new CameraController(app);
        cameraController.init();
    }

    @Override
    public void simpleUpdate(float tpf) {
        cameraController.updateListener();
        //TODO: add update code
    }
}
