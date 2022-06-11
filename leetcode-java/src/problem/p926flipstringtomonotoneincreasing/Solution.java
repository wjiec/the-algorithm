package problem.p926flipstringtomonotoneincreasing;

/**
 * 926. Flip String to Monotone Increasing
 *
 * https://leetcode.cn/problems/flip-string-to-monotone-increasing/
 *
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none),
 * followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 */

public class Solution {

    public int minFlipsMonoIncr(String s) {
        int ones = 0, ans = 0;
        for (var c : s.toCharArray()) {
            // 如果 c == '1' 则这个字符不影响翻转次数
            if (c == '1') ones++;
            // 否则 c == '0' 我们有两种选择
            //      1. 把前面所有的 '0' (ans) 翻转为 '1' (ans + 1)
            //      2. 把前面所有的 '1' (ones) 翻转为 '0'
            else ans = Math.min(ans + 1, ones);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minFlipsMonoIncr("0101100011") == 3;

        assert new Solution().minFlipsMonoIncr("00110") == 1;
        assert new Solution().minFlipsMonoIncr("010110") == 2;
        assert new Solution().minFlipsMonoIncr("00011000") == 2;
    }

}
