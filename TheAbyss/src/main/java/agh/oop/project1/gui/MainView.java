package agh.oop.project1.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainView extends BorderPane {
    static final int BUTTONWIDTH = 100;
    static final int BUTTONHEIGHT = 20;
    private MainPresenter presenter;
    public MainView() { buildView(); }
    protected void buildView() {
        setStyle("-fx-background-color: #000000;");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.CENTER_LEFT);

        Button pauseButton = new Button("Pause");
        pauseButton.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        pauseButton.setStyle("-fx-background-color: #990000; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        pauseButton.setOnAction(event -> presenter.pause());

        Button resumeButton = new Button("Resume");
        resumeButton.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        resumeButton.setStyle("-fx-background-color: #006699; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        resumeButton.setOnAction(event -> presenter.resume());

        Button optionsButton = new Button("Options");
        optionsButton.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);
        optionsButton.setStyle("-fx-background-color: #998800; -fx-text-fill: #CCCCCC; -fx-font-weight: bold");
        optionsButton.setOnAction(event -> presenter.switchOptions());

        hbox.getChildren().addAll(pauseButton, resumeButton, optionsButton);
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
