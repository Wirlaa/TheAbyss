package agh.oop.project1.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class LaunchView extends BorderPane {
    private LaunchPresenter presenter;
    private Button startButton;
    public LaunchView() {
        buildView();
    }
    protected void buildView() {
        setStyle("-fx-background-color: #000000;");

        // góra - tytuł, instrukcje, sterowanie
        VBox topVBox = new VBox();
        topVBox.setPadding(new Insets(15, 15, 15, 15));
        topVBox.setSpacing(10);
        topVBox.setAlignment(Pos.CENTER);

        Label simulationParameters = new Label("SIMULATION PARAMETERS");
        simulationParameters.setStyle("-fx-text-fill: #CCCCCC; -fx-font-size: 35; -fx-font-weight: bold");

        Label instructions = new Label(
                "Load a predefined configuration or enter simulation parameters and press SetOptions to confirm. " +
                        "Then press start to run the simulation.");
        instructions.setStyle("-fx-text-fill: #CCCCCC; -fx-font-size: 15");
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setWrapText(true);
        instructions.setPrefWidth(450);

        HBox controlsHBox = new HBox();
        controlsHBox.setSpacing(25);
        controlsHBox.setAlignment(Pos.CENTER);

        // przyciski wczytaj, ustaw i start
        Button showLoadingOptions = new Button("Load options");
        showLoadingOptions.setPrefSize(120, 25);
        showLoadingOptions.setStyle("-fx-background-color: #006699; -fx-text-fill: #CCCCCC; -fx-font-weight: bold; -fx-font-size: 15");
        showLoadingOptions.setOnAction(event -> presenter.showLoadOptions());

        Button setOptionsButton = new Button("Set options");
        setOptionsButton.setPrefSize(120, 25);
        setOptionsButton.setStyle("-fx-background-color: #990000; -fx-text-fill: #EEEEEE; -fx-font-weight: bold; -fx-font-size: 15");
        setOptionsButton.setOnAction(event -> presenter.initInputOptions());

        startButton = new Button("Start");
        startButton.setPrefSize(120, 25);
        startButton.setStyle("-fx-background-color: #009900; -fx-text-fill: #EEEEEE; -fx-font-weight: bold; -fx-font-size: 15");
        startButton.setOnAction(event -> presenter.startSimulation());
        showStartButton(false);

        controlsHBox.getChildren().addAll(showLoadingOptions, setOptionsButton,startButton);
        topVBox.getChildren().addAll(simulationParameters,instructions,controlsHBox);
        setTop(topVBox);
    }
    public void setPresenter (LaunchPresenter presenter) {
        this.presenter = presenter;
    }
    public void showStartButton(boolean show) {
        startButton.setVisible(show);
        startButton.setManaged(show);
    }
}
