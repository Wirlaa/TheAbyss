package agh.oop.project1.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class GuiElementBox {
    private final VBox field;
    public GuiElementBox (IMapElement element, Image image) {
        int width = 30;
        int height = 30;
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        this.field = new VBox(imageView, new Label(element.getDescription()));
        this.field.setAlignment(Pos.CENTER);
    }

    public VBox getField() {
        return field;
    }
}
