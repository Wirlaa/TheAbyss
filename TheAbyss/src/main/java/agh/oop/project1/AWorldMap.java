package agh.oop.project1;

import com.google.common.collect.*;

public abstract class AWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Multimap<Vector2d, Animal> animals = HashMultimap.create();
    protected int date = 0;


    SimulationOptions simulationOptions;

    @Override
    public boolean placeAnimal(Animal animal){
        if(!isInBounds(animal.getPosition())) {
            throw new IllegalArgumentException(animal.getPosition() + " is not a valid place to place an animal");
        }
        animals.put(animal.getPosition(), animal);
        return true;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animalToRemove){
        animals.remove(oldPosition, animalToRemove);
        animals.put(newPosition, animalToRemove);
    }

    public SimulationOptions getSimulationOptions() {
        return simulationOptions;
    }

    public int getDate() {
        return date;
    }
    public void stepDateUp(){
        date += 1;
    }
}