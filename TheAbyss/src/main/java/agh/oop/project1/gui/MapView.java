package agh.oop.project1.gui;

import agh.oop.project1.Animal;
import agh.oop.project1.IWorldMap;
import agh.oop.project1.Vector2d;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends GridPane {
    static final int WIDTH = 40;
    static final int HEIGHT = 40;
    private Node[][] vboxArray;
    private MapPresenter presenter;

    private static List<Image> bgImages = new ArrayList<Image>();
    private static List<Image> animalImages = new ArrayList<Image>();
    public MapView() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #333333");
        setHgap(1);
        setVgap(1);
        try {
            if(bgImages.isEmpty()) {
                bgImages.add(new Image(new FileInputStream("src/main/resources/images/bg.png")));
                bgImages.add(new Image(new FileInputStream("src/main/resources/images/bgWithPlant.png")));
            }
            if(animalImages.isEmpty()){
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/Animal0.png")));
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/Animal1.png")));
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/Animal2.png")));
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/Animal3.png")));
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/Animal4.png")));
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/Animal5.png")));
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/MultipleAnimals.png")));
                animalImages.add(new Image(new FileInputStream("src/main/resources/images/TrackedAnimal.png")));
            }
        } catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }
        //mapLayout();
        //placeElements();
    }
    protected void buildView() {
        Platform.runLater(() -> {
            getChildren().clear();
            mapLayout();
            placeElements();
        });
    }
    public void setPresenter (MapPresenter presenter) {
        this.presenter = presenter;
    }
    private void mapLayout() {
        int numOfColumns = presenter.getMainPresenter().getMap().getUpperRightBound().x();
        int numOfRows = presenter.getMainPresenter().getMap().getUpperRightBound().y();
        //vboxArray = new Node[numOfColumns+2][numOfRows+2]; //za mala tablica?

        add(createVbox(createLabel("y\\x")),0,0);

        for (int i = 0; i < numOfColumns + 1; i++) {
            add(createVbox(createLabel(Integer.toString(i))),i+1,0);
        }
        for (int i = 0; i < numOfRows + 1; i++) {
            add(createVbox(createLabel(Integer.toString(numOfRows - i))),0,i+1);
        }
        for (int i = 1; i < numOfColumns + 2; i++) {
            for (int j = 1; j < numOfRows + 2; j++) {
                VBox box = createVbox(createLabel(""));
                add(box,i,j);
                //vboxArray[i][j] = box;
            }
        }
    }
    private void placeElements(){
        //List<Vector2d> animalPositions = presenter.getMainPresenter().getMap().getAnimals().keys().stream().toList();
        //List<Vector2d> animalPositions = presenter.getMainPresenter().getMap().getAnimals().keys().stream().toList();
        List<Vector2d> positionsGrid = new ArrayList<>();
        for (int i = 1; i < presenter.getMainPresenter().getMap().getUpperRightBound().x() + 2; i++) {
            for (int j = 1; j < presenter.getMainPresenter().getMap().getUpperRightBound().y() + 2; j++) {
                positionsGrid.add(new Vector2d(i,j));
            }
        }
        /*
        Object object = this.map.animalsAt(currentPosition).isEmpty() ? this.map.plantAt(currentPosition) : this.map.animalsAt(currentPosition).toArray()[0];
            if (object != null) {
                result = object.toString();
            }
         */
        for (Vector2d gridPosition: positionsGrid) {/*
            Vector2d realPosition = new Vector2d(gridPosition.x()-1,presenter.getMainPresenter().getMap().getUpperRightBound().y()-gridPosition.y()+1);
            VBox box = null;
            if (!presenter.getMainPresenter().getMap().getAnimals().get(realPosition).isEmpty()) {
                Animal animal = presenter.getMainPresenter().getMap().animalsAt(realPosition).iterator().next();
                box = createVbox(createLabel(animal.toString()));
                box.setOnMouseClicked(event -> presenter.getMainPresenter().setTrackedAnimal(animal));
                int energy = presenter.getMainPresenter().getMap().animalsAt(realPosition).iterator().next().getEnergy();
                if (energy > 255) { energy = 255;}
                box.setStyle(String.format("-fx-background-color: #%s0000",String.format("%1$02X", energy)));
            } else if (presenter.getMainPresenter().getMap().plantAt(realPosition) != null) {
                //box = createVbox(createLabel(presenter.getMainPresenter().getMap().plantAt(realPosition).toString()));
                box = createVbox(createLabel(" "));
                box.setStyle("-fx-background-color: #119900");
            }
            if (box != null) {
                add(box, gridPosition.x(), gridPosition.y());
            }*/
            //vboxArray[position.x()-1][position.y()-1-presenter.getMainPresenter().getMap().getUpperRightBound().y()] = box;
            /*add(new GuiElementBox(element, element.getMapLabel()).getField(),
                    -map.getLowerBound().getX() + 1 + position.getX(),
                    map.getUpperBound().getY() + 1 - position.getY());*/
            Vector2d realPosition = new Vector2d(gridPosition.x()-1,presenter.getMainPresenter().getMap().getUpperRightBound().y()-gridPosition.y()+1);
            IWorldMap map = presenter.getMainPresenter().getMap();
            VBox box = new VBox();
            box.setBackground(new Background(new BackgroundImage(map.plantAt(realPosition) == null ? bgImages.get(0) : bgImages.get(1),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    new BackgroundPosition(Side.LEFT, WIDTH, false, Side.TOP, HEIGHT, false),
                    new BackgroundSize(
                            WIDTH, HEIGHT,
                            false, false,
                            true, true
                    )
            )));
            if (!map.animalsAt(realPosition).isEmpty() ) {
                ImageView imageView;
                Animal animal = (Animal) map.animalsAt(realPosition).toArray()[0];
                if (map.animalsAt(realPosition).size() == 1) {
                    imageView = new ImageView(animalImages.get(animal.getImageNumber(map.getMaxEnergy())));
                } else {
                    imageView = new ImageView(animalImages.get(6));
                }
                imageView.setFitHeight(HEIGHT);
                imageView.setFitWidth(WIDTH);
                box.getChildren().add(imageView);
                box.setAlignment(Pos.CENTER);
                box.setOnMouseClicked(event -> presenter.getMainPresenter().setTrackedAnimal(animal));
            }
            add(box,gridPosition.x(), gridPosition.y());
        }
        Animal animal = presenter.getMainPresenter().getTrackedAnimal();
        if (animal != null) {
            IWorldMap map = presenter.getMainPresenter().getMap();
            ImageView imageView = new ImageView(animalImages.get(7));
            imageView.setFitHeight(HEIGHT);
            imageView.setFitWidth(WIDTH);
            VBox box = new VBox(imageView);
            box.setAlignment(Pos.CENTER);
            box.setOnMouseClicked(event -> presenter.getMainPresenter().setTrackedAnimal(animal));
            box.getChildren().remove(box.getChildren().stream().filter(o -> o.getLayoutX() == animal.getPosition().x() && o.getLayoutY() == animal.getPosition().y()).findFirst().orElse(null));
            add(box, animal.getPosition().x() + 1, presenter.getMainPresenter().getMap().getUpperRightBound().y() - animal.getPosition().y() + 1);
        }
        /*if (animal != null) {
            VBox box = createVbox(createLabel(animal.toString()));
            box.setStyle("-fx-background-color: #BB00DD");
            add(box, animal.getPosition().x() + 1, presenter.getMainPresenter().getMap().getUpperRightBound().y() - animal.getPosition().y() + 1);
        }*/
    }
    private VBox createVbox(Label label) {
        VBox vbox = new VBox(label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(WIDTH,HEIGHT);
        vbox.setMaxHeight(HEIGHT);
        vbox.setMinHeight(HEIGHT);
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
