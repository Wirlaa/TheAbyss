package agh.oop.project1.gui;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class LoadOptionsPresenter {
    private final LoadOptionsView view;
    private final LaunchPresenter launchPresenter;
    public LoadOptionsPresenter(LoadOptionsView view, LaunchPresenter launchPresenter) {
        this.view = view;
        this.launchPresenter = launchPresenter;
        this.view.setPresenter(this);
    }
    public LoadOptionsView getView() {
        return view;
    }
    public void loadConfiguration (String path) {
        try (
            FileInputStream file = new FileInputStream(path)) {
            Scanner scanner = new Scanner(file);
            /*List<String> stringOptions = new ArrayList<>();
            while (scanner.hasNextLine()) {
                stringOptions.add(scanner.nextLine());
            }*/
            String[] stringOptions = scanner.nextLine().split(" ");
            int[] intOptions = new int[12]; //cos jest nie tak z wielkoscia?
            boolean corpseToxicity = false; //niebezpieczna inicjalizacja
            for (int i = 0, j = 0; i < stringOptions.length - 1; i++) {
                if (isNumber(stringOptions[i])) {
                    intOptions[j] = parseInt(stringOptions[i]);
                    j++;
                } else if (stringOptions[i].equals("false") || stringOptions[i].equals("true")) {
                    corpseToxicity = parseBoolean(stringOptions[i]);
                }
                else throw new IllegalArgumentException(stringOptions[i] + " is not a legal simulation option");
            }
            boolean saveStats = parseBoolean(stringOptions[stringOptions.length-1]);
            launchPresenter.setOptions(intOptions,corpseToxicity,saveStats);
            launchPresenter.showOptions(false);
        }
        catch (
                IOException exception) {
            exception.printStackTrace();
        }
    }
    public static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public void refreshView() {
        view.buildView();
    }
}
