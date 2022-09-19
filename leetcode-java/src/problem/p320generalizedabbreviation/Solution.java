package problem.p320generalizedabbreviation;

import common.Checker;
import common.PrettyPrinter;

import java.util.*;

/**
 * 320. Generalized Abbreviation
 *
 * https://leetcode.cn/problems/generalized-abbreviation/
 *
 * A word's generalized abbreviation can be constructed by taking any number of
 * non-overlapping and non-adjacent substrings and replacing them
 * with their respective lengths.
 *
 * For example, "abcde" can be abbreviated into:
 * "a3e" ("bcd" turned into "3")
 * "1bcd1" ("a" and "e" both turned into "1")
 * "5" ("abcde" turned into "5")
 * "abcde" (no substrings replaced)
 *
 * However, these abbreviations are invalid:
 * "23" ("ab" turned into "2" and "cde" turned into "3") is invalid as the substrings chosen are adjacent.
 * "22de" ("ab" turned into "2" and "bc" turned into "2") is invalid as the substring chosen overlap.
 *
 * Given a string word, return a list of all the possible generalized abbreviations of word.
 *
 * Return the answer in any order.
 */

public class Solution {

    private final Map<String, List<String>> memo = new HashMap<>();

    public List<String> generateAbbreviations(String word) {
        if (word.length() == 0) return List.of();
        if (word.length() == 1) return List.of("1", word);
        if (memo.containsKey(word)) return memo.get(word);

        Set<String> set = new HashSet<>();
        set.add(String.valueOf(word.length()));

        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            List<String> ps = generateAbbreviations(prefix);
            List<String> ss = generateAbbreviations(suffix);
            for (var sub : ps) set.add(sub + suffix);
            for (var sub : ss) set.add(prefix + sub);
            for (var p : ps) {
                boolean pd = lastDigit(p);
                for (var s : ss) {
                    if (pd && firstDigit(s)) continue;
                    set.add(p + s);
                }
            }
        }

        List<String> ans = new ArrayList<>(set);
        memo.put(word, ans);
        return ans;
    }

    private boolean lastDigit(String text) {
        return Character.isDigit(text.charAt(text.length() - 1));
    }

    private boolean firstDigit(String text) {
        return Character.isDigit(text.charAt(0));
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().generateAbbreviations("segmentation"));

        assert Checker.anyOrder(new Solution().generateAbbreviations("word"), List.of(
            "4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"
        ));

        assert Checker.anyOrder(new Solution().generateAbbreviations("a"), List.of(
            "1","a"
        ));
    }

}
