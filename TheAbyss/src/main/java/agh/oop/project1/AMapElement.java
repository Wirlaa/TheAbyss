package agh.oop.project1; // Do zmiany

import java.util.Objects;

public abstract class AMapElement implements IMapElement {
    protected Vector2d position;
    protected MapDirection orientation;
    private int date;
    SimulationOptions simulationOptions;
    public Vector2d getPosition() {
        return position;
    }
    public MapDirection getOrientation(){
        return orientation;
    }
    public boolean isAt(Vector2d position){
        return Objects.equals(this.position, position);
    }
    public SimulationOptions getSimulationOptions() {
        return simulationOptions;
    }
}
