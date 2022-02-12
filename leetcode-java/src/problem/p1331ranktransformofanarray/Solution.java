package problem.p1331ranktransformofanarray;

import common.Checker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1331. Rank Transform of an Array
 *
 * https://leetcode-cn.com/problems/rank-transform-of-an-array/
 *
 * Given an array of integers arr, replace each element with its rank.
 *
 * The rank represents how large the element is. The rank has the following rules:
 *
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 */

public class Solution {

    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = new int[arr.length];
        System.arraycopy(arr, 0, sorted, 0, arr.length);
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 1; i < arr.length; i++) {
            if (map.putIfAbsent(sorted[i], j) == null) {
                j++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().arrayRankTransform(new int[]{40,10,20,30}), new int[]{4,1,2,3});
        assert Checker.check(new Solution().arrayRankTransform(new int[]{100,100,100}), new int[]{1,1,1});
        assert Checker.check(new Solution().arrayRankTransform(new int[]{37,12,28,9,100,56,80,5,12}), new int[]{5,3,4,2,8,6,7,1,3});
    }

}
