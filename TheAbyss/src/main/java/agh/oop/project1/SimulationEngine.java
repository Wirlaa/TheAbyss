package agh.oop.project1;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class SimulationEngine implements IEngine {
    private SimulationStatistics simStats;
    private final List<ISimulationChangeObserver> observers = new ArrayList<>();
    private SimulationOptions simulationOptions;
    private IPreferableFields preferableFields;
    private IWorldMap map;
    public Multimap<Vector2d, Animal> animals = HashMultimap.create();
    public SimulationEngine(IWorldMap map, SimulationOptions simulationOptions, SimulationStatistics simStats){
        Random rng = new Random();
        this.simulationOptions = simulationOptions;
        this.map = map;
        this.simStats = simStats;
        Animal animal;
        for (int i = 0; i < simulationOptions.initialAnimalCount(); i++) {
            animal = new Animal(new Vector2d(rng.nextInt(simulationOptions.mapWidth()), rng.nextInt(simulationOptions.mapHeight())),map,simulationOptions.genomeLength(), simulationOptions.startingEnergy());
            animal.addPositionChangeObserver(this);
            animals.put(animal.getPosition(), animal);
            map.placeAnimal(animal);
        }
    }
    public void pause() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void run() {
        MapVisualizer mapVisualizer = new MapVisualizer(map);
        System.out.println( mapVisualizer.draw(new Vector2d(0,0), map.getUpperRightBound()));
        simulationChanged();
        while(true){
            updateStats();
            killOrMoveAnimal();
            map.eatAndPlaceNewPlants();
            breedTheAnimals();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simulationChanged();
            //System.out.println(mapVisualizer.draw(new Vector2d(0, 0), map.getUpperRightBound()));
            map.stepDateUp();
        }
    }
    /*public void resume() {
        synchronized (Thread.currentThread()) {
            Thread.currentThread().notify();
        }
    }
    public void await() {
        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
    private void breedTheAnimals() {
        for (Vector2d position : animals.keySet()) {
            Object[] animalsToBreed = Utils.fightForYourDeath(animals.get(position)
                    .stream()
                    .filter(x -> {return x.getEnergy() >= simulationOptions.energyToReproduce();})
                    .toList(), 2)
                    .toArray();
            if (animalsToBreed.length == 2) {
                Animal newborn = new Animal((Animal) animalsToBreed[0], (Animal) animalsToBreed[1]);
                newborn.addPositionChangeObserver(this);
                animals.put(newborn.getPosition(), newborn);
                map.placeAnimal(newborn);
                simStats.animalBorn();
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

    private void updateStats(){
        int freeFields = 0;
        for (int i = 0; i < simulationOptions.mapWidth(); i++) {
            for (int j = 0; j < simulationOptions.mapHeight(); j++) {
                if(animals.get(new Vector2d(i, j)).isEmpty()) freeFields++;
            }
        }
        simStats.setFreeFields(freeFields);
        Set<List<Integer>> genotypes = new HashSet<>();
        Map<List<Integer>, Integer> numberOfGenotypes = new HashMap<>();
        for (Animal animal : animals.values()) {
            if(genotypes.contains(animal.getGenes().getGenes())){
                numberOfGenotypes.put(animal.getGenes().getGenes(), numberOfGenotypes.get(animal.getGenes().getGenes())+1);
            } else {
                numberOfGenotypes.put(animal.getGenes().getGenes(), 1);
                genotypes.add(animal.getGenes().getGenes());
            }
        }
        simStats.setTheMostPopularGentype(genotypes.stream()
                        .max(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return Integer.compare(numberOfGenotypes.get(o1), numberOfGenotypes.get(o2));
            }
        })
                        .orElse(null)
        );
        int sum = 0;
        for (Integer i :
                animals.values().stream()
                        .map(x -> x.getEnergy())
                        .toList()) {
            sum += i;
        }
        simStats.setAverageEnergy(
                sum /((float) animals.values().stream()
                        .map(x -> x.getEnergy())
                        .toList()
                        .size())
        );
    }
}
