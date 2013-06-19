package weiboclient4j.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class CollectionUtils {
    public static <E> ArrayList<E> newArrayList(E... elements) {
        ArrayList<E> list = new ArrayList<E>(elements.length);
        Collections.addAll(list, elements);

        return list;
    }

    public static <T> T firstItem(List<T> list) {
        return list != null && list.size() > 0 ? list.get(0) : null;
    }
}
