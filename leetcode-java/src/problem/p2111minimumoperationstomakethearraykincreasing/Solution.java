package problem.p2111minimumoperationstomakethearraykincreasing;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2111. Minimum Operations to Make the Array K-Increasing
 *
 * https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing
 *
 * You are given a 0-indexed array arr consisting of n positive integers, and a positive integer k.
 *
 * The array arr is called K-increasing if arr[i-k] <= arr[i] holds for every index i, where k <= i <= n-1.
 *
 * For example, arr = [4, 1, 5, 2, 6, 2] is K-increasing for k = 2 because:
 * arr[0] <= arr[2] (4 <= 5)
 * arr[1] <= arr[3] (1 <= 2)
 * arr[2] <= arr[4] (5 <= 6)
 * arr[3] <= arr[5] (2 <= 2)
 * However, the same arr is not K-increasing for k = 1 (because arr[0] > arr[1]) or k = 3 (because arr[0] > arr[3]).
 * In one operation, you can choose an index i and change arr[i] into any positive integer.
 *
 * Return the minimum number of operations required to make the array K-increasing for the given k.
 * @noinspection unchecked
 */

public class Solution {

    public int kIncreasing(int[] arr, int k) {
        // 由于两两之间进行对比的下标都相差k, 我们首先对其进行分组
        List<Integer>[] groups = new List[k];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int i = 0; i < arr.length; i++) {
            groups[i % k].add(arr[i]);
        }

        // 然后求每个组都符合非递减的最小变化数量
        int ans = 0;
        for (var group : groups) {
            ans += kIncreasing(group);
        }

        return ans;
    }

    // 求使得 list 变成非递减的最小修改次数
    private int kIncreasing(List<Integer> list) {
        Integer[] index = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) index[i] = i;
        Arrays.sort(index, (a, b) -> list.get(a).equals(list.get(b)) ? (a - b) : (list.get(a) - list.get(b)));

        // 求最长非递减子序列, 然后修改其他的数为与相邻的值一样
        List<Integer> p = new ArrayList<>();
        for (var v : index) {
            int idx = Collections.binarySearch(p, v);
            if (idx < 0) idx = -idx - 1;

            if (idx >= p.size()) p.add(v);
            else p.set(idx, v);
        }

        return list.size() - p.size();
    }

    @Tag({"最长递增子序列", "最长非递减子序列"})
    private static class Optimization {
        // 求最长严格递增子序列需要二分找到大于或等于当前元素的元素位置（即 C++ 中的 lower_bound）；
        // 求最长不降子序列需要二分找到大于当前元素的元素位置（即 C++ 中的 upper_bound）。
        public int kIncreasing(int[] arr, int k) {
            int ans = 0;
            for (int i = 0; i < k; i++) {
                int len = 0;
                List<Integer> lis = new ArrayList<>();
                for (int j = i; j < arr.length; j += k) {
                    len++;
                    int idx = Collections.binarySearch(lis, arr[j] + 1);
                    if (idx < 0) idx = -idx - 1;

                    if (idx >= lis.size()) lis.add(arr[j]);
                    else lis.set(idx, arr[j]);
                }
                ans += len - lis.size();
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().kIncreasing(new int[]{4,1,5,2,6,2}, 3) == 2;
        assert new Solution().kIncreasing(new int[]{4,1,5,2,6,2}, 2) == 0;
        assert new Solution().kIncreasing(new int[]{5,4,3,2,1}, 1) == 4;
        assert new Solution().kIncreasing(new int[]{12,6,12,6,14,2,13,17,3,8,11,7,4,11,18,8,8,3}, 1) == 12;

        assert new Optimization().kIncreasing(new int[]{4,1,5,2,6,2}, 3) == 2;
        assert new Optimization().kIncreasing(new int[]{4,1,5,2,6,2}, 2) == 0;
        assert new Optimization().kIncreasing(new int[]{5,4,3,2,1}, 1) == 4;
        assert new Optimization().kIncreasing(new int[]{12,6,12,6,14,2,13,17,3,8,11,7,4,11,18,8,8,3}, 1) == 12;
    }

}
