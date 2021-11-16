package problem.p1832checkifthesentenceispangram;

/**
 * 1832. Check if the Sentence Is Pangram
 *
 * https://leetcode-cn.com/problems/check-if-the-sentence-is-pangram/
 *
 * A pangram is a sentence where every letter of the English alphabet appears at least once.
 *
 * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram,
 * or false otherwise.
 */

public class Solution {

    public boolean checkIfPangram(String sentence) {
        if (sentence.length() < 26) return false;
        int count = 26;
        boolean[] map = new boolean[26];
        for (var c : sentence.toCharArray()) {
            if (!map[c - 'a']) {
                map[c - 'a'] = true;
                if (--count == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().checkIfPangram("thequickbrownfoxjumpsoverthelazydog");
        assert !new Solution().checkIfPangram("leetcode");
    }

}
