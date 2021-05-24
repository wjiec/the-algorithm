package problem.p459repeatedsubstringpattern;

/**
 * 459. Repeated Substring Pattern
 *
 * https://leetcode-cn.com/problems/repeated-substring-pattern/
 *
 * Given a string s, check if it can be constructed by taking a substring
 * of it and appending multiple copies of the substring together.
 */

public class Solution {

    public boolean repeatedSubstringPattern(String s) {
        int sz = s.length();
        for (int i = 1; i * 2 <= sz; i++) {
            if (sz % i == 0) {
                boolean ok = true;
                for (int j = i; j < sz; j++) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().repeatedSubstringPattern("ababab");
        assert new Solution().repeatedSubstringPattern("abab");
        assert new Solution().repeatedSubstringPattern("aaa");
        assert new Solution().repeatedSubstringPattern("abcabcabcabc");
        assert new Solution().repeatedSubstringPattern("ababababababcababababababc");
        assert !new Solution().repeatedSubstringPattern("ababababababcababababababd");
        assert !new Solution().repeatedSubstringPattern("cbcc");
        assert !new Solution().repeatedSubstringPattern("abcd");
        assert !new Solution().repeatedSubstringPattern("aba");
        assert !new Solution().repeatedSubstringPattern("abaabac");
        assert new Solution().repeatedSubstringPattern("abcabcabc");
        assert !new Solution().repeatedSubstringPattern("abcadcabc");
        assert new Solution().repeatedSubstringPattern("abcdeabcdeabcde");
    }

}
