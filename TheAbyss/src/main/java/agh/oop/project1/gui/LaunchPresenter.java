package agh.oop.project1.gui;

import agh.oop.project1.*;
import javafx.application.Platform;

import static java.lang.Integer.parseInt;

// poczatkowy ekran do uruchamiania symulacji
// umozliwia wybranie przygotowanych konfiguracji z folderu
// umozliwia wczytanie konfiguracji sporzadzonej przez uzytkownika
public class LaunchPresenter {
    private final LaunchView view;
    private LoadOptionsPresenter loadOptionsPresenter;
    private OptionsPresenter optionsPresenter;
    private SimulationOptions options;
    boolean noInputFields = true;
    public LaunchPresenter(LaunchView view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    public LaunchView getView() {
        return view;
    }
    public void setLoadOptionsPresenter(LoadOptionsPresenter loadOptionsPresenter) { this.loadOptionsPresenter = loadOptionsPresenter; }
    public void setOptionsPresenter(OptionsPresenter optionsPresenter) { this.optionsPresenter = optionsPresenter; }

    //fajnie byloby dodac wyjatek, kiedy jest za malo opcji
    public void showLoadOptions() {
        noInputFields = true;
        showStartButton(false);
        Platform.runLater(() -> {
            loadOptionsPresenter.refreshView();
            getView().setCenter(loadOptionsPresenter.getView());
            getView().getScene().getWindow().sizeToScene();
        });
    }
    public void showOptions(boolean allowInput) {
        Platform.runLater(() -> {
            optionsPresenter.getView().allowInput(allowInput);
            optionsPresenter.refreshView();
            showStartButton(!allowInput);
            getView().setCenter(optionsPresenter.getView());
            getView().getScene().getWindow().sizeToScene();
        });
    }
    public void initInputOptions() {
        if (noInputFields) {
            noInputFields = false;
            showOptions(true);
        } else {
            noInputFields = true;
            String[] stringOptions = optionsPresenter.getInput().split(" ");
            int[] intOptions = new int[12]; //cos jest nie tak z dlugoscia tablicy?
            boolean corpseToxicity = optionsPresenter.getView().corpseToxicityCheckBoxState();
            boolean saveStats = optionsPresenter.getView().saveStatsBoxState();
            for (int i = 0; i < stringOptions.length; i++) {
                if (isNumber(stringOptions[i])) {
                    intOptions[i] = parseInt(stringOptions[i]);
                } else throw new IllegalArgumentException(stringOptions[i] + " is not a legal simulation option");
            }
            setOptions(intOptions, corpseToxicity, saveStats);
            showOptions(false);
        }
    }
    //na pewno da sie ladniej
    public SimulationOptions getOptions() { return options; }
    public void setOptions(int[] intOptions, boolean corpseToxicity, boolean saveStats) {
        options = new SimulationOptions(intOptions[0], intOptions[1], corpseToxicity, intOptions[2], intOptions[3], intOptions[4],
                intOptions[5], intOptions[6], intOptions[7], intOptions[8], intOptions[9], intOptions[10], intOptions[11], saveStats);
    }
    public static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public void startSimulation() {
        MainPresenter mainPresenter = new MainPresenter(new MainView());
        mainPresenter.initSimulation(options);
    }
    //wzorzec obserwator moze?
    public void showStartButton(boolean show) {
        view.showStartButton(show);
    }

}

