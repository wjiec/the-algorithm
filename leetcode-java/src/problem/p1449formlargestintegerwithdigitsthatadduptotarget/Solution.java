package problem.p1449formlargestintegerwithdigitsthatadduptotarget;

/**
 * 1449. Form Largest Integer With Digits That Add up to Target
 *
 * https://leetcode.cn/problems/form-largest-integer-with-digits-that-add-up-to-target/
 *
 * Given an array of integers cost and an integer target, return the
 * maximum integer you can paint under the following rules:
 *
 * The cost of painting a digit (i + 1) is given by cost[i] (0-indexed).
 * The total cost used must be equal to target.
 * The integer does not have 0 digits.
 *
 * Since the answer may be very large, return it as a string. If there is no way
 * to paint any integer given the condition, return "0".
 */

@SuppressWarnings("ManualArrayCopy")
public class Solution {

    public String largestNumber(int[] cost, int target) {
        // dp[i] 表示目标值为 i 时, 所选择的数字数量, dp[i][0] 为本组的数字总数
        int[][] dp = new int[target + 1][10];
        for (int i = 1; i <= target; i++) {
            dp[i][0] = -1;
            for (int j = 0; j < cost.length; j++) {
                if (i - cost[j] >= 0) {
                    int prev = i - cost[j];
                    if (dp[prev][0] < 0) continue;

                    // 优先选数字多的
                    if (dp[prev][0] + 1 > dp[i][0]) copy(dp[i], dp[prev], j + 1);
                    if (dp[prev][0] + 1 == dp[i][0] && gt(dp[prev], dp[i], j + 1)) copy(dp[i], dp[prev], j + 1);
                }
            }
        }
        if (dp[target][0] < 0) return "0";

        StringBuilder sb = new StringBuilder();
        for (int i = cost.length - 1; i >= 0; i--) {
            sb.append(String.valueOf(i + 1).repeat(dp[target][i + 1]));
        }
        return sb.toString();
    }

    private void copy(int[] dst, int[] src, int add) {
        for (int i = 0; i < 10; i++) dst[i] = src[i];
        dst[add]++; dst[0]++;
    }

    // check left > right
    private boolean gt(int[] left, int[] right, int add) {
        for (int i = 9; i > 0; i--) {
            int delta = i == add ? 1 : 0;
            if (left[i] + delta > right[i]) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().largestNumber(new int[]{5,4,4,5,5,5,5,5,5}, 19).equals("9993");

        assert new Solution().largestNumber(new int[]{4,3,2,5,6,7,2,5,5}, 9).equals("7772");
        assert new Solution().largestNumber(new int[]{7,6,5,5,5,6,8,7,8}, 12).equals("85");
        assert new Solution().largestNumber(new int[]{2,4,6,2,4,6,4,4,4}, 5).equals("0");
        assert new Solution().largestNumber(new int[]{6,10,15,40,40,40,40,40,40}, 47).equals("32211");
    }

}
