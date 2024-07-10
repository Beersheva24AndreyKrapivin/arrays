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

}
