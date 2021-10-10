package problem.p1455checkifawordoccursasaprefixofanywordinasentence;

/**
 * 1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence
 *
 * https://leetcode-cn.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
 *
 * Given a sentence that consists of some words separated by a single space, and a searchWord,
 * check if searchWord is a prefix of any word in sentence.
 *
 * Return the index of the word in sentence (1-indexed) where searchWord is a prefix of this word.
 *
 * If searchWord is a prefix of more than one word, return the index of the first word (minimum index).
 *
 * If there is no such word return -1.
 *
 * A prefix of a string s is any leading contiguous substring of s.
 */

public class Solution {

    public int isPrefixOfWord(String sentence, String searchWord) {
        var words = sentence.trim().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(searchWord) == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().isPrefixOfWord("i love eating burger", "burg") == 4;
        assert new Solution().isPrefixOfWord("this problem is an easy problem", "pro") == 2;
        assert new Solution().isPrefixOfWord("i am tired", "you") == -1;
        assert new Solution().isPrefixOfWord("i use triple pillow", "pill") == 4;
        assert new Solution().isPrefixOfWord("hello from the other side", "they") == -1;
    }

}
