package problem.p467uniquesubstringsinwraparoundstring;

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

    public int findSubstringInWraproundString(String p) {
        boolean[] map = new boolean[128];
        for (int l = 0, r = 1, n = p.length(); l < n; r++) {
            if (next(p.charAt(r - 1)) != p.charAt(r)) {
                while (l < r) map[p.charAt(l++)] = true;
                l = r;
            }
        }

        int ans = 0;
        for (char l = 'a'; l <= 'z'; l++) {
            if (map[l]) {
                char r = (char) (l + 1);
                while (r <= 'z' && map[r]) r++;
                if (l + 1 == r) ans++;
                else ans += (1 + r - l) * (r - l) / 2;
                l = r;
            }
        }
        return ans;
    }

    private char next(char c) {
        return c == 'z' ? 'a' : (char) (c + 1);
    }

    public static void main(String[] args) {
        assert new Solution().findSubstringInWraproundString("a") == 1;
        assert new Solution().findSubstringInWraproundString("cac") == 2;
        assert new Solution().findSubstringInWraproundString("zab") == 6;
    }

}
