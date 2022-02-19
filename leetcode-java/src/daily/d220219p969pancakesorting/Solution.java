package daily.d220219p969pancakesorting;

import java.util.ArrayList;
import java.util.List;

/**
 * 969. Pancake Sorting
 *
 * https://leetcode-cn.com/problems/pancake-sorting/
 *
 * Given an array of integers arr, sort the array by performing a series of pancake flips.
 *
 * In one pancake flip we do the following steps:
 *
 * Choose an integer k where 1 <= k <= arr.length.
 * Reverse the sub-array arr[0...k-1] (0-indexed).
 *
 * For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3,
 * we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.
 *
 * Return an array of the k-values corresponding to a sequence of pancake flips that sort arr.
 *
 * Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.
 */

public class Solution {

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        for (int i = arr.length; i > 1; i--) {
            int max = 0;
            for (int j = 1; j < i; j++) {
                if (arr[j] > arr[max]) max = j;
            }
            if (max == i - 1) continue;

            reverse(arr, max);
            reverse(arr, i - 1);

            ans.add(max + 1);
            ans.add(i);
        }
        return ans;
    }

    private void reverse(int[] arr, int r) {
        for (int l = 0; l < r; l++, r--) {
            int t = arr[l]; arr[l] = arr[r]; arr[r] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pancakeSort(new int[]{3,2,4,1}));
        System.out.println(new Solution().pancakeSort(new int[]{1,2,3}));
    }

}
