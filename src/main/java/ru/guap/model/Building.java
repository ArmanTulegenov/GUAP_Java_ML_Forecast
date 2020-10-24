package ru.guap.model;

import java.util.Objects;

/*

Строения методы и свойства, которые будут наследовать все типы строений
 */
public class Building {
    private String type;
    private String address;

    public Building(String type, String address) {
        this.type = type;
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Тип здания: %s", type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(type, building.type) &&
                Objects.equals(address, building.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, address);
    }
}
