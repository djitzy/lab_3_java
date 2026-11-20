package ru.cheremnykh.data;

import java.util.Arrays;

public class ImmutableArray {
    private int[] data;

    //Инициализация массивом или перечнем чисел
    public ImmutableArray(int... values) {
        if (values == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        this.data = Arrays.copyOf(values, values.length);
    }

    //Инициализация другим списком (копирование)
    public ImmutableArray(ImmutableArray other) {
        if (other == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }
        this.data = Arrays.copyOf(other.data, other.data.length);
    }
    //Получение значения по позиции
    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    //Замена значения в позиции N
    public void set(int index, int value) {
        checkIndex(index);
        data[index] = value;
    }

    //Приведение к строке вида [1, 2, 3]
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < data.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(data[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    public int size() {
        return data.length;
    }

    public int[] toArray() {
        return Arrays.copyOf(data, data.length);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException(
                    "Индекс " + index + " вне диапазона [0, " + (data.length - 1) + "]"
            );
        }
    }
}
