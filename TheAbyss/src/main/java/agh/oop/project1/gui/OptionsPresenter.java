package agh.oop.project1.gui;

public class OptionsPresenter {
    private final OptionsView view;
    private final MainPresenter mainPresenter;
    public OptionsPresenter(OptionsView view, MainPresenter mainPresenter) {
        this.view = view;
        this.mainPresenter = mainPresenter;
        this.view.setPresenter(this);
    }
    public OptionsView getView() {
        return view;
    }
}
