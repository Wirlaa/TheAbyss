package agh.oop.project1.gui;

import agh.oop.project1.HellishGateMap;
import agh.oop.project1.IEngine;
import agh.oop.project1.IWorldMap;
import agh.oop.project1.SimulationEngine;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

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
                if (isInteger(stringOptions[i], 10)) {
                    intOptions[i] = parseInt(stringOptions[i]);
                } else throw new IllegalArgumentException(stringOptions[i] + " is not a legal simulation option");
            }
            mainPresenter.setOptions(intOptions, corpseToxicity);
            mainPresenter.showOptions(false);
        }
    }
    private static boolean isInteger(String s, int radix) {
        Scanner sc = new Scanner(s.trim());
        if(!sc.hasNextInt(radix)) return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }
    public void startSimulation() {
        IWorldMap map = new HellishGateMap(mainPresenter.getOptions().mapWidth(), mainPresenter.getOptions().mapHeight(), mainPresenter.getOptions());
        IEngine engine = new SimulationEngine(map, mainPresenter.getOptions());
        MapPresenter mapPresenter = new MapPresenter(new MapView(),mainPresenter);
        engine.addObserver(mapPresenter);
        mainPresenter.setMapPresenter(mapPresenter);
        mainPresenter.setEngine(engine);
        mainPresenter.setMap(map);
        mainPresenter.start();
        Stage stage = new Stage();
        stage.setScene(new Scene(mainPresenter.getView()));
        stage.setTitle("Simulation");
        stage.show();
    }
}

