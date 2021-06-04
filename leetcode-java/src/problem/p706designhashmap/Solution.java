package problem.p706designhashmap;

/**
 * 706. Design HashMap
 *
 * https://leetcode-cn.com/problems/design-hashmap/
 *
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the MyHashMap class:
 *
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap.
 * If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped,
 * or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 */

public class Solution {

    static class MyHashMap {
        private static int N = (int) (1e6 + 1);
        private int[] values;

        /** Initialize your data structure here. */
        public MyHashMap() {
            values = new int[N];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            values[key] = value + 1;
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            return values[key] - 1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            values[key] = 0;
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        assert map.get(1) == -1;
        assert map.get(2) == -1;
        map.put(1, 0);
        map.put(2, 1 << 16);
        assert map.get(1) == 0;
        assert map.get(3) == -1;
        assert map.get(2) == 1 << 16;
    }

}
