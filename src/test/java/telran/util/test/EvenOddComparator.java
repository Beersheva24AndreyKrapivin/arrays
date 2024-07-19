package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer arg0, Integer arg1) {
        int res = 0;

        if (arg0 % 2 != arg1 % 2) {
            res = arg0 % 2 == 0 ? -1 : 1;
        } else {
            res = arg0 % 2 == 0 ? arg0 - arg1 : arg1 - arg0;
        }

        return res;

    }

}
