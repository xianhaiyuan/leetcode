package org.practice.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class DefaultHashMap<K, V> extends HashMap<K, V> {
    Function<K, V> function;

    public DefaultHashMap(Supplier<V> supplier) {
        this.function = k -> supplier.get();
    }

    // computeIfAbsent 如果 key不存在则调用function，computeIfPresent 相反
    public V get(Object key) {
        return super.computeIfAbsent((K) key, this.function);
    }

    public static void main(String[] args) {
        DefaultHashMap<String, List<String>> map = new DefaultHashMap<String, List<String>>(ArrayList::new);
        map.get("1").add("123");
        map.get("2").add("234");

        map.forEach((k, v) ->System.out.println("k:" + k + ",v:" + v.toString()));
    }
}
