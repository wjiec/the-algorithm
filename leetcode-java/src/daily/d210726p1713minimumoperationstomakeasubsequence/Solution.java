package daily.d210726p1713minimumoperationstomakeasubsequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1713. Minimum Operations to Make a Subsequence
 *
 * https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/
 *
 * You are given an array target that consists of distinct integers and
 * another integer array arr that can have duplicates.
 *
 * In one operation, you can insert any integer at any position in arr.
 * For example, if arr = [1,4,1,2], you can add 3 in the middle and make it [1,4,3,1,2].
 * Note that you can insert the integer at the very beginning or end of the array.
 *
 * Return the minimum number of operations needed to make target a subsequence of arr.
 *
 * A subsequence of an array is a new array generated from the original array by
 * deleting some elements (possibly none) without changing the remaining elements' relative order.
 *
 * For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
 */

public class Solution {

    // @TODO
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, l = target.length; i < l; i++) map.put(target[i], i);

        List<Integer> list = new ArrayList<>();
        for (int val : arr) {
            if (map.containsKey(val)) {
                int idx = map.get(val), prev = binarySearch(list, idx);
                if (prev != list.size()) list.set(prev, idx);
                else list.add(idx);
            }
        }

        return target.length - list.size();
    }

    private int binarySearch(List<Integer> list, int target) {
        int l = 0, r = list.size() - 1, n = list.size();
        if (n == 0 || (list.get(r) < target)) return n;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) < target) l = mid + 1;
            else r = mid;
        }

        return l;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{5,1,3}, new int[]{9,4,2,3,4}) == 2;
        assert new Solution().minOperations(new int[]{6,4,8,1,3,2}, new int[]{4,7,6,2,3,8,6,1}) == 3;
    }

}
