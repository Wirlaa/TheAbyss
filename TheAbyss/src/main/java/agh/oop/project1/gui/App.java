package agh.oop.project1.gui;

import agh.oop.project1.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private AWorldMap map;
    private IEngine engine;
    @Override
    public void init() {
        //List<String> rawParameters = getParameters().getRaw();
        //MoveDirection[] directions = OptionsParser.parse(rawParameters.toArray(String[]::new));
        List<Vector2d> animalStartingPositions = new ArrayList<>();
        animalStartingPositions.add(new Vector2d(-1, 2));
        animalStartingPositions.add(new Vector2d(3, 4));
        map = new GrassField(10);
        engine = new SimulationEngine(map,animalStartingPositions);
    } //r l f l f f f l b f b f b f r r f b f b f b f f
    @Override //f f f f l f f f r f f f r r r r b b b b r f r f r f
    public void start(Stage stage) {
        MainView mainView = new MainView();
        MainPresenter mainPresenter = new MainPresenter(mainView,engine);
        MapPresenter mapPresenter = new MapPresenter(new MapView(map), mainPresenter);
        engine.addObserver(mapPresenter);
        mainPresenter.setMapPresenter(mapPresenter);
        OptionsPresenter optionsPresenter = new OptionsPresenter(new OptionsView(), mainPresenter);
        mainPresenter.setOptionsPresenter(optionsPresenter);
        stage.setScene(new Scene(mainPresenter.getView()));
        stage.setTitle("World");
        stage.show();
    }
}
