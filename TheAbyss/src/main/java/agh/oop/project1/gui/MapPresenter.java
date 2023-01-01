package agh.oop.project1.gui;

//import agh.ics.oop.ISimulationChangeObserver;

public class MapPresenter {//implements ISimulationChangeObserver {
    private final MapView view;
    private final MainPresenter mainPresenter;
    public MapPresenter(MapView view, MainPresenter mainPresenter) {
        this.view = view;
        this.mainPresenter = mainPresenter;
        this.view.setPresenter(this);
    }
    public MapView getView() {
        return view;
    }
    //@Override
    public void simulationChanged() {
        view.buildView();
        mainPresenter.updateView();
    }
}
