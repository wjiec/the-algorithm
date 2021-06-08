package problem.p748shortestcompletingword;


/**
 * 748. Shortest Completing Word
 *
 * https://leetcode-cn.com/problems/shortest-completing-word/
 *
 * Given a string licensePlate and an array of strings words, find the shortest completing word in words.
 *
 * A completing word is a word that contains all the letters in licensePlate.
 * Ignore numbers and spaces in licensePlate, and treat letters as case insensitive.
 * If a letter appears more than once in licensePlate,
 * then it must appear in the word the same number of times or more.
 *
 * For example, if licensePlate = "aBc 12c", then it contains letters 'a', 'b' (ignoring case),
 * and 'c' twice. Possible completing words are "abccdef", "caaacab", and "cbca".
 *
 * Return the shortest completing word in words. It is guaranteed an answer exists.
 * If there are multiple shortest completing words, return the first one that occurs in words.
 */

public class Solution {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] std = letters(licensePlate);
        String ans = "";
        for (var word : words) {
            if ((word.length() < ans.length() || ans.length() == 0) && check(std, letters(word))) {
                ans = word;
            }
        }
        return ans;
    }

    private boolean check(int[] l, int[] r) {
        for (int i = 0; i < 26; i++) {
            if (l[i] > r[i]) return false;
        }
        return true;
    }

    private int[] letters(String s) {
        int[] letters = new int[26];
        for (var c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') c += 32;
            if (c >= 'a' && c <= 'z') letters[c - 'a']++;
        }
        return letters;
    }

    public static void main(String[] args) {
        assert new Solution().shortestCompletingWord("1s3 PSt", new String[]{
            "step", "steps", "stripe", "stepple"
        }).equals("steps");
        assert new Solution().shortestCompletingWord("1s3 456", new String[]{
            "looks", "pest", "stew", "show"
        }).equals("pest");
        assert new Solution().shortestCompletingWord("Ah71752", new String[]{
            "suggest","letter","of","husband","easy","education","drug","prevent","writer","old"
        }).equals("husband");
        assert new Solution().shortestCompletingWord("iMSlpe4", new String[]{
            "claim","consumer","student","camera","public","never","wonder","simple","thought","use"
        }).equals("simple");
    }

}
