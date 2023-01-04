package agh.oop.project1;

public record SimulationOptions(int mapWidth,
                                int mapHeight,
                                boolean corpseToxicity,
                                int initialPlantCount,
                                int initialAnimalCount,
                                int plantsGrowingEachDay,
                                int startingEnergy,
                                int energyFromOnePlant,
                                int energyToReproduce,
                                int reproductionCost,
                                int minMutatedGenes,
                                int maxMutatedGenes,
                                int genomeLength,
                                boolean savingStatistics
) {}
