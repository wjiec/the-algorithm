package problem.p2047numberofvalidwordsinasentence;

/**
 * 2047. Number of Valid Words in a Sentence
 *
 * https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence/
 *
 * A sentence consists of lowercase letters ('a' to 'z'), digits ('0' to '9'), hyphens ('-'),
 * punctuation marks ('!', '.', and ','), and spaces (' ') only.
 *
 * Each sentence can be broken down into one or more tokens separated by one or more spaces ' '.
 *
 * A token is a valid word if all three of the following are true:
 *
 * It only contains lowercase letters, hyphens, and/or punctuation (no digits).
 *
 * There is at most one hyphen '-'. If present, it must be surrounded by lowercase characters
 * ("a-b" is valid, but "-ab" and "ab-" are not valid).
 *
 * There is at most one punctuation mark. If present, it must be at the end of the token
 * ("ab,", "cd!", and "." are valid, but "a!b" and "c.," are not valid).
 *
 * Examples of valid words include "a-b.", "afad", "ba-c", "a!", and "!".
 *
 * Given a string sentence, return the number of valid words in sentence.
 */

public class Solution {

    public int countValidWords(String sentence) {
        int ans = 0;
        for (var word : sentence.split("\\s+")) {
            if (word.length() != 0 && word.matches("^[a-z]*([a-z]-[a-z]+)?[.,!]?$")) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countValidWords(" o6 t") == 1;
        assert new Solution().countValidWords("!") == 1;

        assert new Solution().countValidWords("cat and  dog") == 3;
        assert new Solution().countValidWords("!this  1-s b8d!") == 0;
        assert new Solution().countValidWords("alice and  bob are playing stone-game10") == 5;
        assert new Solution().countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener.") == 6;
    }

}
