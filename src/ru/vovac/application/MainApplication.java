package ru.vovac.application;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import ru.vovac.game.scene.GameFieldBuilder;

import java.io.File;

public class MainApplication extends SimpleApplication {
    private static MainApplication app;

    public static void main(String[] args) {
        app = new MainApplication();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("CardsGame");
        settings.setResolution(1600, 900);
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Register assets locator
        assetManager.registerLocator(System.getProperty("user.dir") + File.separator + "assets" + File.separator, FileLocator.class);
        // Create a GameFieldBuilder
        GameFieldBuilder gameFieldBuilder = new GameFieldBuilder(app, 12, 8, 1);
        gameFieldBuilder.buildTest();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }
}
