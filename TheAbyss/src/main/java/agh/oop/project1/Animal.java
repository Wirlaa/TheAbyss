package agh.oop.project1;

import java.util.*;

public class Animal extends AMapElement {
    private MapDirection orientation;
    private IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    private ArrayList<Integer> genes;
    private int activeGene;
    private int energy;
    private int offspringCount;
    private int birthDate;
    private SimulationOptions simulationOptions;

    public Animal(Vector2d initialPosition, IWorldMap map, ArrayList<Integer> genes, int initialEnergy, int birthDate, SimulationOptions simulationOptions){
        Random rng = new Random();
        position = initialPosition;
        orientation = MapDirection.values()[rng.nextInt() & 8];
        this.map = map;
        energy = initialEnergy;
        genes = mutateGenes(genes, simulationOptions.minMutatedGenes(), simulationOptions.maxMutatedGenes(), rng);
        this.genes = genes;
        this.activeGene = rng.nextInt() % genes.size();
        offspringCount = 0;
        this.birthDate = birthDate;
        this.simulationOptions = simulationOptions;
    }

    public Animal(Vector2d initialPosition, IWorldMap map, Integer geneCount, int initialEnergy, int birthDate, SimulationOptions simulationOptions){
        this(initialPosition, map, generateGeneList(geneCount), initialEnergy, birthDate, simulationOptions);
    }

    public Animal(Animal animal1, Animal animal2, int birthDate, SimulationOptions simulationOptions){
        Random rng = new Random();
        position = animal1.getPosition();
        orientation = MapDirection.values()[rng.nextInt() & 8];
        this.map = animal1.getmap();
        genes = genesCrossOver(animal1, animal2, rng);
        genes = mutateGenes(genes, simulationOptions.minMutatedGenes(), simulationOptions.maxMutatedGenes(), rng);
        animal1.subtractEnergy(simulationOptions.reproductionCost());
        animal2.subtractEnergy(simulationOptions.reproductionCost());
        energy = 2 * simulationOptions.reproductionCost();
        offspringCount = 0;
        activeGene = 0;
        this.birthDate = birthDate;
        this.simulationOptions = simulationOptions;
    }

    private ArrayList<Integer> genesCrossOver(Animal animal1, Animal animal2, Random rng) {
        ArrayList<Integer> genes = new ArrayList<>();
        int parentEnergySum = animal1.getEnergy() + animal2.getEnergy();
        int genesFrom1 = animal1.getEnergy() / parentEnergySum * animal1.getGenes().size();
        int genesFrom2 = animal1.getGenes().size() - genesFrom1;
        if(rng.nextBoolean()){
            genes.addAll(animal1.getGenes().subList(0,genesFrom1));
            genes.addAll(animal2.getGenes().subList(genesFrom1, animal2.getGenes().size()));
        } else {
            genes.addAll(animal2.getGenes().subList(0,genesFrom2));
            genes.addAll(animal1.getGenes().subList(genesFrom2, animal1.getGenes().size()));
        }
        return genes;
    }

    private static ArrayList<Integer> mutateGenes(ArrayList<Integer> genes, int minMutatedGenes, int maxMutatedGenes, Random rng) {
        int mutatedGeneCount = rng.nextInt(minMutatedGenes, maxMutatedGenes + 1);
        ArrayList<Integer> potentialGenesToMutate = new ArrayList<>();
        for (int i = 0; i< genes.size(); i++) {
            potentialGenesToMutate.add(i);
        }
        Collections.shuffle(potentialGenesToMutate);
        for(int i=0; i<mutatedGeneCount; i++){
            genes.set(potentialGenesToMutate.get(i), rng.nextInt(0,8));
        }
        return genes;
    }

    private IWorldMap getmap() {
        return map;
    }

    private static ArrayList<Integer> generateGeneList(Integer geneCount) {
        Random RNG = new Random();
        ArrayList<Integer> genes = new ArrayList<>(geneCount);
        for(int i = 0; i< geneCount; i++){
            genes.add(RNG.nextInt() & 8);
        }
        return genes;
    }

    public MapDirection getOrientation(){
        return orientation;
    }

    @Override
    public String toString(){
        return (orientation.toString());
    }

    public void executeGene(){
        activeGene = (activeGene + 1) % genes.size();
        orientation = orientation.rotate(genes.get(activeGene));
    }

    public void move(){
        Vector2d newPosition = position.add(orientation.toUnitVector());
        if(!map.canMoveTo(newPosition)){
            newPosition = map.getNewPosition(position, orientation);
            orientation = map.getNewOrientation(position, orientation);
        }
        positionChanged(position, newPosition);
        position = newPosition;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.removeIf(i -> Objects.equals(observer, i));
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver i : observers) {
            i.positionChanged(oldPosition, newPosition, this);
        }
    }

/*    public String getImagePath(){
        return switch (orientation){
            case NORTH -> "src/main/resources/animalN.png";
            case SOUTH -> "src/main/resources/animalS.png";
            case WEST -> "src/main/resources/animalW.png";
            case EAST -> "src/main/resources/animalE.png";
        };
    }*/

    @Override
    public String getMapLabel() {
        return position.toString();
    }

    public void addEnergy(int energy){ // Ew. z limitem jeśli pan nam tak powie
        this.energy += energy;
    }

    public void subtractEnergy(int energy){
        this.energy -= energy;
    }

    public int getEnergy() {
        return energy;
    }

    public void incrementOffspringCount() {
        this.offspringCount += 1;
    }

    public ArrayList<Integer> getGenes() {
        return genes;
    }
}
