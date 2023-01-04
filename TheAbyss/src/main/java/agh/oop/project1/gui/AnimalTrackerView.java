package agh.oop.project1.gui;

import agh.oop.project1.Animal;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class AnimalTrackerView extends VBox {
    public static final int HEIGHT = 25;
    private AnimalTrackerPresenter presenter;
    public AnimalTrackerView(Animal animal) {
        buildView(animal);
    }
    protected void buildView(Animal animal) {
        setStyle("-fx-background-color: #333333;");
        setPadding(new Insets(15, 15, 15, 15));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().clear();

        String buttonString = (animal == null ? "Start tracking" : "Stop tracking");
        Button trackingButton = new Button(buttonString);
        trackingButton.setPrefSize(100, HEIGHT);
        trackingButton.setStyle("-fx-background-color: #660066; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");

        if (animal == null) {
            trackingButton.setOnAction(event -> presenter.getMainPresenter().startTracking());
            HBox buttonBox = new HBox(trackingButton);
            Label instructions = new Label("Click on an animal and press StartTracking");
            instructions.setStyle("-fx-text-fill: #BBBBBB; -fx-font-weight: bold");
            instructions.setTextAlignment(TextAlignment.CENTER);
            instructions.setWrapText(true);
            instructions.setPrefWidth(100);

            getChildren().addAll(buttonBox, instructions);
        }
        else {
            trackingButton.setOnAction(event -> presenter.getMainPresenter().stopTracking());
            // mozna jakis fajniejszy text, zrobilam label zeby nie bylo pusto
            Label trackingLabel = createValueLabel("Tracking is on");
            HBox trackingBox = new HBox(trackingButton,trackingLabel);
            trackingBox.setSpacing(10);
            trackingBox.setAlignment(Pos.CENTER);
            getChildren().add(trackingBox);

            String genomeString = "Genome";
            String activeGeneString = "Active gene";
            String energyString = "Energy";
            String plantsEatenCountString = "Plants eaten";
            String offspringCountString = "Children";
            String ageString = "Age";
            String deathDateString = "Death date";

            Label genomeNameLabel = createNameLabel(genomeString);
            Label activeGeneNameLabel = createNameLabel(activeGeneString);
            Label energyNameLabel = createNameLabel(energyString);
            Label plantsEatenCountNameLabel = createNameLabel(plantsEatenCountString);
            Label offspringCountNameLabel = createNameLabel(offspringCountString);
            Label ageNameLabel = createNameLabel(ageString);
            Label deathDateNameLabel = createNameLabel(deathDateString);

            Label genomeValueLabel = createValueLabel(animal.getGenes().toString());
            Label activeGeneValueLabel = createValueLabel(Integer.toString(animal.getGenes().getActiveGene()));
            int energy = (Math.max(animal.getEnergy(), 0));
            Label energyValueLabel = createValueLabel(Integer.toString(energy));
            Label plantsEatenCountValueLabel = createValueLabel(Integer.toString(animal.getPlantsEaten()));
            Label offspringCountValueLabel = createValueLabel(Integer.toString(animal.getOffspringCount()));
            int deathDate = (animal.getDeathDate() == -1 ? presenter.getMainPresenter().getMap().getDate() - animal.getBirthDate() : animal.getDeathDate() - animal.getBirthDate());
            Label ageValueLabel = createValueLabel(Integer.toString(deathDate));
            Label deathDateValueLabel = createValueLabel(Integer.toString(animal.getDeathDate()));

            HBox genomeBox = createHBox(genomeNameLabel, genomeValueLabel);
            HBox activeGeneBox = createHBox(activeGeneNameLabel, activeGeneValueLabel);
            HBox energyBox = createHBox(energyNameLabel, energyValueLabel);
            HBox plantsEatenCountBox = createHBox(plantsEatenCountNameLabel, plantsEatenCountValueLabel);
            HBox offspringCountBox = createHBox(offspringCountNameLabel, offspringCountValueLabel);
            HBox ageBox = createHBox(ageNameLabel, ageValueLabel);
            HBox deathDateBox = createHBox(deathDateNameLabel, deathDateValueLabel);
            if (animal.getDeathDate() == -1) {
                deathDateBox.setVisible(false);
                deathDateBox.setManaged(false);
            }
            getChildren().addAll(genomeBox,activeGeneBox,energyBox,plantsEatenCountBox,offspringCountBox,ageBox,deathDateBox);
        }
    }
    public void setPresenter (AnimalTrackerPresenter presenter) {
        this.presenter = presenter;
    }
    private Label createNameLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(100, HEIGHT);
        label.setStyle("-fx-background-color: #115522; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        return label;
    }
    private Label createValueLabel(String text) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(presenter.getMainPresenter().getOptions().genomeLength()*15, HEIGHT);
        label.setStyle("-fx-background-color: #669977; -fx-text-fill: #333333; -fx-font-weight: bold");
        return label;
    }
    private HBox createHBox(Label name, Label value) {
        HBox hbox = new HBox(name, value);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    public void refreshView(Animal animal) {
        Platform.runLater(() -> {
            buildView(animal);
            if (getScene() != null)  { getScene().getWindow().sizeToScene(); }
        });
    }
}
