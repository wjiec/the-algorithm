package weekly.w331.D;

import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public long minCost(int[] basket1, int[] basket2) {
        TreeMap<Integer, Integer> map1 = new TreeMap<>();
        TreeMap<Integer, Integer> map2 = new TreeMap<>();
        for (var v : basket1) map1.merge(v, 1, Integer::sum);
        for (var v : basket2) map2.merge(v, 1, Integer::sum);

        long ans = 0;
        while (!map1.isEmpty() && !map2.isEmpty()) {
            Map.Entry<Integer, Integer> e1 = map1.pollFirstEntry();
            Map.Entry<Integer, Integer> e2 = map2.pollFirstEntry();
            if (e1.getKey().equals(e2.getKey())) {
                int min = Math.min(e1.getValue(), e2.getValue());
            }
        }

        return 0;
    }

    public static void main(String[] args) {
    }

}
