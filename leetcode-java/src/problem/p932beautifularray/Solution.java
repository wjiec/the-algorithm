package problem.p932beautifularray;

import common.PrettyPrinter;
import common.TODO;

import java.util.HashMap;
import java.util.Map;

/**
 * 932. Beautiful Array
 *
 * https://leetcode.cn/problems/beautiful-array/
 *
 * An array nums of length n is beautiful if:
 *
 * nums is a permutation of the integers in the range [1, n].
 * For every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
 * Given the integer n, return any beautiful array nums of length n.
 * There will be at least one valid answer for the given n.
 */

public class Solution {

    private final Map<Integer, int[]> memo = new HashMap<>();

    public int[] beautifulArray(int n) {
        return backtrack(n);
    }

    @TODO
    private int[] backtrack(int n) {
        if (memo.containsKey(n)) return memo.get(n);

        int[] ans = new int[n];
        if (n != 1) {
            int idx = 0;
            for (int v : backtrack((n + 1) / 2)) {
                ans[idx++] = 2 * v - 1;
            }
            for (int v : backtrack(n / 2)) {
                ans[idx++] = 2 * v;
            }
        } else ans[0] = 1;

        memo.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().beautifulArray(4));
        PrettyPrinter.println(new Solution().beautifulArray(5));
    }

}
