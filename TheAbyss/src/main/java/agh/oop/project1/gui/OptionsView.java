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
    private boolean allowChange;
    private OptionsPresenter presenter;
    public OptionsView(boolean allowChange) {
        this.allowChange = allowChange;
        buildView();
    }
    protected void buildView() {
        setStyle("-fx-background-color: #333333;");
        setPadding(new Insets(15, 15, 15, 15));
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setMaxSize(450,0); //jak zrobic samo dostosowujace sie?
        // jak zrobic, zeby przy resize stalo w miejscu? moze uzyc anchor zamiast border?

        if (allowChange) {
            TextField mapHeight = createTextField();
            HBox mapHeightBox = this.createSetHBox(createLabel("Map height", "name"),mapHeight,createLabel("value","value"));
            TextField mapWidth = createTextField();
            HBox mapWidthBox = this.createSetHBox(createLabel("Map width","name"),mapWidth,createLabel("value","value"));
            TextField plantsAmount = createTextField();
            HBox plantsAmountBox = this.createSetHBox(createLabel("Amount of plants","name"),plantsAmount,createLabel("value","value"));
            TextField animalsAmount = createTextField();
            HBox animalsAmountBox = this.createSetHBox(createLabel("Amount of animals","name"),animalsAmount,createLabel("value","value"));
            TextField startingEnergy = createTextField();
            HBox startingEnergyBox = this.createSetHBox(createLabel("Starting energy","name"),startingEnergy,createLabel("value","value"));
            TextField energyForAction = createTextField(); // jak nazwac energie najedzonego zwierzaka?
            HBox energyForActionBox = this.createSetHBox(createLabel("Energy needed for action","name"),energyForAction,createLabel("value","value"));
            TextField energyToBreed = createTextField();
            HBox energyToBreedBox = this.createSetHBox(createLabel("Energy needed to breed","name"),energyToBreed,createLabel("value","value"));
            TextField minMutation = createTextField();
            HBox minMutationBox = this.createSetHBox(createLabel("Min num of mutations","name"),minMutation,createLabel("value","value"));
            TextField maxMutation = createTextField();
            HBox maxMutationBox = this.createSetHBox(createLabel("Max num of mutations","name"),maxMutation,createLabel("value","value"));
            TextField genomeLength = createTextField();
            HBox genomeLengthBox = this.createSetHBox(createLabel("Genome length","name"),genomeLength,createLabel("value","value"));

            //plantGrowth
            getChildren().addAll(mapHeightBox,mapWidthBox,plantsAmountBox,animalsAmountBox,startingEnergyBox,energyForActionBox,
                    energyToBreedBox,minMutationBox,maxMutationBox,genomeLengthBox);
        } else {
            HBox mapHeightBox = this.createHBox(createLabel("Map height", "name"),createLabel("value","value"));
            HBox mapWidthBox = this.createHBox(createLabel("Map width","name"),createLabel("value","value"));
            HBox plantsAmountBox = this.createHBox(createLabel("Amount of plants","name"),createLabel("value","value"));
            HBox animalsAmountBox = this.createHBox(createLabel("Amount of animals","name"),createLabel("value","value"));
            HBox startingEnergyBox = this.createHBox(createLabel("Starting energy","name"),createLabel("value","value"));
            HBox energyForActionBox = this.createHBox(createLabel("Energy needed for action","name"),createLabel("value","value"));
            HBox energyToBreedBox = this.createHBox(createLabel("Energy needed to breed","name"),createLabel("value","value"));
            HBox minMutationBox = this.createHBox(createLabel("Min num of mutations","name"),createLabel("value","value"));
            HBox maxMutationBox = this.createHBox(createLabel("Max num of mutations","name"),createLabel("value","value"));
            HBox genomeLengthBox = this.createHBox(createLabel("Genome length","name"),createLabel("value","value"));
            getChildren().addAll(mapHeightBox,mapWidthBox,plantsAmountBox,animalsAmountBox,startingEnergyBox,energyForActionBox,
                    energyToBreedBox,minMutationBox,maxMutationBox,genomeLengthBox);
        }
    }
    public void setPresenter (OptionsPresenter presenter) {
        this.presenter = presenter;
    }
    private TextField createTextField() {
        TextField text = new TextField();
        text.setFocusTraversable(false);
        text.setPrefWidth(90);
        text.setPromptText("Enter a value:");
        text.setStyle("-fx-background-color: #CCCCCC; -fx-prompt-text-fill: #666666");
        return text;
    }
    // name i value moglyby byc enumami czy cos
    private Label createLabel(String text, String option){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        if (Objects.equals(option, "name")) {
            label.setPrefSize(200, HEIGHT);
            label.setStyle("-fx-background-color: #115522; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        } else if (Objects.equals(option, "value")) {
            label.setPrefSize(50, HEIGHT);
            label.setStyle("-fx-background-color: #669977; -fx-text-fill: #333333; -fx-font-weight: bold");
        }
        return label;
    }
    private Button createButton() {
        Button button = new Button("Set");
        button.setPrefSize(50, HEIGHT);
        button.setStyle("-fx-background-color: #053011; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        //button.setOnAction(event -> action);
        return button;
    }
    private HBox createSetHBox(Label name, TextField textField, Label current) {
        HBox hbox = new HBox(name, textField, current, createButton());
        hbox.setSpacing(10);
        return hbox;
    }
    private HBox createHBox(Label name, Label current) {
        HBox hbox = new HBox(name, current);
        hbox.setSpacing(10);
        return hbox;
    }
    private void switchChange() {
        allowChange = !allowChange;
    }
}
