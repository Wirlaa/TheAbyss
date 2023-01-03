package agh.oop.project1.gui;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MapView extends GridPane {
    static final int WIDTH = 10;
    static final int HEIGHT = 10;
    private MapPresenter presenter;
    public MapView() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #333333");
        setHgap(1);
        setVgap(1);
        //mapLayout();
        //placeElements();
    }
    protected void buildView() {
        Platform.runLater(() -> {
            getChildren().clear();
            mapLayout();
            //placeElements();
        });
    }
    public void setPresenter (MapPresenter presenter) {
        this.presenter = presenter;
    }
    private void mapLayout() {
        int numOfColumns = presenter.getMainPresenter().getMap().getUpperRightBound().x();
        int numOfRows = presenter.getMainPresenter().getMap().getUpperRightBound().y();

        add(createVbox(createLabel("y\\x")),0,0);

        for (int i = 0; i < numOfColumns + 1; i++) {
            add(createVbox(createLabel(Integer.toString(i))),i+1,0);
        }
        for (int i = 0; i < numOfRows + 1; i++) {
            add(createVbox(createLabel(Integer.toString(numOfRows - i))),0,i+1);
        }
        for (int i = 0; i < numOfColumns + 1; i++) {
            for (int j = 0; j < numOfRows + 1; j++) {
                add(createVbox(createLabel("")),i+1,j+1);
            }
        }
    }
    /*private void placeElements(){
        for (Vector2d position: map.getElements().keySet()) {
            IMapElement element = map.objectAt(position);
            add(new GuiElementBox(element, element.getMapLabel()).getField(),
                    -map.getLowerBound().getX() + 1 + position.getX(),
                    map.getUpperBound().getY() + 1 - position.getY());
        }
    }*/
    private VBox createVbox(Label label) {
        VBox vbox = new VBox(label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinSize(WIDTH,HEIGHT);
        vbox.setStyle("-fx-background-color: #EEEEEE");
        GridPane.setHalignment(label, HPos.CENTER);
        return vbox;
    }
    private Label createLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        return label;
    }
}
