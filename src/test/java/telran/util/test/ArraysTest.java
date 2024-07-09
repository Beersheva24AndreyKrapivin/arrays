package telran.util.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static telran.util.Arrays.*;

public class ArraysTest {

    int[] numbers = {10, 7, 12, -4, 13, 3, 14};

    @Test
    void searchTest() {
        assertEquals(0, search(numbers, 10));
        assertEquals(6, search(numbers, 14));
        assertEquals(3, search(numbers, -4));
        assertEquals(-1, search(numbers, 100));
    }

    @Test
    void addTest() {
        int newNumber = 100;
        int [] expected = {10, 7, 12, -4, 13, 3, 14, 100};
        assertArrayEquals(expected, add(numbers, newNumber));
    }

    @Test
    void insertTest() {
        int [] expected = {10, 7, 12, 33, -4, 13, 3, 14};
        assertArrayEquals(expected, insert(numbers, 3, 33));
        expected = new int[] {9, 10, 7, 12, -4, 13, 3, 14};
        assertArrayEquals(expected, insert(numbers, 0, 9));
        expected = new int[] {10, 7, 12, -4, 13, 3, 55, 14};
        assertArrayEquals(expected, insert(numbers, 6, 55));
        expected = new int[] {10, 7, 12, -4, 13, 3, 14, 44};
        assertArrayEquals(expected, insert(numbers, 7, 44));
        expected = new int[0];
        assertArrayEquals(expected, insert(numbers, 100, 44));
    }

    @Test
    void removeTest() {
        int [] expected ={10, 7, 12, 13, 3, 14};
        assertArrayEquals(expected, remove(numbers, 3));
        expected = new int[] {7, 12, -4, 13, 3, 14};
        assertArrayEquals(expected, remove(numbers, 0));
        expected = new int[] {10, 7, 12, -4, 13, 3};
        assertArrayEquals(expected, remove(numbers, 6));
        expected = new int[0];
        assertArrayEquals(expected, remove(numbers, 100));
    }

}
