package telran.util.test;

import java.util.Comparator;

public class ComparatorLenth implements Comparator<String> {

    @Override
    public int compare(String arg0, String arg1) {
        return arg0.length() - arg1.length();
    }
    
}
