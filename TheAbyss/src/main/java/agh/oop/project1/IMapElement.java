package agh.oop.project1; // Do zmiany

public interface IMapElement {
    Vector2d getPosition();
    boolean isAt(Vector2d position);
    //public String getImagePath();
    String getMapLabel();
}
