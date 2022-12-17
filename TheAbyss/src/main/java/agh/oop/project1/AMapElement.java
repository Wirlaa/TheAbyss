package agh.oop.project1; // Do zmiany

import java.util.Objects;

public abstract class AMapElement implements IMapElement {
    protected Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    public boolean isAt(Vector2d position){
        return Objects.equals(this.position, position);
    }
}
