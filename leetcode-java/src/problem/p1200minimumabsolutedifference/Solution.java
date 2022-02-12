package problem.p1200minimumabsolutedifference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1200. Minimum Absolute Difference
 *
 * https://leetcode-cn.com/problems/minimum-absolute-difference/
 *
 * Given an array of distinct integers arr, find all pairs of elements with
 * the minimum absolute difference of any two elements. 
 *
 * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
 *
 * a, b are from arr
 * a < b
 * b - a equals to the minimum absolute difference of any two elements in arr
 */

public class Solution {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);

        int max = Integer.MAX_VALUE;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1, l = arr.length; i < l; i++) {
            int diff = arr[i] - arr[i - 1];
            if (diff < max) {
                max = diff;
                ans.clear();
            }

            if (diff == max) ans.add(List.of(arr[i - 1], arr[i]));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minimumAbsDifference(new int[]{4,2,1,3}));
        System.out.println(new Solution().minimumAbsDifference(new int[]{1,3,6,10,15}));
        System.out.println(new Solution().minimumAbsDifference(new int[]{3,8,-10,23,19,-4,-14,27}));
    }

}
