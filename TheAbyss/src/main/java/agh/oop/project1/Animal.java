package agh.oop.project1;

import java.util.*;

public class Animal extends AMapElement {
    private IWorldMap map;
    private final List<IPositionChangeObserver> positionChangeObservers = new ArrayList<>();
    private Genotype genes;
    private int energy;

    private int offspringCount;

    private int birthDate;

    public Animal(Vector2d initialPosition, IWorldMap map, Genotype genes, int initialEnergy){
        Random rng = new Random();
        position = initialPosition;
        orientation = MapDirection.values()[rng.nextInt() & 8];
        this.map = map;
        energy = initialEnergy;
        genes.mutateGenes(map.getSimulationOptions().minMutatedGenes(), map.getSimulationOptions().maxMutatedGenes());
        this.genes = genes;
        offspringCount = 0;
        this.birthDate = map.getDate();
    }

    public Animal(Vector2d initialPosition, IWorldMap map, Integer geneCount, int initialEnergy){
        this(initialPosition, map, new Genotype(geneCount), initialEnergy);
    }

    public Animal(Animal animal1, Animal animal2){
        Random rng = new Random();
        position = animal1.getPosition();
        orientation = MapDirection.values()[rng.nextInt() & 8];
        this.map = animal1.getmap();
        genes = new Genotype(animal1, animal2);
        genes.mutateGenes(map.getSimulationOptions().minMutatedGenes(), map.getSimulationOptions().maxMutatedGenes());
        animal1.subtractEnergy(map.getSimulationOptions().reproductionCost());
        animal2.subtractEnergy(map.getSimulationOptions().reproductionCost());
        energy = 2 * map.getSimulationOptions().reproductionCost();
        offspringCount = 0;
        this.birthDate = map.getDate();
    }

    @Override
    public String toString(){
        return (orientation.toString());
    }

    public void executeGene(){
        orientation = orientation.rotate(genes.getNext());
    }

    public void move(){
        Vector2d oldPosition = position;
        position = position.add(orientation.toUnitVector());
        positionChanged(oldPosition, position);
    }

    public void addPositionChangeObserver(IPositionChangeObserver observer){
        positionChangeObservers.add(observer);
    }

    public void removePositionChangeObserver(IPositionChangeObserver observer){
        positionChangeObservers.removeIf(i -> Objects.equals(observer, i));
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        ((IPositionChangeObserver) map).positionChanged(oldPosition, newPosition, this);
        for (IPositionChangeObserver i : positionChangeObservers) {
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

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    private IWorldMap getmap() {
        return map;
    }

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

    public Genotype getGenes() {
        return genes;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public int getOffspringCount() {
        return offspringCount;
    }

    public static List<Animal> fightForYourDeath(Collection<Animal> animals, int n){
        return animals.stream()
                .sorted(new Comparator<Animal>() {
                    @Override
                    public int compare(Animal o1, Animal o2) {
                        if(o1.getEnergy() != o2.getEnergy()){
                            return -Integer.compare(o1.getEnergy(), o2.getEnergy());
                        } else if (o1.getBirthDate() != o2.getBirthDate()) {
                            return -Integer.compare(o2.getBirthDate(), o1.getBirthDate());
                        } else if (o1.getOffspringCount() != o2.getOffspringCount()) {
                            return -Integer.compare(o1.getOffspringCount(), o2.getOffspringCount());
                        }
                        else return (new Random()).nextInt(-1,2);
                    }
                })
                .limit(n)
                .toList();
    }
}
