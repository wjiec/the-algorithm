package daily.d211114p677mapsumpairs;

import java.util.HashMap;
import java.util.Map;

/**
 * 677. Map Sum Pairs
 *
 * https://leetcode-cn.com/problems/map-sum-pairs/
 *
 * Design a map that allows you to do the following:
 *
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 *
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 *
 * void insert(String key, int val) Inserts the key-val pair into the map.
 * If the key already existed, the original key-value pair will be overridden to the new one.
 *
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 */

public class Solution {

    private static class MapSum {
        private final Map<String, Integer> map = new HashMap<>();
        public MapSum() {}

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int ans = 0;
            for (var k : map.keySet()) {
                if (k.indexOf(prefix) == 0) {
                    ans += map.get(k);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        MapSum map = new MapSum();
        map.insert("apple", 3);
        assert map.sum("ap") == 3;
        map.insert("app", 2);
        assert map.sum("ap") == 5;
    }

}
