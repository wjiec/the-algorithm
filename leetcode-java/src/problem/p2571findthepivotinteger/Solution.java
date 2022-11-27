package problem.p2571findthepivotinteger;

/**
 * 6245. Find the Pivot Integer
 *
 * https://leetcode.cn/problems/find-the-pivot-integer/
 *
 * Given a positive integer n, find the pivot integer x such that:
 *
 * The sum of all elements between 1 and x inclusively equals the
 * sum of all elements between x and n inclusively.
 *
 * Return the pivot integer x. If no such integer exists, return -1.
 * It is guaranteed that there will be at most one pivot index for the given input.
 */

public class Solution {

    public int pivotInteger(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) sum += i;

        int curr = 0;
        for (int i = 1; i <= n; i++) {
            curr += i;
            if (curr == sum) return i;
            sum -= i;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().pivotInteger(8) == 6;
        assert new Solution().pivotInteger(1) == 1;
        assert new Solution().pivotInteger(4) == -1;
    }

}
