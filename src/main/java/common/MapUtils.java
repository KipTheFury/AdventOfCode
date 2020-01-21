package common;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MapUtils {

    public static <K, V extends Comparable<V>> Map.Entry<K, V> getMaxValue(Map<K, V> map) {

        return Collections.max(map.entrySet(), Comparator.comparing(Map.Entry::getValue));
    }

    public static <K, V extends Comparable<V>> Map.Entry<K, V> getMinValue(Map<K, V> map) {

        return Collections.min(map.entrySet(), Comparator.comparing(Map.Entry::getValue));
    }
}
