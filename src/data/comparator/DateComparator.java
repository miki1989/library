package data.comparator;

import data.Publication;

import java.util.Comparator;

public class DateComparator implements Comparator<Publication> {
    @Override
    public int compare(Publication o1, Publication o2) {
        if (o1 == null && o2 == null)
            return 0;
        if (o1 == null)
            return 1;
        if (o2 == null)
            return -1;

        Integer i1 = o1.getYear();
        Integer i2 = o2.getYear();
        return -i1.compareTo(i2);
    }
}
