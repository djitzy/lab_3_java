package ru.cheremnykh.weapon;

public class Shooter {
    private String name;
    private Weapon weapon;

    public Shooter(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя стрелка не может быть пустым");
        }
        this.name = name;
        this.weapon = null;
    }

    public Shooter(String name, Weapon weapon) {
        this(name);
        this.weapon = weapon;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name;
    }

    public Weapon getWeapon() { return weapon; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public void shoot() {
        if (weapon != null) {
            weapon.shoot();
        } else {
            System.out.println("не могу участвовать в перестрелке");
        }
    }

    public String toString() {
        if (weapon != null) {
            return "Стрелок '" + name + "' с оружием: " + weapon;
        }
        return "Стрелок '" + name + "' без оружия";
    }
}
