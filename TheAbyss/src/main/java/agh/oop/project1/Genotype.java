package agh.oop.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Genotype {
    private final List<Integer> genes;
    private int activeGene;

    public Genotype(int geneCount){
        Random rng = new Random();
        genes = new ArrayList<>();
        for(int i = 0; i< geneCount; i++){
            genes.add(rng.nextInt(0,8));
        }
        this.activeGene = rng.nextInt(0,genes.size());
    }

    public Genotype(Genotype genotype1, Genotype genotype2, int ratio1, int ratio2) {
        Random rng = new Random();
        genes = new ArrayList<>();
        int ratioSum = ratio1 + ratio2;
        if(ratioSum == 0)
            System.out.println("Meh");
        int genesFrom1 = ratio1 / ratioSum * genotype1.getGenes().size();
        int genesFrom2 = genotype2.getGenes().size() - genesFrom1;
        if(rng.nextBoolean()){
            genes.addAll(genotype1.getGenes().subList(0,genesFrom1));
            genes.addAll(genotype2.getGenes().subList(genesFrom1, genotype2.getGenes().size()));
        } else {
            genes.addAll(genotype2.getGenes().subList(0,genesFrom2));
            genes.addAll(genotype1.getGenes().subList(genesFrom2, genotype1.getGenes().size()));
        }
    }
    public int getActiveGene() { return activeGene; }
    public List<Integer> getGenes() {
        return genes;
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
    @Override
    public String toString() { return genes.toString(); }
}
