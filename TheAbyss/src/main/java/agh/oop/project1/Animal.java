package agh.oop.project1;

import java.sql.Array;
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

    public Animal(Vector2d initialPosition, IWorldMap map, ArrayList<Integer> genes, int initialEnergy, int birthDate, int minMutatedGenes, int maxMutatedGenes){
        Random rng = new Random();
        position = initialPosition;
        orientation = MapDirection.values()[rng.nextInt() & 8];
        this.map = map;
        energy = initialEnergy;
        mutateGenes(genes, minMutatedGenes, maxMutatedGenes, rng);
        this.genes = genes;
        this.activeGene = rng.nextInt() % genes.size();
        offspringCount = 0;
        this.birthDate = birthDate;
    }

    public Animal(Vector2d initialPosition, IWorldMap map, Integer geneCount, int initialEnergy, int birthDate){
        this(initialPosition, map, getGeneList(geneCount), initialEnergy, birthDate,0,0);
    }

    private static void mutateGenes(ArrayList<Integer> genes, int minMutatedGenes, int maxMutatedGenes, Random rng) {
        int mutatedGeneCount = rng.nextInt(minMutatedGenes, maxMutatedGenes + 1);
        ArrayList<Integer> potentialGenesToMutate = new ArrayList<>();
        for (int i = 0; i< genes.size(); i++) {
            potentialGenesToMutate.add(i);
        }
        Collections.shuffle(potentialGenesToMutate);
        for(int i=0; i<mutatedGeneCount; i++){
            genes.set(potentialGenesToMutate.get(i), rng.nextInt(0,8));
        }
    }

    private static ArrayList<Integer> getGeneList(Integer geneCount) {
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
}
