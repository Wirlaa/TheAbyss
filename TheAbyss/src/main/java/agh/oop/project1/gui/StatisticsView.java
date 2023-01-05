package agh.oop.project1.gui;

import agh.oop.project1.SimulationStatistics;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatisticsView extends VBox {
    public static final int HEIGHT = 25;
    private StatisticsPresenter presenter;
    private final SimulationStatistics statistics;
    public StatisticsView(SimulationStatistics statistics) {
        this.statistics = statistics;
    }
    protected void buildView() {
        setStyle("-fx-background-color: #333333;");
        setPadding(new Insets(15, 15, 15, 15));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().clear();

        String aliveAnimalsCountString = "Alive animals";
        String plantsOnMapString = "Plants";
        String emptyFieldsString = "Empty fields";
        String popularGenotypeString = "The most popular genome";
        String averageEnergyString = "Average energy";
        String averageAgeString = "Average age of dead animals";

        Label aliveAnimalsCountNameLabel = createNameLabel(aliveAnimalsCountString);
        Label plantsOnMapNameLabel = createNameLabel(plantsOnMapString);
        Label emptyFieldsNameLabel = createNameLabel(emptyFieldsString);
        Label popularGenotypeNameLabel = createNameLabel(popularGenotypeString);
        Label averageEnergyNameLabel = createNameLabel(averageEnergyString);
        Label averageAgeNameLabel = createNameLabel(averageAgeString);

        Label aliveAnimalsCountValueLabel = createValueLabel(Integer.toString(statistics.getAliveAnimalsCount()));
        Label plantsOnMapValueLabel = createValueLabel(Integer.toString(statistics.getPlantsOnMap()));
        Label emptyFieldsValueLabel = createValueLabel(Integer.toString(statistics.getFreeFields()));
        Label popularGenotypeValueLabel = createValueLabel(statistics.getTheMostPopularGentype().toString());
        Label averageEnergyValueLabel = createValueLabel(Float.toString(statistics.getAverageEnergy()));
        Label averageAgeValueLabel = createValueLabel(Float.toString(statistics.getAverageAge()));

        HBox aliveAnimalsCountBox = createHBox(aliveAnimalsCountNameLabel,aliveAnimalsCountValueLabel);
        HBox plantsOnMapBox = createHBox(plantsOnMapNameLabel,plantsOnMapValueLabel);
        HBox emptyFieldsBox = createHBox(emptyFieldsNameLabel,emptyFieldsValueLabel);
        HBox popularGenotypeBox = createHBox(popularGenotypeNameLabel,popularGenotypeValueLabel);
        HBox averageEnergyBox = createHBox(averageEnergyNameLabel,averageEnergyValueLabel);
        HBox averageAgeBox = createHBox(averageAgeNameLabel,averageAgeValueLabel);

        getChildren().addAll(aliveAnimalsCountBox,plantsOnMapBox,emptyFieldsBox,popularGenotypeBox,averageEnergyBox,averageAgeBox);
    }
    public void setPresenter (StatisticsPresenter presenter) { this.presenter = presenter; }
    private Label createNameLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(200, HEIGHT);
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
    public void refreshView() {
        Platform.runLater(() -> {
            buildView();
            if (getScene() != null)  { getScene().getWindow().sizeToScene(); }
        });
    }
}
