package problem.p705designhashset;

/**
 * 705. Design HashSet
 *
 * https://leetcode-cn.com/problems/design-hashset/
 *
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement MyHashSet class:
 *
 * void add(key) Inserts the value key into the HashSet.
 * bool contains(key) Returns whether the value key exists in the HashSet or not.
 * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 */

public class Solution {

    static class MyHashSet {
        private boolean[] set;

        public MyHashSet() {
            set = new boolean[(int) (1e6 + 1)];
        }
        public void add(int key) {
            set[key] = true;
        }
        public void remove(int key) {
            set[key] = false;
        }
        public boolean contains(int key) {
            return set[key];
        }
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        assert !set.contains(1);
        set.add(1);
        assert set.contains(1);
        set.remove(1);
        assert !set.contains(1);
    }

}
