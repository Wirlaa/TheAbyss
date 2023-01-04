package agh.oop.project1.gui;

import agh.oop.project1.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPresenter {
    SimulationStatistics simStats;
    private IWorldMap map;
    private IEngine engine;
    private Thread engineThread;
    private final MainView view;
    private MapPresenter mapPresenter;
    private OptionsPresenter optionsPresenter;
    private AnimalTrackerPresenter animalTrackerPresenter;
    private SimulationOptions options;
    private Animal trackedAnimal = null;
    private boolean showOptions = false;
    private boolean showStatistics = false;
    public MainPresenter (MainView view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    public IWorldMap getMap() { return map; }
    public MainView getView(){ return view; }
    public void setMapPresenter(MapPresenter mapPresenter) { this.mapPresenter = mapPresenter; }
    public void setOptionsPresenter(OptionsPresenter optionsPresenter) { this.optionsPresenter = optionsPresenter; }
    public void setStatisticsPresenter(AnimalTrackerPresenter animalTrackerPresenter) { this.animalTrackerPresenter = animalTrackerPresenter; }
    public SimulationOptions getOptions() { return options; }
    public void initSimulation(SimulationOptions options) {
        this.options = options;
        simStats = new SimulationStatistics();
        IWorldMap map = new HellishGateMap(options.mapWidth(), options.mapHeight(), options, simStats);
        simStats.setMap(map);
        this.map = map;
        map.addAnimalDeathObserver(simStats);
        map.setSimStats(simStats);
        IEngine engine = new SimulationEngine(map, options, simStats);
        this.engine = engine;

        MapPresenter mapPresenter = new MapPresenter(new MapView(),this);
        engine.addObserver(mapPresenter);
        setMapPresenter(mapPresenter);

        OptionsPresenter newOptionsPresenter = new OptionsPresenter(new OptionsView(true), this);
        setOptionsPresenter(newOptionsPresenter);

        AnimalTrackerPresenter animalTrackerPresenter = new AnimalTrackerPresenter(new AnimalTrackerView(null),this);
        engine.addObserver(animalTrackerPresenter);
        setStatisticsPresenter(animalTrackerPresenter);

        Stage stage = new Stage();
        stage.setScene(new Scene(getView()));
        stage.setTitle("Simulation");
        stage.show();
        createThread();
        stage.setOnCloseRequest(event -> engineThread.stop());
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
    public void toggleOptions() {
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
    public void toggleAnimalTracking() {
        if (!showStatistics) {
            view.setContentLeft(animalTrackerPresenter.getView());
            showStatistics = true;
        } else {
            view.setContentLeft(null);
            showStatistics = false;
        }
    }
    public void setTrackedAnimal(Animal trackedAnimal) { this.trackedAnimal = trackedAnimal; }
    public void startTracking() {
        engine.setTrackedAnimal(trackedAnimal);
        animalTrackerPresenter.getView().refreshView(trackedAnimal);
    }
    public void stopTracking() {
        engine.setTrackedAnimal(null);
        animalTrackerPresenter.getView().refreshView(null);
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
