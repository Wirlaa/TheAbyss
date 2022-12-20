package agh.oop.project1;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationEngine implements IPositionChangeObserver {

    private SimulationOptions simulationOptions;
    private IPreferableFields preferableFields;
    private IWorldMap map;

    public Multimap<Vector2d, Animal> animals = HashMultimap.create();

    public SimulationEngine(IWorldMap map, SimulationOptions simulationOptions){
        Random rng = new Random();
        this.simulationOptions = simulationOptions;
        this.map = map;
        Animal animal;
        for (int i = 0; i < simulationOptions.startingAnimalsCount(); i++) {
            animal = new Animal(new Vector2d(rng.nextInt(simulationOptions.mapWidth()), rng.nextInt(simulationOptions.mapHeight())),map,simulationOptions.genomeLength(), simulationOptions.startingEnergy());
            animal.addPositionChangeObserver(this);
            animals.put(animal.getPosition(), animal);
            map.placeAnimal(animal);
        }
    }

    public void run() throws InterruptedException {
        MapVisualizer mapVisualizer = new MapVisualizer(map);
        System.out.println( mapVisualizer.draw(new Vector2d(0,0), map.getUpperRightBound()));
        while(true){
            List<Animal> animalsList = animals.values().stream().toList();
            for (Animal animal : animalsList) {
                if (animal.getEnergy() <= 0) {
                    map.killAnimal(animal);
                    animals.remove(animal.getPosition(), animal);
                } else {
                    animal.executeGene();
                    animal.move();
                }
                System.out.println( mapVisualizer.draw(new Vector2d(0,0), map.getUpperRightBound()));
                Thread.sleep(2000);
            }
            map.eatAndPlaceNewPlants();
            for (Vector2d j : animals.keySet()) {
                Object[] animalsToBreed = Animal.fightForYourDeath(animals.get(j), 2).toArray();
                if (animalsToBreed.length == 2) {
                    Animal newborn = new Animal((Animal) animalsToBreed[0], (Animal) animalsToBreed[1]);
                    newborn.addPositionChangeObserver(this);
                    animals.put(newborn.getPosition(), newborn);
                    map.placeAnimal(newborn);
                }
            }
            // Wait
            System.out.println( mapVisualizer.draw(new Vector2d(0,0), map.getUpperRightBound()));
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        animals.remove(oldPosition,animal);
        animals.put(newPosition, animal);
    }
}
