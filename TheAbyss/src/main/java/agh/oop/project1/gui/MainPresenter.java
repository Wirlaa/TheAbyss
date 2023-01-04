package agh.oop.project1.gui;

import agh.oop.project1.IEngine;
import agh.oop.project1.IWorldMap;
import agh.oop.project1.SimulationOptions;
import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

public class MainPresenter {
    private IWorldMap map;
    private IEngine engine;
    private Thread engineThread;
    private final MainView view;
    private MapPresenter mapPresenter;
    private OptionsPresenter optionsPresenter;
    private LaunchPresenter launchPresenter;
    private LoadOptionsPresenter loadOptionsPresenter;
    private SimulationOptions options;
    private boolean showOptions = false;
    public MainPresenter (MainView view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    public IWorldMap getMap() { return map; }
    public void setMap(IWorldMap map) { this.map = map; }
    public MainView getView(){ return view; }
    public LaunchView getStartView(){ return launchPresenter.getView(); }
    public void setMapPresenter(MapPresenter mapPresenter) { this.mapPresenter = mapPresenter; }
    public void setEngine(IEngine engine) { this.engine = engine; }
    //public IEngine getEngine() { return engine; }
    public void setOptionsPresenter(OptionsPresenter optionsPresenter) { this.optionsPresenter = optionsPresenter; }
    public void setStartPresenter(LaunchPresenter launchPresenter) { this.launchPresenter = launchPresenter; }
    public void setLoadOptionsPresenter(LoadOptionsPresenter loadOptionsPresenter) { this.loadOptionsPresenter = loadOptionsPresenter; }
    //na pewno da sie ladniej
    public void setOptions(int[] intOptions, boolean corpseToxicity) {
        options = new SimulationOptions(intOptions[0], intOptions[1], corpseToxicity, intOptions[2], intOptions[3], intOptions[4],
                intOptions[5], intOptions[6], intOptions[7], intOptions[8], intOptions[9], intOptions[10], intOptions[11]);
    }
    public void setOptions(SimulationOptions options) {
        this.options = options;
    }
    public SimulationOptions getOptions() { return options; }
    public void updateView() {
        view.setContentCenter(mapPresenter.getView());
    }
    public void createThread() {
        engineThread = new Thread(engine);
        engineThread.start();
    }
    public void resume() {
        engineThread.resume();
        //engineThread.run();
        //synchronized (engineThread) {
        //    engineThread.notify();
        //}
        //engine.resume();
    }
    public void pause() {
        //engine.await();
        //engine.pause();
        //engineThread.interrupt();
        engineThread.suspend();
    }
    // z jakiegos powodu wylacza widok na opcje w launch view
    public void switchOptions() {
        optionsPresenter.getView().allowInput(false);
        optionsPresenter.refreshView();
        if (!showOptions) {

            view.setContentRight(optionsPresenter.getView());
            showOptions = true;
        } else {
            view.setContentRight(null);
            showOptions = false;
        }
    }
    public void showOptions(boolean allowInput) {
        Platform.runLater(() -> {
            optionsPresenter.getView().allowInput(allowInput);
            optionsPresenter.refreshView();
            if (launchPresenter != null) {
                launchPresenter.showStartButton(!allowInput);
                launchPresenter.getView().setCenter(optionsPresenter.getView());
                launchPresenter.getView().getScene().getWindow().sizeToScene();
            }
        });
    }
    public void showLoadOptions() {
        Platform.runLater(() -> {
            loadOptionsPresenter.refreshView();
            launchPresenter.getView().setCenter(loadOptionsPresenter.getView());
            launchPresenter.getView().getScene().getWindow().sizeToScene();
        });
    }
    public String getInput() { return optionsPresenter.getInput(); }
    public boolean getCorpseToxicityState() { return optionsPresenter.getView().corpseToxicityCheckBoxState(); }

    // taki scuffed to do:
    // jakis inny sposob wywalania bledow?
    // wywalenie cssa do osobnej klasy
    // binding?
    // getView().getScene().setRoot(mainPresenter.getView());
    // pattern observator do wszystkich switch/toogle
    // osobne klasy na wielkie widoki (np options)
    // oddzielenie maina od launchera
    // znalezc sposob na poprawnie stopowanie watkow
}
