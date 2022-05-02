package problem.p670maximumswap;

/**
 * 670. Maximum Swap
 *
 * https://leetcode-cn.com/problems/maximum-swap/
 *
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 */

public class Solution {

    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0, n = chars.length; i < n; i++) {
            int max = -1;
            for (int j = n - 1; j > i; j--) {
                // 从后往前找到一个比 chars[i] 大的数字，并用这个数字替换当前数字
                // 如果后面有多个相同的最大数字，则应该选取最后的那个。这样有助于将最小的数字
                // 放在最后面，而不是中间
                // 原始值：92399495
                // 可选项：99329495
                if (chars[j] > chars[i] && (max == -1 || chars[j] > chars[max]))
                    max = j;
            }

            if (max != -1) {
                char t = chars[i];
                chars[i] = chars[max];
                chars[max] = t;
                return Integer.parseInt(new String(chars));
            }
        }

        return num;
    }

    public static void main(String[] args) {
        assert new Solution().maximumSwap(2736) == 7236;
        assert new Solution().maximumSwap(9973) == 9973;
    }

}
