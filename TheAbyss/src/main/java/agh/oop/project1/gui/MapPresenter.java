package agh.oop.project1.gui;


import agh.oop.project1.ISimulationChangeObserver;

public class MapPresenter implements ISimulationChangeObserver {
    private final MapView view;
    private final MainPresenter mainPresenter;
    public MapPresenter(MapView view, MainPresenter mainPresenter) {
        this.view = view;
        this.mainPresenter = mainPresenter;
        this.view.setPresenter(this);
    }
    public MapView getView() { return view; }
    public MainPresenter getMainPresenter() { return mainPresenter; }
    @Override
    public void simulationChanged() {
        view.buildView();
        mainPresenter.updateView();
    }
}
