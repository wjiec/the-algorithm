package problem.p1963minimumnumberofswapstomakethestringbalanced;

/**
 * 1963. Minimum Number of Swaps to Make the String Balanced
 *
 * https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-string-balanced/
 *
 * You are given a 0-indexed string s of even length n. The string consists of
 * exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.
 *
 * A string is called balanced if and only if:
 *
 * It is the empty string, or
 * It can be written as AB, where both A and B are balanced strings, or
 * It can be written as [C], where C is a balanced string.
 * You may swap the brackets at any two indices any number of times.
 *
 * Return the minimum number of swaps to make s balanced.
 */

public class Solution {

    public int minSwaps(String s) {
        int ans = 0, state = 0;
        char[] chars = s.toCharArray();
        for (int l = 0, r = chars.length - 1; l < r; l++) {
            if (state == 0 && chars[l] == ']') {
                ans++;
                while (l < r && chars[r] != '[') r--;
                char temp = chars[l]; chars[l] = chars[r]; chars[r] = temp;
            }
            state += chars[l] == '[' ? 1 : -1;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSwaps("[[[]]]][][]][[]]][[[") == 2;

        assert new Solution().minSwaps("][][") == 1;
        assert new Solution().minSwaps("]]][[[") == 2;
        assert new Solution().minSwaps("[]") == 0;
    }

}
