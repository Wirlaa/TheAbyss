package agh.oop.project1.gui;

import agh.oop.project1.IEngine;
import javafx.scene.control.TextField;

public class MainPresenter {
    private final IEngine engine;
    private Thread engineThread;
    private final MainView view;
    private MapPresenter mapPresenter;
    private OptionsPresenter optionsPresenter;
    private StartPresenter startPresenter;
    private boolean options = false;
    public MainPresenter (MainView view, IEngine engine) {
        this.view = view;
        this.view.setPresenter(this);
        this.engine = engine;
        //engineThread = new Thread(engine);
    }
    public MainView getView(){
        return view;
    }
    public StartView getStartView(){
        return startPresenter.getView();
    }
    public void setMapPresenter(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;
    }
    public void setOptionsPresenter(OptionsPresenter optionsPresenter) { this.optionsPresenter = optionsPresenter; }
    public void setStartPresenter(StartPresenter startPresenter) { this.startPresenter = startPresenter; }
    public void updateView() {
        view.setContentCenter(mapPresenter.getView());
    }
    public void start(TextField text) {
        //engine.setDirections(OptionsParser.parse(text.getText().split(" ")));
        engineThread.stop(); //cos innego niz stop?
        //engineThread = new Thread(engine);
        engineThread.start();
    }
    public void stop() {
        engineThread.stop();
    }
    public void switchAnimal() {
    //    engine.switchActiveAnimal();
    }
    public void switchOptions() {
        if (!options) {
            view.setContentRight(optionsPresenter.getView());
            options = true;
        } else {
            view.setContentRight(null);
            options = false;
        }
    }
    public OptionsView showOptions() {
        return optionsPresenter.getView();
    }

    // jakis inny sposob wywalania bledow?
    // wywalenie cssa do osobnej klasy

    // binding?
}
