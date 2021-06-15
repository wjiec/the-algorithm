package problem.p884uncommonwordsfromtwosentences;

import java.util.*;

/**
 * 884. Uncommon Words from Two Sentences
 *
 * https://leetcode-cn.com/problems/uncommon-words-from-two-sentences/
 *
 * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
 */

public class Solution {

    public String[] uncommonFromSentences(String s1, String s2) {
        return unique(s1 + " " + s2).toArray(new String[]{});
    }

    private List<String> unique(String s) {
        Set<String> unique = new HashSet<>();
        Map<String, Integer> words = new HashMap<>();
        for (var word : s.split(" ")) {
            if (words.putIfAbsent(word, 1) == null) {
                unique.add(word);
            } else {
                unique.remove(word);
            }
        }
        return new ArrayList<>(unique);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().uncommonFromSentences("s z z z s", "s z ejt")));
        System.out.println(Arrays.toString(new Solution().uncommonFromSentences("this apple is sweet", "this apple is sour")));
        System.out.println(Arrays.toString(new Solution().uncommonFromSentences("apple apple", "banana")));
    }

}
