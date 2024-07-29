package weekly.w408.C;

/**
 * 3234. Count the Number of Substrings With Dominant Ones
 *
 * https://leetcode.cn/contest/weekly-contest-408/problems/count-the-number-of-substrings-with-dominant-ones/
 *
 * You are given a binary string s.
 *
 * Return the number of substrings with dominant ones.
 *
 * A string has dominant ones if the number of ones in the string is greater than
 * or equal to the square of the number of zeros in the string.
 */

public class Solution {

    public int numberOfSubstrings(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int[] prefix = new int[n]; // 记录每个0左边有多少个1
        int[] suffix = new int[n]; // 记录每个0右边有多少个1
        for (int i = 1; i < n; i++) {
            if (chars[i - 1] == '1') {
                prefix[i] = prefix[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (chars[i + 1] == '1') {
                suffix[i] = suffix[i + 1] + 1;
            }
        }

        int ans = 0, ones = 0;
        // 没有0的话, 所有的1都可以自由排列
        for (int i = 0, c = 0; i < n; i++) {
            if (chars[i] == '1') {
                ans += ++c;
                ones++;
            } else {
                if (prefix[i] > 0 || suffix[i] > 0) {
                    ans += prefix[i] + suffix[i];
                }
            }
        }

        // 枚举一共可以有多少个0
        for (int c = 2; c <= (int) Math.sqrt(ones); c++) {
            for (int l = 0, r = 0, c0 = 0; r < n; r++) {
                c0 += chars[r] ^ '1';
                while (c0 > c) c0 -= chars[l++] ^ '1';
                if (c0 == c && (prefix[l] > 0 || suffix[r] > 0)) {
                    ans += Math.max(prefix[l], 1) * Math.max(suffix[r], 1);
                    c0 -= chars[l++] ^ '1'; // move next
                }
            }
        }

        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSubstrings("101") == 5;

        assert new Solution().numberOfSubstrings("00011") == 5;
        assert new Solution().numberOfSubstrings("101101") == 16;
    }

}
