package agh.oop.project1;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Multimap<Vector2d, Animal> animals = HashMultimap.create();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected int date = 0;
    SimulationOptions simulationOptions;
    @Override
    public Multimap<Vector2d, Animal> getAnimals() { return ImmutableMultimap.copyOf(animals); }
    @Override
    public Collection<Animal> animalsAt(Vector2d position) {
        return animals.get(position);
    }
    @Override
    public Map<Vector2d, Plant> getPlants() { return Collections.unmodifiableMap(plants); }
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
    protected abstract void notifyAnimalDeathObservers(Animal animal);
    @Override
    public boolean killAnimal(Animal animal) {
        animal.setDeathDate(date);
        notifyAnimalDeathObservers(animal);
        return animals.remove(animal.getPosition(), animal);
    }
}