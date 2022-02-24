package problem.p170twosumiiidatastructuredesign;

import java.util.*;

/**
 * 170. Two Sum III - Data structure design
 *
 * https://leetcode-cn.com/problems/two-sum-iii-data-structure-design/
 *
 * Design a data structure that accepts a stream of integers and
 * checks if it has a pair of integers that sum up to a particular value.
 *
 * Implement the TwoSum class:
 *
 * TwoSum() Initializes the TwoSum object, with an empty array initially.
 *
 * void add(int number) Adds number to the data structure.
 *
 * boolean find(int value) Returns true if there exists any pair of numbers
 * whose sum is equal to value, otherwise, it returns false.
 */

public class Solution {

    private static class TwoSum {
        private final Map<Integer, Integer> map = new HashMap();
        public TwoSum() {}

        public void add(int number) { map.merge(number, 1, Integer::sum); }

        public boolean find(int value) {
            for (var e : map.keySet()) {
                Integer cnt = map.get(value - e);
                if (cnt != null && (value - e != e || cnt > 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        ts.add(1);
        ts.add(3);
        ts.add(5);
        assert ts.find(4);
        assert !ts.find(7);
    }

}
