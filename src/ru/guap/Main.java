package ru.guap;

import ru.guap.model.Building;
import ru.guap.model.PanelMadeBuilding;

public class Main {

    public static void main(String[] args) {
        Building building = new Building("Хрущевка");
        System.out.println(building.getType());

        Building building1 = new PanelMadeBuilding();
        System.out.println(building1.getType());
    }
}
