package problem.p2121intervalsbetweenidenticalelements;

import common.Checker;
import common.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2121. Intervals Between Identical Elements
 *
 * https://leetcode.cn/problems/intervals-between-identical-elements/
 *
 * You are given a 0-indexed array of n integers arr.
 *
 * The interval between two elements in arr is defined as the absolute difference between their indices.
 * More formally, the interval between arr[i] and arr[j] is |i - j|.
 *
 * Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and
 * each element in arr with the same value as arr[i].
 *
 * Note: |x| is the absolute value of x.
 */

public class Solution {

    @Tag({"数组中的其他数字转变为其中某一个数"})
    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], v -> new ArrayList<>())
                .add(i);
        }

        long[] ans = new long[arr.length];
        for (var index : map.values()) {
            long suffix = 0, first = index.get(0);
            for (var val : index) suffix += val - first;

            long prefix = 0, n = index.size();
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    // 以该位置为中心，计算前后移动的差值
                    prefix += (long) i * (index.get(i) - index.get(i - 1));
                    suffix -= (n - i) * (index.get(i) - index.get(i - 1));
                }

                int curr = index.get(i);
                ans[curr] = prefix + suffix;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getDistances(new int[]{2,1,3,1,2,3,3}), new long[]{4,2,7,2,4,4,5});
        assert Checker.check(new Solution().getDistances(new int[]{10,5,10,10}), new long[]{5,0,3,4});
    }

}
