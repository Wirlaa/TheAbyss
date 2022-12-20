package agh.oop.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Genotype {

    private List<Integer> genes;
    private int activeGene;


    public Genotype(List<Integer> genes){
        Random rng = new Random();
        this.genes = genes;
        this.activeGene = rng.nextInt() % genes.size();
    }

    public Genotype(int geneCount){
        Random rng = new Random();
        genes = new ArrayList<>();
        for(int i = 0; i< geneCount; i++){
            genes.add(rng.nextInt(0,8));
        }
        this.activeGene = rng.nextInt(0,genes.size());
    }

    public Genotype(Animal animal1, Animal animal2) {
        Random rng = new Random();
        genes = new ArrayList<>();
        int parentEnergySum = animal1.getEnergy() + animal2.getEnergy();
        int genesFrom1 = animal1.getEnergy() / parentEnergySum * animal1.getGenes().size();
        int genesFrom2 = animal2.getGenes().size() - genesFrom1;
        if(rng.nextBoolean()){
            genes.addAll(animal1.getGenes().getGenes().subList(0,genesFrom1));
            genes.addAll(animal2.getGenes().getGenes().subList(genesFrom1, animal2.getGenes().size()));
        } else {
            genes.addAll(animal2.getGenes().getGenes().subList(0,genesFrom2));
            genes.addAll(animal1.getGenes().getGenes().subList(genesFrom2, animal1.getGenes().size()));
        }
    }

    public void mutateGenes(int minMutatedGenes, int maxMutatedGenes) {
        Random rng = new Random();
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


    public int size(){
        return genes.size();
    }

    public int getNext() {
        activeGene = (activeGene + 1) % genes.size();
        return genes.get(activeGene);
    }
    public List<Integer> getGenes() {
        return genes;
    }

}
