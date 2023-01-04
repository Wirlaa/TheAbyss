package agh.oop.project1.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        LaunchPresenter launchPresenter = new LaunchPresenter(new LaunchView());
        OptionsPresenter optionsPresenter = new OptionsPresenter(new OptionsView(true), launchPresenter);
        launchPresenter.setOptionsPresenter(optionsPresenter);
        LoadOptionsPresenter loadOptionsPresenter = new LoadOptionsPresenter(new LoadOptionsView(),launchPresenter);
        launchPresenter.setLoadOptionsPresenter(loadOptionsPresenter);
        primaryStage.setScene(new Scene(launchPresenter.getView()));
        primaryStage.setTitle("TheAbyss");
        primaryStage.show();
    }
}
