package agh.oop.project1;

import agh.oop.project1.gui.App;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        SimulationOptions simulationOptions = new SimulationOptions(
                20,
                20,
                20,
                100,
                10,
                false,
                0,
                20,
                10,
                2,
                1,
                3,
                8);
        IWorldMap map = new HellishGateMap(simulationOptions.mapWidth(), simulationOptions.mapHeight(), simulationOptions);
        IEngine engine = new SimulationEngine(map, simulationOptions);
        /*try {
            //Application.launch(AppOld.class, args);
            Application.launch(App.class, args);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
         */

        try {
            engine.run();
        } catch (InterruptedException e) {
            System.out.println("Engine interruption occured");
            return;
        }
    }
}