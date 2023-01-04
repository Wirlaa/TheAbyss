package agh.oop.project1.gui;

import agh.oop.project1.Animal;
import agh.oop.project1.IAnimalObserver;

public class AnimalTrackerPresenter implements IAnimalObserver {
    private final AnimalTrackerView view;
    private final MainPresenter mainPresenter;
    public AnimalTrackerPresenter(AnimalTrackerView view, MainPresenter mainPresenter) {
        this.view = view;
        this.mainPresenter = mainPresenter;
        this.view.setPresenter(this);
    }
    public AnimalTrackerView getView() {
        return view;
    }
    public MainPresenter getMainPresenter() { return mainPresenter; }
    public void animalChanged(Animal animal) { view.refreshView(animal); }
}
