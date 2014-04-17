package weiboclient4j.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Hover Ruan
 */
public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static <E> List<E> newArrayList(E... elements) {
        List<E> list = new ArrayList<E>(elements.length);
        Collections.addAll(list, elements);

        return list;
    }

    public static <T> T firstItem(List<T> list) {
        return list != null && list.size() > 0 ? list.get(0) : null;
    }
}
