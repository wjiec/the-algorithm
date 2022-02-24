package problem.p408validwordabbreviation;

/**
 * 408. Valid Word Abbreviation
 *
 * https://leetcode-cn.com/problems/valid-word-abbreviation/
 *
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths.
 * The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 *
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

public class Solution {

    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, n = word.length(), num = 0;
        for (var c : abbr.toCharArray()) {
            if ('0' <= c && c <= '9') {
                if (num == 0 && c == '0') {
                    return false;
                }
                num = num * 10 + (c - '0');
                continue;
            }

            if (num != 0) i += num;
            if (i >= n || word.charAt(i++) != c) {
                return false;
            }
            num = 0;
        }
        return i + num == n;
    }

    public static void main(String[] args) {
        assert !new Solution().validWordAbbreviation("hi", "2i");

        assert new Solution().validWordAbbreviation("internationalization", "i12iz4n");
        assert !new Solution().validWordAbbreviation("apple", "a2e");
        assert new Solution().validWordAbbreviation("a", "1");
        assert new Solution().validWordAbbreviation("app", "a2");
    }

}
