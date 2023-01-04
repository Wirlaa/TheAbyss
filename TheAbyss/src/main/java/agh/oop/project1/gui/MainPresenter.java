package agh.oop.project1.gui;

import agh.oop.project1.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPresenter {
    private IWorldMap map;
    private IEngine engine;
    private Thread engineThread;
    private final MainView view;
    private MapPresenter mapPresenter;
    private OptionsPresenter optionsPresenter;
    private SimulationOptions options;
    private boolean showOptions = false;
    public MainPresenter (MainView view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    public IWorldMap getMap() { return map; }
    public MainView getView(){ return view; }
    public void setMapPresenter(MapPresenter mapPresenter) { this.mapPresenter = mapPresenter; }
    public void setOptionsPresenter(OptionsPresenter optionsPresenter) { this.optionsPresenter = optionsPresenter; }
    public SimulationOptions getOptions() { return options; }
    public void initSimulation(SimulationOptions options) {
        this.options = options;
        IWorldMap map = new HellishGateMap(options.mapWidth(), options.mapHeight(), options);
        this.map = map;
        IEngine engine = new SimulationEngine(map, options);
        this.engine = engine;

        OptionsPresenter newOptionsPresenter = new OptionsPresenter(new OptionsView(true), this);
        setOptionsPresenter(newOptionsPresenter);

        MapPresenter mapPresenter = new MapPresenter(new MapView(),this);
        engine.addObserver(mapPresenter);
        setMapPresenter(mapPresenter);

        Stage stage = new Stage();
        stage.setScene(new Scene(getView()));
        stage.setTitle("Simulation");
        stage.show();
        createThread();
    }
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
