package agh.oop.project1;

import java.util.*;

public class HellishGateMap extends AWorldMap{

    SimulationStatistics simStats;
    IPreferableFields preferableFields;
    private static final Vector2d LOWER_BOUND = new Vector2d(0,0);
    private static Vector2d upper_bound;
    List<IAnimalDeathObserver> animalDeathObserverList = new ArrayList<>();
    public HellishGateMap(int width, int height, SimulationOptions simulationOptions, SimulationStatistics simStats){
        this.simStats = simStats;
        upper_bound = new Vector2d(width-1, height-1);
        this.simulationOptions = simulationOptions;
        if(simulationOptions.corpseToxicity()){
            preferableFields = new ToxicCorpsesPreferableFields(simulationOptions.mapWidth(), simulationOptions.mapHeight());
            animalDeathObserverList.add((IAnimalDeathObserver) preferableFields);
        } else {
            preferableFields = new EquatorPreferableFields(simulationOptions.mapWidth(), simulationOptions.mapHeight());
        }
        placeNPlants(simulationOptions.initialPlantCount());
    }
    private void placeNPlants(int n) {
        Random rng = new Random();
        List<Vector2d> betterFields = preferableFields.betterFields();
        Collections.shuffle(betterFields);
        List<Vector2d> worseFields = preferableFields.worseFields();
        Collections.shuffle(worseFields);
        for (int i = 0; i < n; i++) {
            if(rng.nextDouble(0,1) < 0.8){
                if (placeFromFirstElsePlaceFromSecond(betterFields, worseFields)) break;
            } else {
                if (placeFromFirstElsePlaceFromSecond(worseFields, betterFields)) break;
            }
        }
    }
    private boolean placeFromFirstElsePlaceFromSecond(List<Vector2d> first, List<Vector2d> second) {
        boolean found = false;
        for (Vector2d vector2d : first) {
            if (plantAt(vector2d) == null) {
                plants.put(vector2d, new Plant(vector2d));
                simStats.plantPlanted();
                found = true;
                break;
            }
        }
        if(!found){
            for (Vector2d vector2d : second) {
                if (plantAt(vector2d) == null) {
                    plants.put(vector2d, new Plant(vector2d));
                    simStats.plantPlanted();
                    found = true;
                    break;
                }
            }
            return !found;
        }
        return false;
    }
    @Override
    public boolean isInBounds(Vector2d dest) {
        return LOWER_BOUND.precedes(dest) && upper_bound.follows(dest);
    }

    @Override
    public void eatAndPlaceNewPlants() {
        for (Vector2d pos : animals.keySet()) {
            if(plants.get(pos) != null){
                Animal eatingAnimal = (Utils.fightForYourDeath(animals.get(pos), 1).size() == 1 ? Utils.fightForYourDeath(animals.get(pos), 1).get(0) : null);
                if (eatingAnimal != null){
                    plants.remove(pos);
                    simStats.plantEaten();
                    eatingAnimal.addEnergy(simulationOptions.energyFromOnePlant());
                    eatingAnimal.incrementPlantsEaten();
                }
            }
        }
        placeNPlants(simulationOptions.plantsGrowingEachDay());
    }
    @Override
    public Plant plantAt(Vector2d position) {
        return plants.get(position);
    }
    @Override
    public Vector2d getNewPosition(Vector2d position, MapDirection orientation) {
        Random rng = new Random();
        return new Vector2d(rng.nextInt(LOWER_BOUND.x(), upper_bound.x()), rng.nextInt(LOWER_BOUND.y(), upper_bound.y()));
    }
    @Override
    public MapDirection getNewOrientation(Vector2d position, MapDirection orientation) {
        return orientation;
    }
    @Override
    public Vector2d getUpperRightBound() {
        return upper_bound;
    }

    @Override
    public void addAnimalDeathObserver(IAnimalDeathObserver observer) {
        animalDeathObserverList.add(observer);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animalToRemove){
        animals.remove(oldPosition, animalToRemove);
        if(LOWER_BOUND.precedes(newPosition) && upper_bound.follows(newPosition)) {
            animals.put(newPosition, animalToRemove);
        } else {
            Random rng = new Random();
            animalToRemove.setPosition(new Vector2d(rng.nextInt(LOWER_BOUND.x(), upper_bound.x()+1), rng.nextInt(LOWER_BOUND.y(), upper_bound.y()+1)));
            animals.put(animalToRemove.getPosition(), animalToRemove);
        }
        animalToRemove.subtractEnergy(simulationOptions.reproductionCost());
    }
    protected void notifyAnimalDeathObservers(Animal animal){
        for (IAnimalDeathObserver i :
                animalDeathObserverList) {
            i.animalDied(animal);
        }
    }
    // huh? czemu sa dwie funkcje upperbound?
    // Nie wiem XD
    public static Vector2d getUpper_bound() {
        return upper_bound;
    }
    public void setSimStats(SimulationStatistics simStats) {
        this.simStats = simStats;
    }
}
