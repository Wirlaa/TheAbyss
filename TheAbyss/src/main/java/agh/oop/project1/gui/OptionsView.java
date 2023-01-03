package agh.oop.project1.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OptionsView extends VBox {
    public static final int HEIGHT = 25;
    private final String mapWidthString = "Map width";
    private final String mapHeightString = "Map height";
    private final String corpseToxicityString = "Toggle toxic corpses";
    private final String initialPlantCountString = "Initial count of plants";
    private final String initialAnimalCountString = "Initial count of animals";
    private final String plantsGrowingEachDayString = "Amount of plants growing each day";
    private final String startingEnergyString = "Starting energy";
    private final String energyFromOnePlantString = "Energy received from eating one plant";
    private final String energyToReproduceString = "Energy needed to be able to reproduce";
    private final String reproductionCostString = "Reproduction cost";
    private final String minMutatedGenesString = "Min num of mutations";
    private final String maxMutatedGenesString = "Max num of mutations";
    private final String genomeLengthString = "Genome length";
    private final Label mapWidthNameLabel = createNameLabel(mapWidthString);
    private final Label mapHeightNameLabel = createNameLabel(mapHeightString);
    private final Label corpseToxicityNameLabel = createNameLabel(corpseToxicityString);
    private final Label initialPlantCountNameLabel = createNameLabel(initialPlantCountString);
    private final Label initialAnimalCountNameLabel = createNameLabel(initialAnimalCountString);
    private final Label plantsGrowingEachDayNameLabel = createNameLabel(plantsGrowingEachDayString);
    private final Label startingEnergyNameLabel = createNameLabel(startingEnergyString);
    private final Label energyFromOnePlantNameLabel = createNameLabel(energyFromOnePlantString);
    private final Label energyToReproduceNameLabel = createNameLabel(energyToReproduceString);
    private final Label reproductionCostNameLabel = createNameLabel(reproductionCostString);
    private final Label minMutatedGenesNameLabel = createNameLabel(minMutatedGenesString);
    private final Label maxMutatedGenesNameLabel = createNameLabel(maxMutatedGenesString);
    private final Label genomeLengthNameLabel = createNameLabel(genomeLengthString);
    private Label mapWidthValueLabel;
    private Label mapHeightValueLabel;
    private Label corpseToxicityValueLabel;
    private Label initialPlantCountValueLabel;
    private Label initialAnimalCountValueLabel;
    private Label plantsGrowingEachDayValueLabel;
    private Label startingEnergyValueLabel;
    private Label energyFromOnePlantValueLabel;
    private Label energyToReproduceValueLabel;
    private Label reproductionCostValueLabel;
    private Label minMutatedGenesValueLabel;
    private Label maxMutatedGenesValueLabel;
    private Label genomeLengthValueLabel;
    private TextField mapWidthTextField;
    private TextField mapHeightTextField;
    private CheckBox corpseToxicityCheckBox;
    private TextField initialPlantCountTextField;
    private TextField initialAnimalCountTextField;
    private TextField plantsGrowingEachDayTextField;
    private TextField startingEnergyTextField;
    private TextField energyFromOnePlantTextField;
    private TextField energyToReproduceTextField;
    private TextField reproductionCostTextField;
    private TextField minMutatedGenesTextField;
    private TextField maxMutatedGenesTextField;
    private TextField genomeLengthTextField;
    private HBox mapWidthBox;
    private HBox mapHeightBox;
    private HBox corpseToxicityBox;
    private HBox initialPlantCountBox;
    private HBox initialAnimalCountBox;
    private HBox plantsGrowingEachDayBox;
    private HBox startingEnergyBox;
    private HBox energyFromOnePlantBox;
    private HBox energyToReproduceBox;
    private HBox reproductionCostBox;
    private HBox minMutatedGenesBox;
    private HBox maxMutatedGenesBox;
    private HBox genomeLengthBox;
    private boolean allowInput;
    private OptionsPresenter presenter;
    public OptionsView(boolean allowInput) {
        this.allowInput = allowInput;
        buildView();
    }
    protected void buildView() {
        setStyle("-fx-background-color: #333333;");
        setPadding(new Insets(15, 15, 15, 15));
        setSpacing(10);
        setAlignment(Pos.CENTER);
        // jak zrobic, zeby przy resize stalo w miejscu? moze uzyc anchor zamiast border?

        if (allowInput) {
            setBoxesWithTextFields();
        } else {
            setValues();
            setBoxes();
        }
        getChildren().clear();
        getChildren().addAll(mapHeightBox,mapWidthBox,corpseToxicityBox,initialPlantCountBox,initialAnimalCountBox,
                plantsGrowingEachDayBox,startingEnergyBox,energyFromOnePlantBox,energyToReproduceBox,
                reproductionCostBox,minMutatedGenesBox,maxMutatedGenesBox,genomeLengthBox);
    }
    public void setPresenter (OptionsPresenter presenter) {
        this.presenter = presenter;
    }
    //strasznie brzydkie
    //przydalyby sie wyjatki krzyczace jak jest zly input
    //i fajnie byloby moc wyswietlac bledy w aplikacji a nie konsoli
    public String getInput() {
        if (mapHeightTextField.getText().isEmpty() ||
                mapWidthTextField.getText().isEmpty() ||
                initialPlantCountTextField.getText().isEmpty() ||
                initialAnimalCountTextField.getText().isEmpty() ||
                plantsGrowingEachDayTextField.getText().isEmpty() ||
                startingEnergyTextField.getText().isEmpty() ||
                energyFromOnePlantTextField.getText().isEmpty() ||
                energyToReproduceTextField.getText().isEmpty() ||
                reproductionCostTextField.getText().isEmpty() ||
                minMutatedGenesTextField.getText().isEmpty() ||
                maxMutatedGenesTextField.getText().isEmpty() ||
                genomeLengthTextField.getText().isEmpty()) {
             throw new IllegalArgumentException("Text field cannot be empty");
        }
        return  mapHeightTextField.getText() + " " +
                mapWidthTextField.getText() + " " +
                initialPlantCountTextField.getText() + " " +
                initialAnimalCountTextField.getText() + " " +
                plantsGrowingEachDayTextField.getText() + " " +
                startingEnergyTextField.getText() + " " +
                energyFromOnePlantTextField.getText() + " " +
                energyToReproduceTextField.getText() + " " +
                reproductionCostTextField.getText() + " " +
                minMutatedGenesTextField.getText() + " " +
                maxMutatedGenesTextField.getText() + " " +
                genomeLengthTextField.getText();
    }
    public boolean corpseToxicityCheckBoxState() {
        return corpseToxicityCheckBox.isSelected();
    }
    private TextField createTextField() {
        TextField text = new TextField();
        text.setFocusTraversable(false);
        text.setPrefWidth(90);
        text.setPromptText("Enter a value:");
        text.setStyle("-fx-background-color: #CCCCCC; -fx-prompt-text-fill: #666666");
        return text;
    }
    private Label createNameLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(250, HEIGHT);
        label.setStyle("-fx-background-color: #115522; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        return label;
    }
    private Label createValueLabel(String text) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(50, HEIGHT);
        label.setStyle("-fx-background-color: #669977; -fx-text-fill: #333333; -fx-font-weight: bold");
        return label;
    }
    /* poprzednio kazde pole mialo wlasny przycisk
    private Button createButton() {
        Button button = new Button("Set");
        button.setPrefSize(50, HEIGHT);
        button.setStyle("-fx-background-color: #053011; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        //button.setOnAction(event -> action);
        return button;
    }
     */
    private HBox createHBoxWithTextField(Label name, Label current, Node textField) {
        //HBox hbox = new HBox(name, current, textField);
        HBox hbox = new HBox(name, textField);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    private HBox createHBox(Label name, Label current) {
        HBox hbox = new HBox(name, current);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    private void setValues() {
        mapWidthValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().mapWidth()));
        mapHeightValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().mapHeight()));
        corpseToxicityValueLabel = createValueLabel(Boolean.toString(presenter.getMainPresenter().getOptions().corpseToxicity()));
        initialPlantCountValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().initialPlantCount()));
        initialAnimalCountValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().initialAnimalCount()));
        plantsGrowingEachDayValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().plantsGrowingEachDay()));
        startingEnergyValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().startingEnergy()));
        energyFromOnePlantValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().energyFromOnePlant()));
        energyToReproduceValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().energyToReproduce()));
        reproductionCostValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().reproductionCost()));
        minMutatedGenesValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().minMutatedGenes()));
        maxMutatedGenesValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().maxMutatedGenes()));
        genomeLengthValueLabel = createValueLabel(Integer.toString(presenter.getMainPresenter().getOptions().genomeLength()));
    }
    private void setBoxesWithTextFields() {
        mapWidthTextField = createTextField();
        mapWidthBox = createHBoxWithTextField(mapWidthNameLabel,mapHeightValueLabel,mapWidthTextField);
        mapHeightTextField = createTextField();
        mapHeightBox = createHBoxWithTextField(mapHeightNameLabel,mapWidthValueLabel,mapHeightTextField);
        corpseToxicityCheckBox = new CheckBox();
        corpseToxicityCheckBox.setMinWidth(90);
        corpseToxicityCheckBox.setPadding(new Insets(0, 35, 0, 35));
        corpseToxicityBox = createHBoxWithTextField(corpseToxicityNameLabel,corpseToxicityValueLabel, corpseToxicityCheckBox);
        initialPlantCountTextField = createTextField();
        initialPlantCountBox = createHBoxWithTextField(initialPlantCountNameLabel,initialPlantCountValueLabel,initialPlantCountTextField);
        initialAnimalCountTextField = createTextField();
        initialAnimalCountBox = createHBoxWithTextField(initialAnimalCountNameLabel,initialAnimalCountValueLabel,initialAnimalCountTextField);
        plantsGrowingEachDayTextField = createTextField();
        plantsGrowingEachDayBox = createHBoxWithTextField(plantsGrowingEachDayNameLabel,plantsGrowingEachDayValueLabel,plantsGrowingEachDayTextField);
        startingEnergyTextField = createTextField();
        startingEnergyBox = createHBoxWithTextField(startingEnergyNameLabel,startingEnergyValueLabel,startingEnergyTextField);
        energyFromOnePlantTextField = createTextField();
        energyFromOnePlantBox = createHBoxWithTextField(energyFromOnePlantNameLabel,energyFromOnePlantValueLabel,energyFromOnePlantTextField);
        energyToReproduceTextField = createTextField();
        energyToReproduceBox = createHBoxWithTextField(energyToReproduceNameLabel,energyToReproduceValueLabel,energyToReproduceTextField);
        reproductionCostTextField = createTextField();
        reproductionCostBox = createHBoxWithTextField(reproductionCostNameLabel,reproductionCostValueLabel,reproductionCostTextField);
        minMutatedGenesTextField = createTextField();
        minMutatedGenesBox = createHBoxWithTextField(minMutatedGenesNameLabel,minMutatedGenesValueLabel,minMutatedGenesTextField);
        maxMutatedGenesTextField = createTextField();
        maxMutatedGenesBox = createHBoxWithTextField(maxMutatedGenesNameLabel,maxMutatedGenesValueLabel,maxMutatedGenesTextField);
        genomeLengthTextField = createTextField();
        genomeLengthBox = createHBoxWithTextField(genomeLengthNameLabel,genomeLengthValueLabel,genomeLengthTextField);
    }
    private void setBoxes() {
        mapWidthBox = createHBox(mapWidthNameLabel,mapHeightValueLabel);
        mapHeightBox = createHBox(mapHeightNameLabel,mapWidthValueLabel);
        corpseToxicityBox = createHBox(corpseToxicityNameLabel,corpseToxicityValueLabel);
        initialPlantCountBox = createHBox(initialPlantCountNameLabel,initialPlantCountValueLabel);
        initialAnimalCountBox = createHBox(initialAnimalCountNameLabel,initialAnimalCountValueLabel);
        plantsGrowingEachDayBox = createHBox(plantsGrowingEachDayNameLabel,plantsGrowingEachDayValueLabel);
        startingEnergyBox = createHBox(startingEnergyNameLabel,startingEnergyValueLabel);
        energyFromOnePlantBox = createHBox(energyFromOnePlantNameLabel,energyFromOnePlantValueLabel);
        energyToReproduceBox = createHBox(energyToReproduceNameLabel,energyToReproduceValueLabel);
        reproductionCostBox = createHBox(reproductionCostNameLabel,reproductionCostValueLabel);
        minMutatedGenesBox = createHBox(minMutatedGenesNameLabel,minMutatedGenesValueLabel);
        maxMutatedGenesBox = createHBox(maxMutatedGenesNameLabel,maxMutatedGenesValueLabel);
        genomeLengthBox = createHBox(genomeLengthNameLabel,genomeLengthValueLabel);
    }
    public void allowInput (boolean allowInput) {
        this.allowInput = allowInput;
    }
}
