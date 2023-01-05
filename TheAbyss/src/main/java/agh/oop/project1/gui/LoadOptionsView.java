package agh.oop.project1.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoadOptionsView extends VBox {
    public static final int HEIGHT = 25;
    private LoadOptionsPresenter presenter;
    private final ToggleGroup group = new ToggleGroup();
    public LoadOptionsView() {
        buildView();
    }
    protected void buildView() {
        setStyle("-fx-background-color: #333333;");
        setPadding(new Insets(15, 15, 15, 15));
        setSpacing(10);
        setAlignment(Pos.CENTER);

        String config1String = "First configuration";
        String config2String = "Second configuration";
        String config3String = "Third configuration";

        Label config1NameLabel = createNameLabel(config1String);
        Label config2NameLabel = createNameLabel(config2String);
        Label config3NameLabel = createNameLabel(config3String);

        RadioButton config1RButton = new RadioButton();
        config1RButton.setToggleGroup(group);
        config1RButton.setOnAction(event -> presenter.loadConfiguration("src/main/resources/configuration1"));
        RadioButton config2RButton = new RadioButton();
        config2RButton.setToggleGroup(group);
        config2RButton.setOnAction(event -> presenter.loadConfiguration("src/main/resources/configuration2"));
        RadioButton config3RButton = new RadioButton();
        config3RButton.setToggleGroup(group);
        config3RButton.setOnAction(event -> presenter.loadConfiguration("src/main/resources/configuration3"));

        HBox config1Box = createHBox(config1NameLabel,config1RButton);
        HBox config2Box = createHBox(config2NameLabel,config2RButton);
        HBox config3Box = createHBox(config3NameLabel,config3RButton);

        getChildren().clear();
        getChildren().addAll(config1Box,config2Box,config3Box);
    }
    public void setPresenter (LoadOptionsPresenter presenter) {
        this.presenter = presenter;
    }
    private Label createNameLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(150, HEIGHT);
        label.setStyle("-fx-background-color: #115522; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        return label;
    }
    private HBox createHBox(Label name, RadioButton button) {
        HBox hbox = new HBox(name, button);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
}
