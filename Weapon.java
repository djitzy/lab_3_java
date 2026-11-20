package ru.cheremnykh.weapon;

public abstract class Weapon {
    protected int bullets;

    public abstract void shoot();

    public int getBullets() {
        return bullets;
    }

    public boolean isLoaded() {
        return bullets > 0;
    }

    public String toString() {
        return "Оружие [" + bullets + " патронов]";
    }
}
