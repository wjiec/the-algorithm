package problem.p2080rangefrequencyqueries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 2080. Range Frequency Queries
 *
 * https://leetcode.cn/problems/range-frequency-queries/
 *
 * Design a data structure to find the frequency of a given value in a given subarray.
 *
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 *
 * Implement the RangeFreqQuery class:
 *
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
 * int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
 * A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that
 * contains the elements of nums between indices left and right (inclusive).
 */

public class Solution {

    private static class RangeFreqQuery {
        private final HashMap<Integer, List<Integer>> map = new HashMap<>();
        public RangeFreqQuery(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                map.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
            }
        }

        public int query(int left, int right, int value) {
            List<Integer> index = map.get(value);
            if (index != null) {
                int r = find(index, right + 1);
                int l = find(index, left);
                return r - l;
            }
            return 0;
        }

        private int find(List<Integer> array, int target) {
            int l = 0, r = array.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (array.get(mid) >= target) r = mid;
                else l = mid + 1;
            }
            return l;
        }
    }

    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        assert rangeFreqQuery.query(1, 2, 4) == 1;
        assert rangeFreqQuery.query(0, 11, 33) == 2;
    }

}
