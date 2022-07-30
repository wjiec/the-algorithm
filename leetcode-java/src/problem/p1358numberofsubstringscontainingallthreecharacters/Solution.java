package problem.p1358numberofsubstringscontainingallthreecharacters;

/**
 * 1358. Number of Substrings Containing All Three Characters
 *
 * https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters/
 *
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 */

public class Solution {

    public int numberOfSubstrings(String s) {
        int a = 0, b = 0, c = 0, n = s.length(), ans = 0;
        for (int l = 0, r = -1; l < n; ) {
            while (r < n && (a == 0 || b == 0 || c == 0)) {
                if (++r == n) break;
                switch (s.charAt(r)) {
                    case 'a' -> a++;
                    case 'b' -> b++;
                    case 'c' -> c++;
                }
            }

            ans += n - r;
            switch (s.charAt(l++)) {
                case 'a' -> a--;
                case 'b' -> b--;
                case 'c' -> c--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSubstrings("abcabc") == 10;
        assert new Solution().numberOfSubstrings("aaacb") == 3;
        assert new Solution().numberOfSubstrings("abc") == 1;
    }

}
