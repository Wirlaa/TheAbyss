package agh.oop.project1;

public interface IEngine extends Runnable, IPositionChangeObserver, ISimulationChangeObserver {
    public void run();
    void addObserver(ISimulationChangeObserver observer);
}
