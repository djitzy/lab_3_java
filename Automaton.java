package ru.cheremnykh.weapon;

public class Automaton extends Gun {
    private final int fireRate;

    public Automaton() {
        super(30);
        this.fireRate = 30;
    }

    public Automaton(int maxBullets) {
        super(maxBullets);
        this.fireRate = maxBullets / 2;
    }

    public Automaton(int maxBullets, int fireRate) {
        super(maxBullets);
        if (fireRate <= 0) {
            throw new IllegalArgumentException(
                    "Скорострельность должна быть положительной, получено: " + fireRate
            );
        }
        this.fireRate = fireRate;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void shoot() {
        for (int i = 0; i < fireRate; i++) {
            super.shoot();
        }
    }

    public void shootSeconds(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Время не может быть отрицательным");
        }
        int totalShots = seconds * fireRate;
        for (int i = 0; i < totalShots; i++) {
            super.shoot();
        }
    }

    public String toString() {
        return "Автомат [" + bullets + "/" + getMaxBullets() + "] скорострельность: " + fireRate + " выстр/сек";
    }
}
