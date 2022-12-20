package agh.oop.project1;

import java.util.*;
import java.util.stream.Stream;

public class HellishGateMap extends AWorldMap{
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    IPreferableFields preferableFields;

    public static Vector2d getUpper_bound() {
        return upper_bound;
    }

    private static final Vector2d LOWER_BOUND = new Vector2d(0,0);
    private static Vector2d upper_bound;

    public HellishGateMap(int width, int height, SimulationOptions simulationOptions, IPreferableFields preferableFields){
        upper_bound = new Vector2d(width-1, height-1);
        this.simulationOptions = simulationOptions;
        this.preferableFields = preferableFields;
        // Ustawianie roślin
    }
    @Override
    public boolean canMoveTo(Vector2d dest) {
        return LOWER_BOUND.precedes(dest) && upper_bound.follows(dest);
    }

    @Override
    public boolean remove(Animal animal) {
        return animals.remove(animal.getPosition(), animal);
    }

    @Override
    public void eatAndPlaceNewPlants() {
        // Do napisania
    }

    @Override
    public Collection<Animal> animalsAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public Plant plantAt(Vector2d position) {
        return plants.get(position);
    }

    @Override
    public Vector2d getNewPosition(Vector2d position, MapDirection orientation) {
        Random rng = new Random();
        return new Vector2d(rng.nextInt(LOWER_BOUND.x(), upper_bound.x()+1), rng.nextInt(LOWER_BOUND.y(), upper_bound.y()+1));
    }

    @Override
    public MapDirection getNewOrientation(Vector2d position, MapDirection orientation) {
        return orientation;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animalToRemove){
        animals.remove(oldPosition, animalToRemove);
        if(LOWER_BOUND.precedes(newPosition) && upper_bound.follows(newPosition)) {
            animals.put(newPosition, animalToRemove);
        } else {
            Random rng = new Random();
            animalToRemove.setPosition(new Vector2d(rng.nextInt(LOWER_BOUND.x(), upper_bound.x() +1), rng.nextInt(LOWER_BOUND.x(), upper_bound.y())+1));
        }
        animalToRemove.subtractEnergy(simulationOptions.reproductionCost());
    }
}
