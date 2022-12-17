package agh.oop.project1;

import com.google.common.collect.*;

import javax.annotation.CheckForNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Multimap<Vector2d, Animal> animals = HashMultimap.create();
    protected Map<Vector2d, Plant> plants = new HashMap<>();


    @Override
    public boolean place(Animal animal){
        if(!canMoveTo(animal.getPosition())) {
            throw new IllegalArgumentException(animal.getPosition() + " is not a valid place to place an animal");
        }
        animals.put(animal.getPosition(), animal);
        animal.addObserver(this);
        return true;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animalToRemove){
        animals.remove(oldPosition, animalToRemove);
        animals.put(newPosition, animalToRemove);
    }
}