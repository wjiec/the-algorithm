package problem.p1400constructkpalindromestrings;

/**
 * 1400. Construct K Palindrome Strings
 *
 * https://leetcode.cn/problems/construct-k-palindrome-strings/
 *
 * Given a string s and an integer k, return true if you can use all the characters in s
 * to construct k palindrome strings or false otherwise.
 */

public class Solution {

    public boolean canConstruct(String s, int k) {
        int[] map = new int[128];
        for (var c : s.toCharArray()) map[c]++;

        int l = 0, r = s.length();
        for (char i = 'a'; i <= 'z'; i++) {
            if (map[i] % 2 == 1) l++;
        }
        l = Math.max(1, l);

        return l <= k && k <= r;
    }

    public static void main(String[] args) {
        assert new Solution().canConstruct("annabelle", 2);
        assert !new Solution().canConstruct("leetcode", 3);
        assert new Solution().canConstruct("true", 4);
        assert new Solution().canConstruct("yzyzyzyzyzyzyzy", 2);
        assert !new Solution().canConstruct("cr", 7);
    }

}
