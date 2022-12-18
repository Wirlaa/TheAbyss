package agh.oop.project1;

import java.util.List;

public class SimulationEngine {

    SimulationOptions simulationOptions;
    IWorldMap map;

    public List<Animal> animals;

    public SimulationEngine(SimulationOptions simulationOptions){
        this.simulationOptions = simulationOptions;
        map = new HellishGateMap(simulationOptions.mapWidth(), simulationOptions.mapHeight());
        // Ustawianie roślin
        // Ustawianie zwierząt
    }

    public void run(){
        // Pokaż mapę
        while(/*warunek na kontynuację symulacji*/){
            for (int i = 0; i< animals.size(); i++) {
                if(animals.get(i).getEnergy()<=0) {
                    //ew. zwiększ licznik śmierci na polu
                    //usuń zwierze z mapy
                    animals.remove(i);
                } else {
                    animals.get(i).executeGene();
                    animals.get(i).move();
                    if(animals.get(i).getPosition().x() < 0 || animals.get(i).getPosition().y() < 0 || /*animal poza górnym boundsem*/){
                        // tp go
                        animals.get(i).subtractEnergy(simulationOptions.reproductionCost());
                    }
                }
                // Jedzenie roślin
                // Rozmnażanie
                // Wzrost roślin
            }
            // Pokaż mapę
        }
    }
}
