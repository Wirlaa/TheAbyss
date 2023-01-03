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
    public MainPresenter getMainPresenter() { return mainPresenter; }
    public String getInput() { return view.getInput(); }
    public void refreshView() { view.buildView(); }
    /* funkcje przydatne jakby mozna bylo ustawiac/zmieniac kazda opcje samodzielnie
    public void setMapWidth (int mapWidth) {}
    public void setMapHeight (int mapHeight) {}
    public void setCorpseToxicity (boolean corpseToxicity) {}
    public void setInitialPlantCount (int initialPlantCount) {}
    public void setInitialAnimalCount (int initialAnimalCount) {}
    public void setPlantsGrowingEachDay (int plantsGrowingEachDay) {}
    public void setStartingEnergy (int startingEnergy) {}
    public void setEnergyFromOnePlant(int energyFromOnePlant) {}
    public void setEnergyToReproduce (int energyToReproduce) {}
    public void  setReproductionCost (int reproductionCost) {}
    public void setMinMutatedGenes (int minMutatedGenes) {}
    public void setMaxMutatedGenes (int maxMutatedGenes) {}
    public void setGenomeLength (int genomeLength) {} */
}
