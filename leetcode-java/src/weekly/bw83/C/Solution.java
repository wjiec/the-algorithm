package weekly.bw83.C;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 6130. Design a Number Container System
 *
 * https://leetcode.cn/contest/biweekly-contest-83/problems/design-a-number-container-system/
 *
 * Design a number container system that can do the following:
 *
 * Insert or Replace a number at the given index in the system.
 * Return the smallest index for the given number in the system.
 * Implement the NumberContainers class:
 *
 * NumberContainers() Initializes the number container system.
 *
 * void change(int index, int number) Fills the container at index with the number.
 * If there is already a number at that index, replace it.
 *
 * int find(int number) Returns the smallest index for the given number, or -1 if
 * there is no index that is filled by number in the system.
 */

public class Solution {

    private static class NumberContainers {
        private final Map<Integer, Integer> map = new HashMap<>();
        private final Map<Integer, TreeSet<Integer>> idx = new HashMap<>();
        public NumberContainers() {}

        public void change(int index, int number) {
            if (map.containsKey(index)) {
                int v = map.get(index);
                idx.get(v).remove(index);
            }

            map.put(index, number);
            idx.computeIfAbsent(number, v -> new TreeSet<>()).add(index);
        }

        public int find(int number) {
            TreeSet<Integer> s = idx.get(number);
            if (s != null && !s.isEmpty()) {
                return s.first();
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        NumberContainers nc = new NumberContainers();
        assert nc.find(10) == -1;
        nc.change(2, 10);
        nc.change(1, 10);
        nc.change(3, 10);
        nc.change(5, 10);
        assert nc.find(10) == 1;
        nc.change(1, 20);
        assert nc.find(10) == 2;
    }

}
