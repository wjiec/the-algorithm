package problem.p381insertdeletegetrandomo1duplicatesallowed;

import java.util.*;

/**
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 *
 * https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed
 *
 * RandomizedCollection is a data structure that contains a collection of numbers,
 * possibly duplicates (i.e., a multiset). It should support inserting and removing
 * specific elements and also reporting a random element.
 *
 * Implement the RandomizedCollection class:
 *
 * RandomizedCollection() Initializes the empty RandomizedCollection object.
 *
 * bool insert(int val) Inserts an item val into the multiset, even if the item is already present.
 * Returns true if the item is not present, false otherwise.
 *
 * bool remove(int val) Removes an item val from the multiset if present. Returns true if
 * the item is present, false otherwise. Note that if val has multiple occurrences in the
 * multiset, we only remove one of them.
 *
 * int getRandom() Returns a random element from the current multiset of elements. The probability of
 * each element being returned is linearly related to the number of the same values the multiset contains.
 *
 * You must implement the functions of the class such that each function works on average O(1) time complexity.
 * Note: The test cases are generated such that getRandom will only be called if there is at
 * least one item in the RandomizedCollection.
 */

public class Solution {

    private static class RandomizedCollection {
        private final Random random = new Random();
        private final List<Integer> list = new ArrayList<>();
        private final Map<Integer, Set<Integer>> map = new HashMap<>();
        public RandomizedCollection() {}
        public int getRandom() { return list.get(random.nextInt(list.size())); }

        public boolean insert(int val) {
            list.add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(list.size() - 1);
            return map.get(val).size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;

            int removeIdx = map.get(val).iterator().next();
            map.get(val).remove(removeIdx);
            if (map.get(val).size() == 0) map.remove(val);

            int lastIdx = list.size() - 1;
            if (removeIdx != lastIdx) {
                int lastEl = list.get(lastIdx);
                map.get(lastEl).remove(lastIdx);
                map.get(lastEl).add(removeIdx);
                list.set(removeIdx, lastEl);
            }
            list.remove(lastIdx);

            return true;
        }
    }

    public static void main(String[] args) {
        RandomizedCollection rand = new RandomizedCollection();
        assert rand.insert(1);
        assert rand.remove(1);
        assert rand.insert(1);
        assert rand.getRandom() == 1;
    }

}
