package agh.oop.project1;

public class Main {
    public static void main(String[] args) {
        SimulationOptions simulationOptions = new SimulationOptions(200,
                46,
                20,
                10,
                5,
                false,
                4,
                20000,
                10,
                2,
                1,
                3,
                8);
        IWorldMap map = new HellishGateMap(simulationOptions.mapWidth(), simulationOptions.mapHeight(), simulationOptions);
        SimulationEngine engine = new SimulationEngine(map, simulationOptions);
        try {
            engine.run();
        } catch (InterruptedException e) {
            System.out.println("Engine interruption occured");
            return;
        }
    }
}
