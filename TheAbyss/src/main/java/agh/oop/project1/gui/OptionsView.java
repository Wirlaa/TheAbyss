package agh.oop.project1.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class OptionsView extends VBox {
    static final int HEIGHT = 25;
    private OptionsPresenter presenter;
    public OptionsView() {
        buildView();
    }
    protected void buildView() {
        setStyle("-fx-background-color: #333333;");
        setPadding(new Insets(15, 15, 15, 15));
        setSpacing(10);

        TextField mapHeight = createTextField();
        HBox mapHeightBox = createHBox(createLabel("Map height", "n"),mapHeight,createLabel("value","c"));
        TextField mapWidth = createTextField();
        HBox mapWidthBox = createHBox(createLabel("Map width","n"),mapWidth,createLabel("value","c"));
        TextField plantsAmount = createTextField();
        HBox plantsAmountBox = createHBox(createLabel("Amount of plants","n"),plantsAmount,createLabel("value","c"));
        TextField animalsAmount = createTextField();
        HBox animalsAmountBox = createHBox(createLabel("Amount of animals","n"),animalsAmount,createLabel("value","c"));
        TextField startingEnergy = createTextField();
        HBox startingEnergyBox = createHBox(createLabel("Starting energy","n"),startingEnergy,createLabel("value","c"));
        TextField energyForAction = createTextField(); // jak nazwac energie najedzonego zwierzaka?
        HBox energyForActionBox = createHBox(createLabel("Energy needed for action","n"),energyForAction,createLabel("value","c"));
        TextField energyToBreed = createTextField();
        HBox energyToBreedBox = createHBox(createLabel("Energy needed to breed","n"),energyToBreed,createLabel("value","c"));
        TextField minMutation = createTextField();
        HBox minMutationBox = createHBox(createLabel("Min num of mutations","n"),minMutation,createLabel("value","c"));
        TextField maxMutation = createTextField();
        HBox maxMutationBox = createHBox(createLabel("Max num of mutations","n"),maxMutation,createLabel("value","c"));
        TextField genomeLength = createTextField();
        HBox genomeLengthBox = createHBox(createLabel("Genome length","n"),genomeLength,createLabel("value","c"));

        //plantGrowth

        getChildren().addAll(mapHeightBox,mapWidthBox,plantsAmountBox,animalsAmountBox,startingEnergyBox,energyForActionBox,
                energyToBreedBox,minMutationBox,maxMutationBox,genomeLengthBox);
    }
    public void setPresenter (OptionsPresenter presenter) {
        this.presenter = presenter;
    }
    private TextField createTextField() {
        TextField text = new TextField();
        text.setFocusTraversable(false);
        text.setPrefWidth(50);
        text.setStyle("-fx-background-color: #CCCCCC");
        return text;
    }

    private Label createLabel(String text, String option){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        if (Objects.equals(option, "n")) {
            label.setMinSize(200, HEIGHT);
            label.setStyle("-fx-background-color: #6666AA; -fx-text-fill: #BBBBBB; -fx-font-weight: bold");
        } else if (Objects.equals(option, "c")) {
            label.setMinSize(50, HEIGHT);
            label.setStyle("-fx-background-color: #66AA66; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        }
        return label;
    }


    private Button createButton() {
        Button button = new Button("Set");
        button.setPrefSize(50, HEIGHT);
        button.setStyle("-fx-background-color: #4066AA; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        //button.setOnAction(event -> action);
        return button;
    }
    private HBox createHBox(Label name, TextField textField, Label current) {
        HBox hbox = new HBox(name, textField, current, createButton());
        hbox.setSpacing(10);
        return hbox;
    }
}
