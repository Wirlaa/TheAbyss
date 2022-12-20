package agh.oop.project1;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {

    private SimulationOptions simulationOptions;
    private IPreferableFields preferableFields;
    private IWorldMap map;

    public Multimap<Vector2d, Animal> animals = HashMultimap.create();
    List<IAnimalDeathObserver> animalDeathObserverList = new ArrayList<>();

    public SimulationEngine(SimulationOptions simulationOptions){
        this.simulationOptions = simulationOptions;
        if(simulationOptions.areDeadAnimalsToxic()){
            preferableFields = new ToxicCorsesPreferableFields(simulationOptions.mapWidth(), simulationOptions.mapHeight());
            animalDeathObserverList.add((IAnimalDeathObserver) preferableFields);
        } else {
            preferableFields = new EquatorPreferableFields(simulationOptions.mapWidth(), simulationOptions.mapHeight());
        }
        map = new HellishGateMap(simulationOptions.mapWidth(), simulationOptions.mapHeight(), simulationOptions, preferableFields);
        // Ustawianie zwierząt
    }

    public void run(){
        // Pokaż mapę
        while(/*warunek na kontynuację symulacji*/){
            List<Animal> animalsList = animals.values().stream().toList();
            for (int i = 0; i< animalsList.size(); i++) {
                if(animalsList.get(i).getEnergy()<=0) {
                    notifyAnimalDeathObservers(animalsList.get(i));
                    map.remove(animalsList.get(i));
                    animals.remove(animalsList.get(i).getPosition(), animalsList.get(i));
                } else {
                    animalsList.get(i).executeGene();
                    animalsList.get(i).move();
                }
                map.eatAndPlaceNewPlants();
                for (Vector2d j: animals.keySet()) {
                    Animal[] animalsToBreed = (Animal[]) Animal.fightForYourDeath(animals.get(j), 2).toArray();
                    Animal newborn = new Animal(animalsToBreed[0], animalsToBreed[1]);
                    animals.put(newborn.getPosition(), newborn);
                    map.place(newborn);
                }
            }
            // Pokaż mapę
        }
    }

    private void notifyAnimalDeathObservers(Animal animal){
        for (IAnimalDeathObserver i :
                animalDeathObserverList) {
            i.animalDied(animal);
        }
    }
}
