package agh.oop.project1.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class StartView extends BorderPane {
    private StartPresenter presenter;
    public StartView() {
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
                "Enter simulation parameters or load a predefined configuration. " +
                        "Then press start to run the simulation.");
        instructions.setStyle("-fx-text-fill: #CCCCCC; -fx-font-size: 15");
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setWrapText(true);
        instructions.setPrefWidth(450);

        HBox controlsHBox = new HBox();
        controlsHBox.setSpacing(25);
        controlsHBox.setAlignment(Pos.CENTER);

        // przyciski wczytaj i start
        Button load = new Button("Load");
        load.setPrefSize(100, 25);
        load.setStyle("-fx-background-color: #006699; -fx-text-fill: #CCCCCC; -fx-font-weight: bold; -fx-font-size: 15");
        //button.setOnAction(event -> action);

        Button start = new Button("Start");
        start.setPrefSize(100, 25);
        start.setStyle("-fx-background-color: #009900; -fx-text-fill: #EEEEEE; -fx-font-weight: bold; -fx-font-size: 15");
        //button.setOnAction(event -> action);

        Button exit = new Button("Exit");
        exit.setPrefSize(100, 25);
        exit.setStyle("-fx-background-color: #990000; -fx-text-fill: #EEEEEE; -fx-font-weight: bold; -fx-font-size: 15");
        //button.setOnAction(event -> action);

        controlsHBox.getChildren().addAll(load, start, exit);
        topVBox.getChildren().addAll(simulationParameters,instructions,controlsHBox);
        setTop(topVBox);
    }
    public void setPresenter (StartPresenter presenter) {
        this.presenter = presenter;
    }
}
