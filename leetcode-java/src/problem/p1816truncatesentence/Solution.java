package problem.p1816truncatesentence;

/**
 * 1816. Truncate Sentence
 *
 * https://leetcode-cn.com/problems/truncate-sentence/
 *
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 *
 * Each of the words consists of only uppercase and lowercase English letters (no punctuation).
 *
 * For example, "Hello World", "HELLO", and "hello world hello world" are all sentences.
 *
 * You are given a sentence s and an integer k.
 *
 * You want to truncate s such that it contains only the first k words.
 *
 * Return s after truncating it.
 */

public class Solution {

    public String truncateSentence(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            if (c == ' ' && --k == 0) break;
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().truncateSentence("Hello how are you Contestant", 4).equals("Hello how are you");
        assert new Solution().truncateSentence("What is the solution to this problem", 4).equals("What is the solution");
        assert new Solution().truncateSentence("chopper is not a tanuki", 5).equals("chopper is not a tanuki");
    }

}
