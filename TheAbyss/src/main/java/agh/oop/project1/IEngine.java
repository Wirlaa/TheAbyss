package agh.oop.project1;

public interface IEngine extends Runnable, IPositionChangeObserver, ISimulationChangeObserver {
    void run();
    void addObserver(ISimulationChangeObserver observer);
    void pause();
}
