package weekly.bw52.p0p5742sortingthesentence;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 5742. Sorting the Sentence
 *
 * https://leetcode-cn.com/contest/biweekly-contest-52/problems/sorting-the-sentence/
 *
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * Each word consists of lowercase and uppercase English letters.
 *
 * A sentence can be shuffled by appending the 1-indexed word position
 * to each word then rearranging the words in the sentence.
 *
 * For example, the sentence "This is a sentence" can be shuffled as
 * "sentence4 a3 is2 This1" or "is2 sentence4 This1 a3".
 * Given a shuffled sentence s containing no more than 9 words,
 * reconstruct and return the original sentence.
 */


public class Solution {

    public String sortSentence(String s) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> mapping = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '1' && c <= '9') {
                mapping.put(c - '0', sb.toString());
                sb = new StringBuilder();
            } else if ((c >= 'a' && c <= 'z') || c >= 'A' && c <= 'Z') {
                sb.append(c);
            }
        }
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 1; i < 10; i++) {
            if (mapping.containsKey(i)) {
                sj.add(mapping.get(i));
            }
        }

        return sj.toString();
    }

    public static void main(String[] args) {
        assert new Solution().sortSentence("sentence4 a3 is2 This1").equals("This is a sentence");
    }

}
