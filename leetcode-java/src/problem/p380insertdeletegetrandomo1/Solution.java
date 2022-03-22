package problem.p380insertdeletegetrandomo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 380. Insert Delete GetRandom O(1)
 *
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 *
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 *
 * bool insert(int val) Inserts an item val into the set if not present.
 * Returns true if the item was not present, false otherwise.
 *
 * bool remove(int val) Removes an item val from the set if present.
 * Returns true if the item was present, false otherwise.
 *
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one
 * element exists when this method is called). Each element must have the same probability of being returned.
 *
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 */

public class Solution {

    private static class RandomizedSet {
        private final List<Integer> list = new ArrayList<>();
        private final Map<Integer, Integer> map = new HashMap<>();
        public RandomizedSet() {}

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;

            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;

            int last = list.get(list.size() - 1);
            int index = map.get(val);

            // swap
            map.put(last, index);
            list.set(index, last);

            // remove
            list.remove(list.size() - 1);
            map.remove(val);

            return true;
        }

        public int getRandom() { return list.get(ThreadLocalRandom.current().nextInt(list.size())); }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        assert randomizedSet.insert(1);
        assert !randomizedSet.remove(2);
        assert randomizedSet.insert(2);
        System.out.println(randomizedSet.getRandom());
        assert randomizedSet.remove(1);
        assert !randomizedSet.insert(2);
        System.out.println(randomizedSet.getRandom());
    }

}
