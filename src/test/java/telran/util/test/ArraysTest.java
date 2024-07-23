package telran.util.test;

import org.junit.jupiter.api.Test;

import telran.util.CharacterRule;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static telran.util.Arrays.*;

import java.util.Comparator;
import java.util.Random;

public class ArraysTest {

    private static final int N_ELEMENTS = 1_000;
    int[] numbers = { 10, 7, 12, -4, 13, 3, 14 };

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
        int[] expected = { 10, 7, 12, -4, 13, 3, 14, 100 };
        assertArrayEquals(expected, add(numbers, newNumber));
    }

    @Test
    void insertTest() {
        int[] expected = { 10, 7, 12, 33, -4, 13, 3, 14 };
        assertArrayEquals(expected, insert(numbers, 3, 33));
        expected = new int[] { 9, 10, 7, 12, -4, 13, 3, 14 };
        assertArrayEquals(expected, insert(numbers, 0, 9));
        expected = new int[] { 10, 7, 12, -4, 13, 3, 55, 14 };
        assertArrayEquals(expected, insert(numbers, 6, 55));
        expected = new int[] { 10, 7, 12, -4, 13, 3, 14, 44 };
        assertArrayEquals(expected, insert(numbers, 7, 44));
    }

    @Test
    void removeTest() {
        int[] expected = { 10, 7, 12, 13, 3, 14 };
        assertArrayEquals(expected, remove(numbers, 3));
        expected = new int[] { 7, 12, -4, 13, 3, 14 };
        assertArrayEquals(expected, remove(numbers, 0));
        expected = new int[] { 10, 7, 12, -4, 13, 3 };
        assertArrayEquals(expected, remove(numbers, 6));
    }

    @Test
    void sortTest() {
        int[] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        int[] expected = { -4, 3, 7, 10, 12, 13, 14 };
        sort(testNumbers);
        assertArrayEquals(expected, testNumbers);
    }

    @Test
    void sortTestRandomArray() {
        int[] array = getRandomArray(N_ELEMENTS);
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
        // numbers = 10, 7, 12, -4, 13, 3, 14;
        // sorted numbers = -4, 3, 7, 10, 12, 13, 14;
        int[] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        sort(testNumbers);
        assertEquals(3, binarySearch(testNumbers, 10));
        assertEquals(4, binarySearch(testNumbers, 12));
        assertEquals(6, binarySearch(testNumbers, 14));
        assertEquals(-8, binarySearch(testNumbers, 25));
        assertEquals(-4, binarySearch(testNumbers, 8));
        assertEquals(-1, binarySearch(testNumbers, -8));

        int[] array = getRandomArray(101);
        sort(array);
        int key = array[67];
        assertEquals(67, binarySearch(array, key));
    }

    @Test
    void insertSortedTest() {
        int[] sortedNumbers = { -4, 3, 7, 10, 12, 13, 14 };
        int[] expected = { -4, 3, 7, 10, 11, 12, 13, 14 };
        assertArrayEquals(expected, insertSorted(sortedNumbers, 11));
        int[] expected2 = { -8, -4, 3, 7, 10, 12, 13, 14 };
        assertArrayEquals(expected2, insertSorted(sortedNumbers, -8));
        int[] expected3 = { -4, 3, 7, 10, 12, 13, 14, 22 };
        assertArrayEquals(expected3, insertSorted(sortedNumbers, 22));
        int[] ar = { 1, 2, 3 };
        int[] expected4 = { 1, 1, 2, 3 };
        assertArrayEquals(expected4, insertSorted(ar, 1));
    }

    @Test
    void isOneSwapTest() {
        int[] numbers = { -4, 13, 7, 10, 12, 3, 14 }; // enough to change 13 and 3
        assertEquals(true, isOneSwap(numbers));
        int[] numbers1 = { -4, 13, 7, 22, 12, 3, 14 }; // more than 1 change
        assertEquals(false, isOneSwap(numbers1));
        int[] numbers2 = { 14, 3, 7, 10, 12, 13, -4 }; // enough to change -4 and 14
        assertEquals(true, isOneSwap(numbers2));
        int[] numbers3 = { -4, 3, 7, 10, 12, 13, 14 }; // already sorted
        assertEquals(false, isOneSwap(numbers3));
        int[] numbers4 = { 3, -4, 7, 10, 12, 13, 14 }; // the swaped number (3, -4) are neighbors
        assertEquals(false, isOneSwap(numbers4));
        int[] numbers5 = { 1, 2, 3, 4, 20, 4, 10, 4 }; // One change, same numbers
        assertEquals(true, isOneSwap(numbers5));
        int[] numbers6 = { 1, 2, 3, 4, 4, 20, 10, 4 }; // One change, same numbers
        assertEquals(true, isOneSwap(numbers6));
        int[] numbers7 = { 1, 2, 13, 4, 4, 4, 4, 20 };
        assertTrue(isOneSwap(numbers7));
        int[] arTrue7 = { 1, 2, 10, 4, 20, 30 };
        assertEquals(false, isOneSwap(arTrue7));
    }

    @Test
    void sortAnyTypeTest() {
        String[] strings = { "lmn", "cfta", "w", "aa" };
        String[] expectedASCII = { "aa", "cfta", "lmn", "w" };
        String[] expectedLenth = { "w", "aa", "lmn", "cfta" };
        sort(strings, (a, b) -> a.compareTo(b));
        assertArrayEquals(expectedASCII, strings);
        sort(strings, (a, b) -> Integer.compare(a.length(), b.length()));
        assertArrayEquals(expectedLenth, strings);
    }

    @Test
    void binarySearchAnyTypeTest() {
        Comparator<String> compString = (a, b) -> a.compareTo(b);
        String[] strings = { "aa", "bbb", "cfta", "ddrk", "lmn", "w" };
        assertEquals(1, binarySearch(strings, "bbb", compString));
        String[] strings2 = { "aa", "bbb", "cfta", "ddrk", "lmn", "w" };
        assertEquals(-5, binarySearch(strings2, "dfs", compString));
        Comparator<Integer> compInt = Integer::compareTo;
        Integer[] integers = { 1, 4, 6, 10, 15, 20 };
        assertEquals(4, binarySearch(integers, Integer.valueOf(15), compInt));
        Integer[] integers2 = { 1, 4, 6, 10, 15, 20 };
        assertEquals(-2, binarySearch(integers2, Integer.valueOf(3), compInt));
        Comparator<Double> compDouble = Double::compareTo;
        Double[] doubles = { 1.3, 4.5, 6.8, 10.4, 15.6, 20.3 };
        assertEquals(4, binarySearch(doubles, Double.valueOf(15.6), compDouble));
        Double[] doubles2 = { 1.3, 4.5, 6.8, 10.4, 15.6, 20.3 };
        assertEquals(-2, binarySearch(doubles2, Double.valueOf(3.4), compDouble));
    }

    @Test
    void binarySearchNoComparatorTest() {
        String[] strings = { "aa", "cfta", "ddrk", "lmn", "w" };
        Person prs1 = new Person(10, "Vasya");
        Person prs2 = new Person(20, "Itay");
        Person prs3 = new Person(30, "Sara");
        Person[] persons = { prs1, prs2, prs3 };
        assertEquals(1, binarySearch(strings, "cfta"));
        assertEquals(0, binarySearch(persons, prs1));
        assertEquals(-1, binarySearch(persons, new Person(5, "Serg")));
    }

    @Test
    void evenOddSorting() {
        Integer[] array = { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] expected = { -100, -10, -8, 10, 99, 13, 7 }; // even numbers in ascending order first, odd in
                                                               // descending order after that
        sort(array, (a, b) -> {
            int res = 0;
            if (a % 2 != b % 2) {
                res = a % 2 == 0 ? -1 : 1;
            } else {
                res = a % 2 == 0 ? a - b : b - a;
            }
            return res;
        });
        assertArrayEquals(expected, array);
    }

    @Test
    void findTest() {
        Integer[] array = { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] expected = { 7, 13, 99 };
        assertArrayEquals(expected, find(array, n -> n % 2 != 0));
    }

    @Test
    void removeIfTest() {
        Integer[] array = { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] expected = { -8, 10, -100, -10 };
        assertArrayEquals(expected, removeIf(array, n -> n % 2 != 0));
    }

    @Test
    void matchRulesTest() {
        //TODO
        //Must be rules: at least one capital letter, at least one lower case letter
        //at least one digit, at least one dot(.)
        //Must not be rules: space is disallowed
        //examples: mathes - {'a', 'n', '*', 'G', '.', '.', '1'}
        //mismatches - {'a', 'n', '*', 'G', '.', '.', '1', ' '} -> "space disallowed", 
        //{'a', 'n', '*', '.', '.', '1'} -> "no capital", 
        //{'a', 'n', '*', 'G', '.', '.'} -> "no digit"
        CharacterRule[] mustBeRules = setMustBeRules();
        CharacterRule[] mustNotBeRules = setMustNotBeRules();
        char[] matches = {'a', 'n', '*', 'G', '.', '.', '1'};
        assertEquals("", matchesRules(matches, mustBeRules, mustNotBeRules)); // no error
        char[] matches1 = {'a', 'n', '*', 'G', '.', '.', '1', ' '};
        assertEquals("space disallowed", matchesRules(matches1, mustBeRules, mustNotBeRules));
        char[] matches2 = {'a', 'n', '*', '.', '.', '1'};
        assertEquals("no capital", matchesRules(matches2, mustBeRules, mustNotBeRules));
        char[] matches3 = {'a', 'n', '*', 'G', '.', '.'};
        assertEquals("no digit", matchesRules(matches3, mustBeRules, mustNotBeRules));
        char[] matches4 = {'*', 'G', '.', '.', '1'};
        assertEquals("no lower", matchesRules(matches4, mustBeRules, mustNotBeRules));
        char[] matches5 = {'a', 'n', '*', 'G', '1'};
        assertEquals("no dote", matchesRules(matches5, mustBeRules, mustNotBeRules));
        char[] matches6 ={'a', 'n', '*', '1', ' '};
        assertEquals("no capital, no dote, space disallowed", matchesRules(matches6, mustBeRules, mustNotBeRules));
    }

    private CharacterRule[] setMustBeRules() {
        CharacterRule mustBeRuleUpCase = new CharacterRule(false, n -> Character.isUpperCase(n), "no capital");
        CharacterRule mustBeRuleLowCase = new CharacterRule(false, n -> Character.isLowerCase(n), "no lower");
        CharacterRule mustBeRuleDigit = new CharacterRule(false, n -> Character.isDigit(n), "no digit");
        CharacterRule mustBeRuleDote = new CharacterRule(false, n -> n == (char)'.', "no dote");
        CharacterRule[] mustBeRules = {mustBeRuleUpCase, mustBeRuleLowCase, mustBeRuleDigit, mustBeRuleDote};  
        return mustBeRules;  
    }

    private CharacterRule[] setMustNotBeRules() {
        CharacterRule mustNotBeRuleSpace = new CharacterRule(true, n -> Character.isWhitespace(n), "space disallowed");
        CharacterRule[] mustNotBeRules = {mustNotBeRuleSpace};
        return mustNotBeRules;
    }

}
