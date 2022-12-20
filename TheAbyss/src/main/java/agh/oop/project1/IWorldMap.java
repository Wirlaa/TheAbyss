package agh.oop.project1;

import java.util.Collection;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(Vector2d position);

    /**
     * Place a animal on the map.
     *
     * @param animal
     *            The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    boolean place(Animal animal);
    boolean remove(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    default boolean isOccupied(Vector2d position){
        return (!animalsAt(position).isEmpty()) || (plantAt(position) != null);
    }

    void eatAndPlaceNewPlants();

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Animal or null if the position is not occupied.
     */
    Collection<Animal> animalsAt(Vector2d position);
    Plant plantAt(Vector2d position);
    Vector2d getNewPosition(Vector2d position, MapDirection orientation);
    MapDirection getNewOrientation(Vector2d position, MapDirection orientation);
    public SimulationOptions getSimulationOptions();
    public int getDate();
}