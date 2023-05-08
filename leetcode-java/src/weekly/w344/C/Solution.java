package weekly.w344.C;

/**
 * 6418. Number of Adjacent Elements With the Same Color
 *
 * https://leetcode.cn/contest/weekly-contest-344/problems/number-of-adjacent-elements-with-the-same-color/
 *
 * There is a 0-indexed array nums of length n. Initially, all elements are uncolored (has a value of 0).
 *
 * You are given a 2D integer array queries where queries[i] = [indexi, colori].
 *
 * For each query, you color the index indexi with the color colori in the array nums.
 *
 * Return an array answer of the same length as queries where answer[i] is the number of adjacent
 * elements with the same color after the ith query.
 *
 * More formally, answer[i] is the number of indices j, such that 0 <= j < n - 1 and
 * nums[j] == nums[j + 1] and nums[j] != 0 after the ith query.
 */

@SuppressWarnings("DuplicateExpressions")
public class Solution {

    public int[] colorTheArray(int n, int[][] queries) {
        int curr = 0;
        int[] colors = new int[n + 2];
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0] + 1, cls = queries[i][1];
            if (colors[idx] != 0) {
                curr -= colors[idx] == colors[idx - 1] ? 1 : 0;
                curr -= colors[idx] == colors[idx + 1] ? 1 : 0;
            }

            colors[idx] = cls;
            curr += colors[idx] == colors[idx - 1] ? 1 : 0;
            curr += colors[idx] == colors[idx + 1] ? 1 : 0;
            ans[i] = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
