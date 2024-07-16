package telran.util.test;

import java.util.Comparator;

public class ComparatorDouble implements Comparator <Double> {

    @Override
    public int compare(Double arg0, Double arg1) {
        return Double.compare(arg0, arg1);
    }

}
