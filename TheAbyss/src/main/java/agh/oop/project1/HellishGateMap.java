package agh.oop.project1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Stream;

public class HellishGateMap extends AWorldMap{

    private static final Vector2d LOWER_BOUND = new Vector2d(0,0);
    private static Vector2d upper_bound;

    public HellishGateMap(int width, int height){
        upper_bound = new Vector2d(width-1, height-1);
    }
    @Override
    public boolean canMoveTo(Vector2d dest) {
        return LOWER_BOUND.precedes(dest) && upper_bound.follows(dest);
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
}
