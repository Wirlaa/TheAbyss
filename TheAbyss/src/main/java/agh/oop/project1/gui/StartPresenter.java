package agh.oop.project1.gui;

// poczatkowy ekran do uruchamiania symulacji
// umozliwia wybranie przygotowanych konfiguracji z folderu
// umozliwia wczytanie konfiguracji sporzadzonej przez uzytkownika
public class StartPresenter {
    private final StartView view;
    private final MainPresenter mainPresenter;
    public StartPresenter(StartView view, MainPresenter mainPresenter) {
        this.view = view;
        this.mainPresenter = mainPresenter;
        this.view.setPresenter(this);
        this.view.setCenter(mainPresenter.showOptions());
    }
    public StartView getView() {
        return view;
    }
}
