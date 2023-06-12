package pro.javadev.sql.internal.common;

import java.util.Comparator;

public class PriorityComparator<T extends Priority> implements Comparator<T> {

    @Override
    public int compare(T objectA, T objectB) {
        return objectA.priority() - objectB.priority();
    }

}
