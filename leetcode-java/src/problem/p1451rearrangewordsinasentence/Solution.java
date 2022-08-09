package problem.p1451rearrangewordsinasentence;

import java.util.ArrayList;
import java.util.List;

/**
 * 1451. Rearrange Words in a Sentence
 *
 * https://leetcode.cn/problems/rearrange-words-in-a-sentence/
 *
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 *
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are rearranged in an increasing
 * order of their lengths. If two words have the same length, arrange them in their original order.
 *
 * Return the new text following the format shown above.
 */

public class Solution {

    private record Word(char[] chars, int idx) {}

    public String arrangeWords(String text) {
        List<Word> words = new ArrayList<>();
        for (var word : text.split(" ")) {
            char[] chars = word.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            words.add(new Word(chars, words.size()));
        }

        words.sort((a, b) -> {
            if (a.chars.length == b.chars.length) return a.idx - b.idx;
            return a.chars.length - b.chars.length;
        });

        StringBuilder sb = new StringBuilder();
        for (var word : words) {
            if (sb.length() == 0) {
                word.chars[0] = Character.toUpperCase(word.chars[0]);
            } else sb.append(" ");
            sb.append(word.chars);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().arrangeWords("Leetcode is cool").equals("Is cool leetcode");
        assert new Solution().arrangeWords("Keep calm and code on").equals("On and keep calm code");
        assert new Solution().arrangeWords("To be or not to be").equals("To be or to be not");
    }

}
