package agh.oop.project1;

import java.util.List;

public class SimulationStatistics implements IAnimalDeathObserver {
    private int aliveAnimalsCount = 0;
    private final int initialAnimalCount;
    private int plantsOnMap = 0;
    private int freeFields = 0;
    private List<Integer> theMostPopularGenotype;
    private float averageEnergy = 0;
    private int deadAnimals;
    private int ageSum;
    IWorldMap map;
    public SimulationStatistics(int initialAnimalCount){
        this.initialAnimalCount = initialAnimalCount;
    }
    public void setMap(IWorldMap map){ this.map = map; }
    @Override
    public void animalDied(Animal animal) {
        aliveAnimalsCount--;
        deadAnimals++;
        ageSum += map.getDate() - animal.getBirthDate();
    }
    public void animalBorn(){ aliveAnimalsCount++; }
    public void plantEaten(){ plantsOnMap--; }
    public void plantPlanted(){ plantsOnMap++; }
    public void setFreeFields(int i){ freeFields = i; }
    public void setTheMostPopularGenotype(List<Integer> theMostPopularGenotype) { this.theMostPopularGenotype = theMostPopularGenotype; }
    public void setAverageEnergy(float i){ averageEnergy = i; }
    public int getAliveAnimalsCount() { return aliveAnimalsCount + initialAnimalCount; }
    public int getPlantsOnMap() { return plantsOnMap; }
    public int getFreeFields() { return freeFields; }
    public List<Integer> getTheMostPopularGenotype() { return theMostPopularGenotype;}
    public float getAverageEnergy() { return averageEnergy; }
    public float getAverageAge() { return ageSum/((float)(deadAnimals == 0 ? 1 : deadAnimals)); }
    public void setAliveAnimalsCount(int aliveAnimalsCount) { this.aliveAnimalsCount = aliveAnimalsCount; }
}
