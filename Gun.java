package ru.cheremnykh.weapon;

public class Gun extends Weapon {
    private final int maxBullets;

    public Gun() {
        this(7);
    }

    public Gun(int maxBullets) {
        if (maxBullets <= 0) {
            throw new IllegalArgumentException(
                    "Максимальное количество патронов должно быть положительным, получено: " + maxBullets
            );
        }
        this.maxBullets = maxBullets;
        this.bullets = 0;
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public int reload(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Отрицательного числа патронов быть не может, получено: " + amount
            );
        }
        int freeSpace = maxBullets - bullets;
        int loaded = Math.min(amount, freeSpace);
        bullets += loaded;
        return amount - loaded; // лишние патроны
    }

    public int unload() {
        int returned = bullets;
        bullets = 0;
        return returned;
    }

    public void shoot() {
        if (bullets > 0) {
            System.out.println("Бах!");
            bullets--;
        } else {
            System.out.println("Клац!");
        }
    }

    public String toString() {
        return "Пистолет [" + bullets + "/" + maxBullets + "]";
    }
}
