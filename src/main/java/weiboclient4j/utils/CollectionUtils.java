package weiboclient4j.utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Hover Ruan
 */
public class CollectionUtils {
    public static <E> ArrayList<E> newArrayList(E... elements) {
        ArrayList<E> list = new ArrayList<E>(elements.length);
        Collections.addAll(list, elements);

        return list;
    }
}
