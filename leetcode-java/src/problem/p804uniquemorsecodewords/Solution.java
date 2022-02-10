package problem.p804uniquemorsecodewords;

import java.util.HashSet;
import java.util.Set;

/**
 * 804. Unique Morse Code Words
 *
 * https://leetcode-cn.com/problems/unique-morse-code-words/
 *
 * International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes,
 * as follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.
 *
 * Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter.
 * For example, "cab" can be written as "-.-..--...", (which is the concatenation "-.-." + ".-" + "-...").
 * We'll call such a concatenation, the transformation of a word.
 *
 * Return the number of different transformations among all words we have.
 */

public class Solution {

    private String[] MORSE = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
        "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
    };

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> unique = new HashSet<>();
        for (var s : words) {
            StringBuilder sb = new StringBuilder();
            for (var c : s.toCharArray()) {
                sb.append(MORSE[c - 'a']);
            }
            unique.add(sb.toString());
        }

        return unique.size();
    }

    public static void main(String[] args) {
        assert new Solution().uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}) == 2;
    }

}
