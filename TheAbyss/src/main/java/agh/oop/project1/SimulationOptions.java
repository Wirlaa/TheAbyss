package agh.oop.project1;

public record SimulationOptions(int mapWidth,
                                int mapHeight,
                                int startingPlantsCount,
                                int energyFromOnePlant,
                                int plantsGrowingEachDay,
                                boolean areDeadAnimalsToxic,
                                int startingAnimalsCount,
                                int startingEnergy,
                                int minEnergyToReproduce,
                                int reproductionCost,
                                int minMutatedGenes,
                                int maxMutatedGenes,
                                int genomeLength
) {

}
