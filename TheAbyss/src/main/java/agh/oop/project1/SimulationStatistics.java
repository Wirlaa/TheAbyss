package agh.oop.project1;

import java.util.List;

public class SimulationStatistics implements IAnimalDeathObserver {
    private int aliveAnimalsCount = 0;
    private int plantsOnMap = 0;
    private int freeFields = 0;
    private List<Integer> theMostPopularGentype;
    private float averageEnergy = 0;
    private int deadAnimals;
    private int ageSum;

    IWorldMap map;

    public SimulationStatistics(){

    }

    public void setMap(IWorldMap map){
        this.map = map;
    }

    @Override
    public void animalDied(Animal animal) {
        aliveAnimalsCount--;
        deadAnimals++;
        ageSum += map.getDate() - animal.getBirthDate();
    }

    public void animalBorn(){
        aliveAnimalsCount++;
    }

    public void plantEaten(){
        plantsOnMap--;
    }

    public void plantPlanted(){
        plantsOnMap++;
    }

    public void setFreeFields(int i){
        freeFields = i;
    }

    public void setTheMostPopularGentype(List<Integer> theMostPopularGentype) {
        this.theMostPopularGentype = theMostPopularGentype;
    }

    public void setAverageEnergy(float i){
        averageEnergy = i;
    }

    public int getAliveAnimalsCount() {
        return aliveAnimalsCount;
    }

    public int getPlantsOnMap() {
        return plantsOnMap;
    }

    public int getFreeFields() {
        return freeFields;
    }

    public List<Integer> getTheMostPopularGentype() {
        return theMostPopularGentype;
    }

    public float getAverageEnergy() {
        return averageEnergy;
    }

    public float getAverageAge() {
        return ageSum/deadAnimals;
    }
}
