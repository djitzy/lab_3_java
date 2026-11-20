package ru.cheremnykh.geometry;

public class House {
    private final int floors;

    public House(int floors) {
        if (floors <= 0) {
            throw new IllegalArgumentException(
                    "Количество этажей должно быть положительным, получено: " + floors
            );
        }
        this.floors = floors;
    }

    public String toString() {
        return "Дом над землей с " + floors + " " + getFloorWord(floors);
    }

    private String getFloorWord(int n) {
        int mod10 = n % 10;
        int mod100 = n % 100;
        if (mod10 == 1 && mod100 != 11) return "этажом";
        if (mod10 >= 2 && mod10 <= 4 && (mod100 < 10 || mod100 >= 20)) return "этажами";
        return "этажами";
    }
}
