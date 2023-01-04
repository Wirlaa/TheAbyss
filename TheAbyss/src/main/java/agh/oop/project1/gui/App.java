package agh.oop.project1.gui;

import agh.oop.project1.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private AWorldMap map;
    @Override
    public void start(Stage primaryStage) {
        MainPresenter mainPresenter = new MainPresenter(new MainView());
        OptionsPresenter optionsPresenter = new OptionsPresenter(new OptionsView(true), mainPresenter);
        mainPresenter.setOptionsPresenter(optionsPresenter);
        LaunchPresenter launchPresenter = new LaunchPresenter(new LaunchView(), mainPresenter);
        mainPresenter.setStartPresenter(launchPresenter);
        LoadOptionsPresenter loadOptionsPresenter = new LoadOptionsPresenter(new LoadOptionsView(),mainPresenter);
        mainPresenter.setLoadOptionsPresenter(loadOptionsPresenter);
        primaryStage.setScene(new Scene(mainPresenter.getStartView()));
        primaryStage.setTitle("TheAbyss");
        primaryStage.show();
    }
}
