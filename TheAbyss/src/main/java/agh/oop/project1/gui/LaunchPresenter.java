package agh.oop.project1.gui;

import agh.oop.project1.HellishGateMap;
import agh.oop.project1.IEngine;
import agh.oop.project1.IWorldMap;
import agh.oop.project1.SimulationEngine;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

// poczatkowy ekran do uruchamiania symulacji
// umozliwia wybranie przygotowanych konfiguracji z folderu
// umozliwia wczytanie konfiguracji sporzadzonej przez uzytkownika
public class LaunchPresenter {
    private final LaunchView view;
    private final MainPresenter mainPresenter;
    boolean noInputFields = true;
    public LaunchPresenter(LaunchView view, MainPresenter mainPresenter) {
        this.view = view;
        this.mainPresenter = mainPresenter;
        this.view.setPresenter(this);
    }
    public LaunchView getView() {
        return view;
    }
    public void setNoInputFields(boolean noInputFields) { this.noInputFields = noInputFields; }
    //fajnie byloby dodac wyjatek, kiedy jest za malo opcji
    public void showLoadOptions() {
        noInputFields = true;
        showStartButton(false);
        mainPresenter.showLoadOptions();
    }
    public void initInputOptions() {
        if (noInputFields) {
            noInputFields = false;
            mainPresenter.showOptions(true);
        } else {
            noInputFields = true;
            String[] stringOptions = mainPresenter.getInput().split(" ");
            int[] intOptions = new int[12]; //cos jest nie tak z dlugoscia tablicy?
            boolean corpseToxicity = mainPresenter.getCorpseToxicityState();
            for (int i = 0; i < stringOptions.length; i++) {
                if (isNumber(stringOptions[i])) {
                    intOptions[i] = parseInt(stringOptions[i]);
                } else throw new IllegalArgumentException(stringOptions[i] + " is not a legal simulation option");
            }
            mainPresenter.setOptions(intOptions, corpseToxicity);
            mainPresenter.showOptions(false);
        }
    }
    public static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public void startSimulation() {
        IWorldMap map = new HellishGateMap(mainPresenter.getOptions().mapWidth(), mainPresenter.getOptions().mapHeight(), mainPresenter.getOptions());
        IEngine engine = new SimulationEngine(map, mainPresenter.getOptions());

        MainPresenter newMainPresenter = new MainPresenter(new MainView());
        newMainPresenter.setOptions(mainPresenter.getOptions());

        OptionsPresenter optionsPresenter = new OptionsPresenter(new OptionsView(true), newMainPresenter);
        newMainPresenter.setOptionsPresenter(optionsPresenter);

        MapPresenter mapPresenter = new MapPresenter(new MapView(),newMainPresenter);
        engine.addObserver(mapPresenter);
        newMainPresenter.setMapPresenter(mapPresenter);

        newMainPresenter.setEngine(engine);
        newMainPresenter.setMap(map);
        newMainPresenter.createThread();

        Stage stage = new Stage();
        stage.setScene(new Scene(newMainPresenter.getView()));
        stage.setTitle("Simulation");
        stage.show();
    }
    //wzorzec obserwator moze?
    public void showStartButton(boolean show) {
        view.showStartButton(show);
    }
}

