package org.practice.map;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMerge {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> keys = new ArrayList<>();
        keys.add(1);
        keys.add(2);

        map.put(1, 10);

        keys.forEach(key -> map.merge(key, 3, Integer::sum));

        /*keys.forEach(key -> map.merge(key, 3, (v1,v2)->{
            System.out.println(v1);
            return v1+v2;
        }));*/

        map.forEach((k,v) -> System.out.println("k:"+k+",v:"+v));
    }
}
