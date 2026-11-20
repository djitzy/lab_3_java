package ru.cheremnykh.main;

import ru.cheremnykh.weapon.Weapon;
import ru.cheremnykh.weapon.Gun;
import ru.cheremnykh.weapon.Automaton;
import ru.cheremnykh.weapon.Shooter;
import ru.cheremnykh.geometry.House;
import ru.cheremnykh.geometry.Point;
import ru.cheremnykh.data.ImmutableArray;

import java.util.Scanner;
import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                double result = power(args[0], args[1]);
                System.out.println("Результат (из аргументов КС): " + args[0] + " ^ " + args[1] + " = " + result);
                return;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка в аргументах командной строки, запуск меню...");
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("  Лабораторная работа №3. Вариант 6");
            System.out.println(" 1. Задание 1.1 — Дом над землей");
            System.out.println(" 2. Задание 1.5 — Перезарядка Пистолета");
            System.out.println(" 3. Задание 2.1 — Неизменяемый массив");
            System.out.println(" 4. Задание 3.4 — Автомат");
            System.out.println(" 5. Задание 4.1 — Оружие (наследование)");
            System.out.println(" 6. Задание 5.8 — Лучший стрелок");
            System.out.println(" 7. Задание 6.2 — Сравнение точек");
            System.out.println(" 8. Задание 7.3 — Возведение в степень");
            System.out.println(" 9. Запустить все задания");
            System.out.println(" 0. Выход");
            System.out.print("\nВыберите пункт: ");

            int choice = readInt();

            switch (choice) {
                case 1: task1_1(); break;
                case 2: task1_5(); break;
                case 3: task2_1(); break;
                case 4: task3_4(); break;
                case 5: task4_1(); break;
                case 6: task5_8(); break;
                case 7: task6_2(); break;
                case 8: task7_3(); break;
                case 9: runAll(); break;
                case 0:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный пункт меню!");
            }
        }
        scanner.close();
    }

    //Задание 7.3
    public static double power(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);
        return pow(x, y);
    }

    //Демонстрации

    private static void task1_1() {
        System.out.println("\n---Задание 1.1 — Дом над землей");
        System.out.println("Демонстрация корректных домов:");
        System.out.println("  " + new House(1));
        System.out.println("  " + new House(5));
        System.out.println("  " + new House(23));
        System.out.println("  " + new House(111));

        System.out.println("\nДемонстрация обработки ошибок:");
        try {
            System.out.println("Попытка создать дом с 0 этажами...");
            new House(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        try {
            System.out.println("Попытка создать дом с -5 этажами...");
            new House(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.print("\nВведите количество этажей для своего дома: ");
        int floors = readInt();
        try {
            House h = new House(floors);
            System.out.println("Создан: " + h);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void task1_5() {
        System.out.println("\n---Задание 1.5 — Перезарядка Пистолета");
        System.out.println("Сценарий из задания:");
        System.out.println("Создаём пистолет вместимостью 7");
        Gun gun = new Gun(7);
        System.out.println("  " + gun);

        System.out.println("Заряжаем 3 патрона...");
        int extra = gun.reload(3);
        System.out.println("  " + gun + " (лишних: " + extra + ")");

        System.out.println("Стреляем 5 раз:");
        for (int i = 0; i < 5; i++) {
            System.out.print("    Выстрел " + (i + 1) + ": ");
            gun.shoot();
        }

        System.out.println("Заряжаем 8 патронов (влезет только 7):");
        extra = gun.reload(8);
        System.out.println("  " + gun + " (лишних возвращено: " + extra + ")");

        System.out.println("Стреляем 2 раза:");
        for (int i = 0; i < 2; i++) {
            System.out.print("    Выстрел " + (i + 1) + ": ");
            gun.shoot();
        }

        System.out.println("Разряжаем пистолет:");
        int returned = gun.unload();
        System.out.println("Возвращено патронов: " + returned);
        System.out.println("  " + gun);

        System.out.println("Контрольный выстрел:");
        System.out.print("    ");
        gun.shoot();

        System.out.println("\nДемонстрация обработки ошибок:");
        try {
            System.out.print("  Попытка зарядить -5 патронов: ");
            gun.reload(-5);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("\nВведите вместимость нового пистолета: ");
        int capacity = readInt();
        try {
            Gun g2 = new Gun(capacity);
            System.out.println("Создан: " + g2);
            System.out.print("Сколько патронов зарядить? ");
            int n = readInt();
            g2.reload(n);
            System.out.println("После зарядки: " + g2);
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ " + e.getMessage());
        }
    }

    private static void task2_1() {
        System.out.println("\n---Задание 2.1 — Неизменяемый массив");

        System.out.println("Создание из массива:");
        ImmutableArray arr1 = new ImmutableArray(new int[]{10, 20, 30});
        System.out.println("  " + arr1);

        System.out.println("Создание из перечня чисел:");
        ImmutableArray arr2 = new ImmutableArray(1, 2, 3, 4, 5);
        System.out.println("  " + arr2);

        System.out.println("Копирование другого списка:");
        ImmutableArray arr3 = new ImmutableArray(arr2);
        System.out.println("  Копия: " + arr3);

        System.out.println("\nДемонстрация методов:");
        System.out.println("Размер arr2: " + arr2.size());
        System.out.println("Пустой? " + arr2.isEmpty());
        System.out.println("Элемент [2]: " + arr2.get(2));

        System.out.println("Замена элемента [1] на 99:");
        arr2.set(1, 99);
        System.out.println("После замены: " + arr2);

        System.out.println("\nДемонстрация обработки ошибок:");
        try {
            System.out.print("Попытка получить элемент [10]: ");
            arr2.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("\nВведите количество элементов: ");
        int n = readInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент [" + i + "]: ");
            values[i] = readInt();
        }
        ImmutableArray custom = new ImmutableArray(values);
        System.out.println("Ваш массив: " + custom);
    }

    private static void task3_4() {
        System.out.println("\n---Задание 3.4 — Автомат");

        System.out.println("Автомат по умолчанию (скорострельность 30, обойма 30):");
        Automaton a1 = new Automaton();
        System.out.println("  " + a1);

        System.out.println("\nАвтомат с обоймой 20 (скорострельность = 20/2 = 10):");
        Automaton a2 = new Automaton(20);
        System.out.println("  " + a2);

        System.out.println("\nАвтомат с обоймой 10 и скорострельностью 3:");
        Automaton a3 = new Automaton(10, 3);
        a3.reload(10);
        System.out.println("  " + a3);
        System.out.println("Один выстрел (должно быть 3 'Бах!'):");
        a3.shoot();
        System.out.println("После выстрела: " + a3);

        System.out.println("\nСтрельба 2 секунды из a3 (2*3 = 6 выстрелов):");
        a3.reload(10);
        System.out.println("Перед стрельбой: " + a3);
        a3.shootSeconds(2);
        System.out.println("После стрельбы: " + a3);

        System.out.print("\nВведите скорострельность для нового автомата: ");
        int rate = readInt();
        try {
            Automaton custom = new Automaton(30, rate);
            custom.reload(30);
            System.out.println("Создан: " + custom);
            System.out.println("Один выстрел:");
            custom.shoot();
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ " + e.getMessage());
        }
    }

    private static void task4_1() {
        System.out.println("\n--- Задание 4.1 — Оружие");
        System.out.println("Демонстрация полиморфизма через абстрактный класс ru.cheremnykh.weapon.Weapon:\n");

        Gun[] arsenal = {
                new Gun(7),
                new Automaton(10, 2)
        };

        arsenal[0].reload(3);
        arsenal[1].reload(5);

        for (Weapon w : arsenal) {
            System.out.println("Объект: " + w);
            System.out.print("Выстрел: ");
            w.shoot();
            System.out.println("Заряжено: " + w.getBullets());
            System.out.println("Заряжен? " + w.isLoaded());
            System.out.println();
        }

        System.out.println("ru.cheremnykh.weapon.Gun является ru.cheremnykh.weapon.Weapon? " + (arsenal[0] instanceof Weapon));
        System.out.println("ru.cheremnykh.weapon.Automaton является ru.cheremnykh.weapon.Weapon? " + (arsenal[1] instanceof Weapon));
        System.out.println("ru.cheremnykh.weapon.Automaton является ru.cheremnykh.weapon.Gun? " + (arsenal[1] instanceof Gun));
    }
    private static void task5_8() {
        System.out.println("\n---Задание 5.8 — Лучший стрелок");

        Shooter s1 = new Shooter("Иванов");
        Gun pistol = new Gun(7);
        pistol.reload(3);
        Shooter s2 = new Shooter("Петров", pistol);
        Automaton auto = new Automaton(10, 2);
        auto.reload(5);
        Shooter s3 = new Shooter("Сидоров", auto);

        System.out.println("Стрелки:");
        System.out.println("  " + s1);
        System.out.println("  " + s2);
        System.out.println("  " + s3);

        System.out.println("\nСтрельба:");
        System.out.print("  " + s1.getName() + " (без оружия): ");
        s1.shoot();
        System.out.print("  " + s2.getName() + " (пистолет): ");
        s2.shoot();
        System.out.print("  " + s3.getName() + " (автомат, 2 выстрела): ");
        s3.shoot();

        System.out.println("\nСмена оружия:");
        s1.setWeapon(pistol);
        System.out.println("  " + s1.getName() + " получил пистолет: " + s1);
        System.out.print("Стрельба: ");
        s1.shoot();

        System.out.print("\nВведите имя нового стрелка: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        try {
            Shooter custom = new Shooter(name);
            System.out.println("Создан: " + custom);
            System.out.print("Стрельба без оружия: ");
            custom.shoot();
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ " + e.getMessage());
        }
    }

    private static void task6_2() {
        System.out.println("\n---Задание 6.2 — Сравнение точек");

        Point p1 = new Point(3, 5);
        Point p2 = new Point(3, 5);
        Point p3 = new Point(3, 7);
        Point p4 = p1;

        System.out.println("Точки:");
        System.out.println("  p1 = " + p1);
        System.out.println("  p2 = " + p2);
        System.out.println("  p3 = " + p3);
        System.out.println("  p4 = p1 = " + p4);

        System.out.println("\nСравнения:");
        System.out.println("  p1.equals(p2) [одинаковые координаты]: " + p1.equals(p2));
        System.out.println("  p1.equals(p3) [разные Y]: " + p1.equals(p3));
        System.out.println("  p1.equals(p4) [одна ссылка]: " + p1.equals(p4));
        System.out.println("  p1.equals(null): " + p1.equals(null));
        System.out.println("  p1.equals(\"строка\"): " + p1.equals("строка"));

        System.out.println("\nhashCode:");
        System.out.println("  p1.hashCode() = " + p1.hashCode());
        System.out.println("  p2.hashCode() = " + p2.hashCode());
        System.out.println("  p3.hashCode() = " + p3.hashCode());
        System.out.println("  (p1 == p2 по hashCode): " + (p1.hashCode() == p2.hashCode()));

        System.out.print("\nВведите X для вашей точки: ");
        int x = readInt();
        System.out.print("Введите Y для вашей точки: ");
        int y = readInt();
        Point custom = new Point(x, y);
        System.out.println("Ваша точка: " + custom);
        System.out.println("Совпадает с p1? " + custom.equals(p1));
    }

    private static void task7_3() {
        System.out.println("\n---Задание 7.3 — Возведение в степень");
        System.out.println("Используются короткие имена: parseInt и pow (static import)");

        System.out.print("\nВведите основание X: ");
        String xStr = scanner.next();
        System.out.print("Введите показатель Y: ");
        String yStr = scanner.next();

        try {
            double result = power(xStr, yStr);
            System.out.println("\nРезультат: " + xStr + " ^ " + yStr + " = " + result);
        } catch (NumberFormatException e) {
            System.out.println("\nОшибка: введены некорректные числа");
        }
    }

    private static void runAll() {
        task1_1();
        task1_5();
        task2_1();
        task3_4();
        task4_1();
        task5_8();
        task6_2();
        task7_3();
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("  Ошибка! Введите целое число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
