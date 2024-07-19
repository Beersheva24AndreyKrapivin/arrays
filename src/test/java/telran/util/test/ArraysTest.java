package telran.util.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.util.Arrays.*;

import java.util.Comparator;
import java.util.Random;

public class ArraysTest {

    private static final int N_ELEMENTS = 1_000;
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
    }

    @Test
    void removeTest() {
        int [] expected ={10, 7, 12, 13, 3, 14};
        assertArrayEquals(expected, remove(numbers, 3));
        expected = new int[] {7, 12, -4, 13, 3, 14};
        assertArrayEquals(expected, remove(numbers, 0));
        expected = new int[] {10, 7, 12, -4, 13, 3};
        assertArrayEquals(expected, remove(numbers, 6));
    }

    /*@Test
    void pushMaxAtEndTest() {
        int [] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        pushMaxAtEnd(testNumbers);
        assertEquals(14, testNumbers[testNumbers.length - 1]);
        assertEquals(13, testNumbers[testNumbers.length - 2]);
        testNumbers = new int[] {20, -10, 10, -17};
        pushMaxAtEnd(testNumbers);
        assertEquals(20, testNumbers[testNumbers.length - 1]);
    }*/

    @Test
    void sortTest() {
        int [] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        int[] expected = {-4, 3, 7, 10, 12, 13, 14};
        sort(testNumbers);
        assertArrayEquals(expected, testNumbers);    
    }

    @Test
    void sortTestRandomArray() {
        int [] array = getRandomArray(N_ELEMENTS);
        int limit = array.length - 1;
        sort(array);
        for (int i = 0; i < limit; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

    private int[] getRandomArray(int nElements) {
        int[] res = new int[nElements];
        Random random = new Random();
        for (int i = 0; i < nElements; i++) {
            res[i] = random.nextInt();    
        }
        return res;
    }

    @Test
    void binarySearchTest() {
        //numbers = 10, 7, 12, -4, 13, 3, 14; 
        //sorted numbers = -4, 3, 7, 10, 12, 13, 14;
        int [] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        sort(testNumbers);
        assertEquals(3, binarySearch(testNumbers, 10));
        assertEquals(4, binarySearch(testNumbers, 12));
        assertEquals(6, binarySearch(testNumbers, 14));
        assertEquals(-8, binarySearch(testNumbers, 25));
        assertEquals(-4, binarySearch(testNumbers, 8));
        assertEquals(-1, binarySearch(testNumbers, -8));

        int [] array = getRandomArray(101);
        sort(array);
        int key = array[67];
        assertEquals(67, binarySearch(array, key));
    }

    @Test
    void insertSortedTest() {
        int[] sortedNumbers = {-4, 3, 7, 10, 12, 13, 14};
        int[] expected = {-4, 3, 7, 10, 11, 12, 13, 14};
        assertArrayEquals(expected, insertSorted(sortedNumbers, 11));
        int[] expected2 = {-8, -4, 3, 7, 10, 12, 13, 14};
        assertArrayEquals(expected2, insertSorted(sortedNumbers, -8));
        int[] expected3 = {-4, 3, 7, 10, 12, 13, 14, 22};
        assertArrayEquals(expected3, insertSorted(sortedNumbers, 22)); 
        int [] ar = {1, 2, 3}; 
        int [] expected4 = {1, 1, 2, 3}; 
        assertArrayEquals(expected4, insertSorted(ar, 1));   
    }

    @Test
    void isOneSwapTest() {
        int[] numbers = {-4, 13, 7, 10, 12, 3, 14}; // enough to change 13 and 3
        assertEquals(true, isOneSwap(numbers));
        int[] numbers1 = {-4, 13, 7, 22, 12, 3, 14}; // more than 1 change
        assertEquals(false, isOneSwap(numbers1));
        int[] numbers2 = {14, 3, 7, 10, 12, 13, -4}; // enough to change -4 and 14
        assertEquals(true, isOneSwap(numbers2));
        int[] numbers3 = {-4, 3, 7, 10, 12, 13, 14}; // already sorted
        assertEquals(false, isOneSwap(numbers3));
        int[] numbers4 = {3, -4, 7, 10, 12, 13, 14}; // the swaped number (3, -4) are neighbors
        assertEquals(false, isOneSwap(numbers4));
        int[] numbers5 = {1, 2, 3, 4, 20, 4, 10, 4}; // One change, same numbers
        assertEquals(true, isOneSwap(numbers5));
        int[] numbers6 = {1, 2, 3, 4, 4, 20, 10, 4}; // One change, same numbers
        assertEquals(true, isOneSwap(numbers6));
        int [] numbers7 = {1, 2, 13, 4, 4, 4, 4, 20}; 
        assertTrue(isOneSwap(numbers7));
        int [] arTrue7 = {1, 2, 10, 4, 20, 30};
        assertEquals(false, isOneSwap(arTrue7));
    }

    @Test
    void sortAnyTypeTest() {
        String [] strings = {"lmn", "cfta", "w", "aa"};
        String [] expectedASCII = {"aa", "cfta", "lmn", "w"};
        String [] expectedLenth = {"w", "aa", "lmn", "cfta"};
        sort(strings, new ComparatorASCII());
        assertArrayEquals(expectedASCII, strings);
        sort(strings, new ComparatorLenth());
        assertArrayEquals(expectedLenth, strings);
    }

    @Test
    void binarySearchAnyTypeTest() {
        Comparator<String> compString = new ComparatorASCII();
        String [] strings = {"aa", "bbb", "cfta", "ddrk", "lmn", "w"};
        assertEquals(1, binarySearch(strings, "bbb", compString));
        String [] strings2 = {"aa", "bbb", "cfta", "ddrk", "lmn", "w"};
        assertEquals(-5, binarySearch(strings2, "dfs", compString));
        Comparator<Integer> compInt = new ComparatorInt();
        Integer [] integers = {1, 4, 6, 10, 15, 20};
        assertEquals(4, binarySearch(integers, Integer.valueOf(15), compInt));
        Integer [] integers2 = {1, 4, 6, 10, 15, 20};
        assertEquals(-2, binarySearch(integers2, Integer.valueOf(3), compInt));
        Comparator<Double> compDouble = new ComparatorDouble();
        Double [] doubles = {1.3, 4.5, 6.8, 10.4, 15.6, 20.3};
        assertEquals(4, binarySearch(doubles, Double.valueOf(15.6), compDouble));
        Double [] doubles2 = {1.3, 4.5, 6.8, 10.4, 15.6, 20.3};
        assertEquals(-2, binarySearch(doubles2, Double.valueOf(3.4), compDouble));
    }

    @Test
    void binarySearchNoComparatorTest() {
        String [] strings = {"aa", "cfta", "ddrk", "lmn", "w"};
        Person prs1 = new Person(10, "Vasya");
        Person prs2 = new Person(20, "Itay");
        Person prs3 = new Person(30, "Sara");
        Person [] persons = {prs1, prs2, prs3};
        assertEquals(1, binarySearch(strings, "cfta"));
        assertEquals(0, binarySearch(persons, prs1));
        assertEquals(-1, binarySearch(persons, new Person(5, "Serg")));
    }

    @Test  
    void evenOddSorting() {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99};
        Integer[] expected = {-100, -10, -8, 10, 99, 13, 7}; //even numbers in ascending order first, odd in descending order after that
        sort(array, new EvenOddComparator());
        assertArrayEquals(expected, array);
    }

    @Test
    void findTest() {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99};
        Integer[] expected = {7, 13, 99};
        assertArrayEquals(expected, find(array, new OddNumbersPredicate()));
    }

    @Test
    void removeIfTest() {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99};
        Integer[] expected = {-8, 10, -100, -10};
        assertArrayEquals(expected, removeIf(array, new OddNumbersPredicate()));    
    }
}
