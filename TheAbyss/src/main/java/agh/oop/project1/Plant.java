package agh.oop.project1;

public class Plant extends AMapElement {
    public Plant(Vector2d position){
        this.position = position;
    }
    @Override
    public String toString() {
        return "* ";
    }
    //@Override
    /*public String getImagePath() {
        return "src/main/resources/plant.png";
    }*/
    @Override
    public String getMapLabel() {
        return "Roślinka";
    }
}
