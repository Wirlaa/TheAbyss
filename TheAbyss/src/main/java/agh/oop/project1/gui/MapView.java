package agh.oop.project1.gui;

//import agh.ics.oop.AWorldMap;
//import agh.ics.oop.IMapElement;
//import agh.ics.oop.Vector2d;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapView extends GridPane {
    static final int WIDTH = 48;
    static final int HEIGHT = 48;
    private MapPresenter presenter;
    //private AWorldMap map;
    private final Map<String, Image> images = new HashMap<>();
    public MapView() {
        //this.map = map;
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
            //mapLayout();
            //placeElements();
        });
    }
    public void setPresenter (MapPresenter presenter) {
        this.presenter = presenter;
    }
    /*private void mapLayout() {
        int numOfColumns = map.getUpperBound().getX() - map.getLowerBound().getX();
        int numOfRows = map.getUpperBound().getY() - map.getLowerBound().getY();

        add(createVbox(createLabel("y\\x")),0,0);

        for (int i = 0; i < numOfColumns + 1; i++) {
            add(createVbox(createLabel(Integer.toString(map.getLowerBound().getX() + i))),i+1,0);
        }
        for (int i = 0; i < numOfRows + 1; i++) {
            add(createVbox(createLabel(Integer.toString(map.getUpperBound().getY() - i))),0,i+1);
        }
        for (int i = 0; i < numOfColumns + 1; i++) {
            for (int j = 0; j < numOfRows + 1; j++) {
                add(createVbox(createLabel("")),i+1,j+1);
            }
        }
    }
    private void placeElements(){
        for (Vector2d position: map.getElements().keySet()) {
            IMapElement element = map.objectAt(position);
            add(new GuiElementBox(element, getImage(element)).getField(),
                    -map.getLowerBound().getX() + 1 + position.getX(),
                    map.getUpperBound().getY() + 1 - position.getY());
        }
    }
    private Image getImage(IMapElement element) {
        if (images.containsKey(element.getImageName())) {
            return images.get(element.getImageName());
        }
        else {
            try (FileInputStream file = new FileInputStream(element.getImageName()))
            {
                Image image = new Image(file);
                images.put(element.getImageName(), image);
                return image;
            }
            catch (IOException exception) {
                exception.printStackTrace();
                return null;
            }
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
