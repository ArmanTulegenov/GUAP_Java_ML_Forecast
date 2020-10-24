package ru.guap;

import ru.guap.model.Building;
import ru.guap.model.PanelMadeBuilding;

public class Main {

    public static void main(String[] args) {
        Building building = new Building("Хрущевка", "");
        System.out.println(building.getType());

        Building building1 = new Building("Новый комплекс на Лиговке", "");
        System.out.println(building1.toString());

        Building building1_1 = new Building("Новый комплекс на Лиговке", "");
        System.out.println(building1_1.toString());

        Building building2 = new PanelMadeBuilding();
        System.out.println(building2.getType());

        Object object1 = building;
        System.out.println(object1);

        System.out.println(building == object1);
        System.out.println(building1 == building2);
        System.out.println(building1.equals(building2));

        System.out.println("Result ----->");
        System.out.println(building1 == building1_1);
        System.out.println(building1.equals(building1_1));

        Boolean valueIs = Boolean.TRUE;
        boolean literalValue = true;

        Integer valueOfInteger = 1000;
        int valueOfInt = 1000;

        System.out.println(valueOfInteger == valueOfInt);



    }
}
