package agh.oop.project1;

public class Main {
    public static void main(String[] args) {
        SimulationOptions simulationOptions = new SimulationOptions(
                20,
                20,
                20,
                100,
                50,
                false,
                200,
                20,
                10,
                2,
                1,
                3,
                8);
        IWorldMap map = new HellishGateMap(simulationOptions.mapWidth(), simulationOptions.mapHeight(), simulationOptions);
        IEngine engine = new SimulationEngine(map, simulationOptions);
        try {
            engine.run();
        } catch (InterruptedException e) {
            System.out.println("Engine interruption occured");
            return;
        }
    }
}