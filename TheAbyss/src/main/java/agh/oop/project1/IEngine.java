package agh.oop.project1;

public interface IEngine extends Runnable, IPositionChangeObserver, ISimulationChangeObserver {
    void run();
    void addSimulationChangeObserver(ISimulationChangeObserver observer);
    void addAnimalObserver(IAnimalObserver observer);
    void setTrackedAnimal(Animal trackedAnimal);
}
