package agh.oop.project1;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationEngine implements IEngine {
    private final List<ISimulationChangeObserver> observers = new ArrayList<>();
    private SimulationOptions simulationOptions;
    private IPreferableFields preferableFields;
    private IWorldMap map;
    public Multimap<Vector2d, Animal> animals = HashMultimap.create();
    public SimulationEngine(IWorldMap map, SimulationOptions simulationOptions){
        Random rng = new Random();
        this.simulationOptions = simulationOptions;
        this.map = map;
        Animal animal;
        for (int i = 0; i < simulationOptions.initialAnimalCount(); i++) {
            animal = new Animal(new Vector2d(rng.nextInt(simulationOptions.mapWidth()), rng.nextInt(simulationOptions.mapHeight())),map,simulationOptions.genomeLength(), simulationOptions.startingEnergy());
            animal.addPositionChangeObserver(this);
            animals.put(animal.getPosition(), animal);
            map.placeAnimal(animal);
        }
    }
    public synchronized void pause() {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        MapVisualizer mapVisualizer = new MapVisualizer(map);
        System.out.println( mapVisualizer.draw(new Vector2d(0,0), map.getUpperRightBound()));
        simulationChanged();
        while(true){
            killOrMoveAnimal();
            map.eatAndPlaceNewPlants();
            breedTheAnimals();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simulationChanged();
            System.out.println(mapVisualizer.draw(new Vector2d(0,0), map.getUpperRightBound()));
            map.stepDateUp();
        }
    }
    private void breedTheAnimals() {
        for (Vector2d j : animals.keySet()) {
            Object[] animalsToBreed = Utils.fightForYourDeath(animals.get(j)
                    .stream()
                    .filter(x -> {return x.getEnergy() >= simulationOptions.energyToReproduce();})
                    .toList(), 2)
                    .toArray();
            if (animalsToBreed.length == 2) {
                Animal newborn = new Animal((Animal) animalsToBreed[0], (Animal) animalsToBreed[1]);
                newborn.addPositionChangeObserver(this);
                animals.put(newborn.getPosition(), newborn);
                map.placeAnimal(newborn);
            }
        }
    }
    private void killOrMoveAnimal() {
        List<Animal> animalsList = animals.values().stream().toList();
        for (Animal animal : animalsList) {
            animal.subtractEnergy(1);
            if (animal.getEnergy() <= 0) {
                map.killAnimal(animal);
                animals.remove(animal.getPosition(), animal);
            } else {
                animal.executeGene();
                animal.move();
            }
        }
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        animals.remove(oldPosition,animal);
        animals.put(newPosition, animal);
    }
    public void addObserver (ISimulationChangeObserver observer) {
        observers.add(observer);
    }
    public void removeObserver (ISimulationChangeObserver observer) {
        observers.remove(observer);
    }
    public void simulationChanged() {
        for (ISimulationChangeObserver observer: observers) {
            observer.simulationChanged();
        }
    }
}
