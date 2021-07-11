package daily.d210710p981timebasedkeyvaluestore;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 981. Time Based Key-Value Store
 *
 * https://leetcode-cn.com/problems/time-based-key-value-store/
 *
 * Design a time-based key-value data structure that can store multiple values
 * for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores
 * the key key with the value value at the given time timestamp.
 *
 * String get(String key, int timestamp) Returns a value such that set was called previously,
 * with timestamp_prev <= timestamp. If there are multiple such values,
 * it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 */

public class Solution {

    private static class TimeMap {
        private final Map<String, TreeMap<Integer, String>> map = new HashMap<>();

        public TimeMap() {}

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (map.containsKey(key)) {
                var index = map.get(key).floorKey(timestamp);
                if (index != null) return map.get(key).get(index);
            }
            return "";
        }
    }

    public static void main(String[] args) {
        TimeMap map = new TimeMap();
        assert map.get("hello", 1).equals("");
        map.set("foo", "bar", 1);
        assert map.get("foo", 1).equals("bar");
        assert map.get("foo", 3).equals("bar");
        map.set("foo", "bar2", 4);
        assert map.get("foo", 3).equals("bar");
        assert map.get("foo", 4).equals("bar2");
        assert map.get("foo", 5).equals("bar2");
        map.set("foo", "bar3", 6);
        assert map.get("foo", 3).equals("bar");
        assert map.get("foo", 4).equals("bar2");
        assert map.get("foo", 5).equals("bar2");
        assert map.get("foo", 6).equals("bar3");
        assert map.get("foo", 7).equals("bar3");
    }

}
