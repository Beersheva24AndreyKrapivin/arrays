package telran.util;

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

        int index = (binarySearch(arSorted, testNumbers) + 1) * -1;

        return insert(arSorted, index, testNumbers);
    }

    public static boolean isOneSwap(int[] array) {
        // TODO
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
                if (first == 0) {
                    first = array[i];
                    firstIndex = i;
                } else {
                    second = array[i + 1];
                    secondIndex = i + 1;
                }
                j++;
            }
        }

        if (j == 2) {
            if ((firstIndex == 0 && second <= array[firstIndex + 1]
                || firstIndex != 0 && array[firstIndex - 1] <= second && second <= array[firstIndex + 1])
                && (secondIndex == array.length - 1 && first >= array[secondIndex - 1]
                || secondIndex != array.length - 1 && array[secondIndex - 1] <= first && first >= array[secondIndex])) {
                res = true;
            }
        }

        return res;
    }

}
