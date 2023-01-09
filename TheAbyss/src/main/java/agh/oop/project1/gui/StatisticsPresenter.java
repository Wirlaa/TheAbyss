package agh.oop.project1.gui;

import agh.oop.project1.ISimulationChangeObserver;

public class StatisticsPresenter implements ISimulationChangeObserver {
    private final StatisticsView view;
    private final MainPresenter mainPresenter;
    public StatisticsPresenter(StatisticsView view, MainPresenter mainPresenter) {
        this.view = view;
        this.mainPresenter = mainPresenter;
        this.view.setPresenter(this);
    }
    public StatisticsView getView() {
        return view;
    }
    public MainPresenter getMainPresenter() { return mainPresenter; }
    public void simulationChanged() {
        view.refreshView();
    }
}
