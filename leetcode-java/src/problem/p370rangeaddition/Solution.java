package problem.p370rangeaddition;

/**
 * 370. Range Addition
 *
 * https://leetcode.cn/problems/range-addition/
 *
 * You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
 *
 * You have an array arr of length length with all zeros, and you have some operation to apply on arr.
 * In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
 *
 * Return arr after applying all the updates.
 */

public class Solution {

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (var update : updates) {
            ans[update[0]] += update[2];
            if (update[1] + 1 < length) ans[update[1] + 1] -= update[2];
        }
        for (int i = 1; i < length; i++) ans[i] += ans[i - 1];
        return ans;
    }

}
