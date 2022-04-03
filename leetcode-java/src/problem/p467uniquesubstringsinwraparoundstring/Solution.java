package problem.p467uniquesubstringsinwraparoundstring;

import common.TODO;

/**
 * 467. Unique Substrings in Wraparound String
 *
 * https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string/
 *
 * We define the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz",
 * so s will look like this:
 *
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 *
 * Given a string p, return the number of unique non-empty substrings of p are present in s.
 */

public class Solution {

    @TODO(url = "https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string/solution/")
    public int findSubstringInWraproundString(String p) {
        int[] map = new int[128];
        int c = 1, n = p.length();
        for (int i = 0; i < n; i++) {
            if (i == 0 || next(p.charAt(i - 1)) != p.charAt(i)) c = 1; else c++;
            map[p.charAt(i)] = Math.max(map[p.charAt(i)], c);
        }

        int ans = 0;
        for (var v : map) ans += v;
        return ans;
    }

    private char next(char c) {
        return c == 'z' ? 'a' : (char) (c + 1);
    }

    public static void main(String[] args) {
        assert new Solution().findSubstringInWraproundString("a") == 1;
        assert new Solution().findSubstringInWraproundString("cac") == 2;
        assert new Solution().findSubstringInWraproundString("zab") == 6;

        assert new Solution().findSubstringInWraproundString("uvwyzabcdefghijklmnopqrstuvwxyz") == 403;
        assert new Solution().findSubstringInWraproundString("uvwyzabcdefghijklmnopqrstuvwxyzabcd") == 507;
    }

}
