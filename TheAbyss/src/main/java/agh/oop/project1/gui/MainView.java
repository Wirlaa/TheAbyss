package agh.oop.project1.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainView extends BorderPane {
    static final int BUTTONWIDTH = 100;
    static final int BUTTONHEIGHT = 20;
    private MainPresenter presenter;
    public MainView() {
        buildView();
    }
    protected void buildView() {
        setStyle("-fx-background-color: #000000;");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.CENTER_LEFT);

        Button buttonStart = new Button("Start");
        buttonStart.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        buttonStart.setStyle("-fx-background-color: #006699; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        //buttonStart.setOnAction(event -> presenter.startView());

        Button buttonStop = new Button("Stop");
        buttonStop.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        buttonStop.setStyle("-fx-background-color: #990000; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        buttonStop.setOnAction(event -> presenter.stop());

        Button buttonSwitchAnimal = new Button("SwitchAnimal");
        buttonSwitchAnimal.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        buttonSwitchAnimal.setStyle("-fx-background-color: #009900; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        buttonSwitchAnimal.setOnAction(event -> presenter.switchAnimal());

        Button buttonOptions = new Button("Options");
        buttonOptions.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        buttonOptions.setStyle("-fx-background-color: #998800; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        buttonOptions.setOnAction(event -> presenter.switchOptions());

        hbox.getChildren().addAll(buttonStart, buttonStop, buttonSwitchAnimal, buttonOptions);
        setTop(hbox);
    }
    public void setPresenter (MainPresenter presenter) {
        this.presenter = presenter;
    }
    public void setContentCenter(Node content) {
        Platform.runLater(() -> {
            setCenter(content);
            getScene().getWindow().sizeToScene();
        });
    }
    public void setContentRight(Node content) {
        Platform.runLater(() -> {
            setRight(content);
            getScene().getWindow().sizeToScene();
        });
    }

}
