package telran.util;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class Arrays {

    public static int search(int[] ar, int key) {

        int index = 0;

        while (index < ar.length && key != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    public static int[] add(int[] ar, int numbers) {

        int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
        res[ar.length] = numbers;
        return res;
    }

    /**
     * 
     * @param ar
     * @param index
     * @param number
     * @return reference to a new array containing @param number at @param index
     */
    public static int[] insert(int[] ar, int index, int number) {

        // creates new array with all elements from the given "ar" and
        // the given "number" at the given index
        // to apply System.arraycopy method

        int[] res = new int[ar.length + 1];

        System.arraycopy(ar, 0, res, 0, index);
        res[index] = number;
        System.arraycopy(ar, index, res, index + 1, ar.length - index);

        return res;
    }

    /**
     * 
     * @param numbers
     * @param index
     * @return new array with no removed from @param numbers number at @param index
     */
    public static int[] remove(int[] numbers, int index) {

        // creates new array with no element in "numbers" at "index"
        // to apply System.arraycopy method
        int[] res = new int[numbers.length - 1];

        System.arraycopy(numbers, 0, res, 0, index);
        System.arraycopy(numbers, index + 1, res, index, numbers.length - index - 1);

        return res;

    }

    public static boolean pushMaxAtEnd(int[] ar, int length) {
        boolean res = true;
        for (int i = 0; i < length; i++) {
            if (ar[i] > ar[i + 1]) {
                res = false;
                swap(ar, i, i + 1);
            }
        }
        return res;
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void sort(int[] ar) {
        int length = ar.length;
        boolean flSorted = false;
        while (!flSorted) {
            length--;
            flSorted = pushMaxAtEnd(ar, length);
        }
    }

    /**
     * 
     * @param ar  - sorted array
     * @param key - being searched number
     * @return see comments definition
     */
    public static int binarySearch(int[] ar, int key) {

        int res = -1;
        int first = 0;
        int last = ar.length - 1;

        while (first <= last) {
            int middleIndex = (first + last) / 2;
            if (ar[middleIndex] == key) {
                res = middleIndex;
                first = last + 1;
            } else if (ar[middleIndex] < key) {
                first = middleIndex + 1;
            } else {
                last = middleIndex - 1;
            }
        }

        if (res == -1) {
            res = -first - 1;
        }

        return res;
    }

    public static int[] insertSorted(int[] arSorted, int testNumbers) {
        // TODO
        // arSorted is sorted array
        // to insert number at index to keep the array sorted
        // additional sorting is disallowed

        int index = (binarySearch(arSorted, testNumbers) + 1);

        if (index < 0) {
            index = index * -1;
        }

        return insert(arSorted, index, testNumbers);
    }

    public static boolean isOneSwap(int[] array) {
        // return true if a given array has exactly one swap to get sorted array
        // the swaped number may not be neighbors
        int j = 0;
        boolean res = false;
        int first = 0;
        int second = 0;
        int firstIndex = -1;
        int secondIndex = -1;

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                if (firstIndex == -1) {
                    firstIndex = i;
                }
                secondIndex = i + 1;
                j++;
            }
        }

        if (firstIndex != -1 && j <= 2) {

            for (int i = secondIndex + 1; i < array.length; i++) {
                if (array[i] < array[firstIndex] && array[i] >= array[i-1]) {
                    secondIndex = i;
                }
            }

            if (secondIndex - firstIndex > 1) {
                first = array[firstIndex];
                second = array[secondIndex];

                if ((firstIndex == 0 && second <= array[firstIndex + 1]
                        || firstIndex != 0 && array[firstIndex - 1] <= second && second <= array[firstIndex + 1])
                        && (secondIndex == array.length - 1 && first >= array[secondIndex - 1]
                                || secondIndex != array.length - 1 && array[secondIndex - 1] <= first
                                        && first >= array[secondIndex])) {
                    res = true;
                }
            }
        }

        return res;
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        int length = array.length;
        boolean flSort = false;
        do {
            length--;
            flSort = true;
            for (int i = 0; i < length; i++) {
                if (comparator.compare(array[i], array[i+1]) > 0) {
                    swap(array, i, i + 1);
                    flSort = false;
                }
            }
        } while(!flSort);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static <T> int binarySearch(T[] array, T key, Comparator<T> comparator) {
       
        int first = 0;
        int last = array.length - 1;
        int middleIndex = (first + last) / 2;
        int compInt = 0;

        while (first <= last && (compInt = comparator.compare(array[middleIndex], key)) != 0) {
            if (compInt < 0) {
                first = middleIndex + 1;
            } else {
                last = middleIndex - 1;
            }
            middleIndex = (first + last) / 2;
        }

        return first > last ? -(first + 1) : middleIndex;

    }

    public static <T> int binarySearch(T[] array, T key) {
        return binarySearch(array, key, (Comparator<T>) Comparator.naturalOrder());
    }

    public static <T> T[] insert(T [] array, int index, T item) {    

        T[] res = java.util.Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, res, index + 1, array.length - index);
        res[index] = item;
        return res;

    }

    public static <T> T[] find(T[] array, Predicate<T> predicate) {

        T[] result  = java.util.Arrays.copyOf(array, 0);
        for (int i = 0; i < array.length; i++) {
            if (predicate.test(array[i])) {
                result = insert(result, result.length, array[i]);                
            }
        }
        return result;

    }

    public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
        return find(array, predicate.negate());  
    }

    /**
     * 
     * @param chars - array of char primitives
     * @param mustBeRules - array of rules that must be true
     * @param mustNotBeRules - array of rules that must be fales
     * @return empty error message if array of chars matches all rules otherwise specific error message saying what rules don't match
     */
    public static String matchesRules(char[] chars, CharacterRule[] mustBeRules, CharacterRule[] mustNotBeRules) {
        //consider the class Character for rules definition

        setFlagInRules(chars, mustBeRules, true);
        setFlagInRules(chars, mustNotBeRules, false);

        String resMustBeError = checkRules(mustBeRules);
        String resMustNotBeError = checkRules(mustNotBeRules);
        
        return resMustBeError + (!resMustBeError.equals("") && !resMustNotBeError.equals("") ? ", " : "") + resMustNotBeError;

    }

    private static void setFlagInRules(char[] chars, CharacterRule[] currentRules, boolean flag) {
        for (int i = 0; i < currentRules.length; i++) {
            currentRules[i].flag = !flag;
            int j = 0;
            while (j < chars.length && currentRules[i].flag == !flag) {
                if (currentRules[i].predicate.test(chars[j])) {
                    currentRules[i].flag = flag;    
                }
                j++;
            }    
        }
    }

    private static String checkRules(CharacterRule[] currentRules) {
        String res = "";
        for (int i = 0; i < currentRules.length; i++) {
            if (!currentRules[i].flag) {
                res = res + (res.equals("") ? "" : ", ") + currentRules[i].errorMessage;
            }    
        }
        return res;
    }

}
