package problem.p1807evaluatethebracketpairsofastring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1807. Evaluate the Bracket Pairs of a String
 *
 * https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string/
 *
 * You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
 *
 * For example, in the string "(name)is(age)yearsold", there are two
 * bracket pairs that contain the keys "name" and "age".
 * You know the values of a wide range of keys. This is represented by a 2D string
 * array knowledge where each knowledge[i] = [keyi, valuei] indicates that key keyi
 * has a value of valuei.
 *
 * You are tasked to evaluate all of the bracket pairs. When you evaluate a bracket pair
 * that contains some key keyi, you will:
 *
 * Replace keyi and the bracket pair with the key's corresponding valuei.
 * If you do not know the value of the key, you will replace keyi and the bracket pair
 * with a question mark "?" (without the quotation marks).
 * Each key will appear at most once in your knowledge. There will not be any nested
 * brackets in s.
 *
 * Return the resulting string after evaluating all of the bracket pairs.
 */

public class Solution {

    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (var k : knowledge) map.put(k.get(0), k.get(1));

        boolean bracket = false;
        StringBuilder k = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        for (var c : s.toCharArray()) {
            switch (c) {
                case '(' -> bracket = true;
                case ')' -> {
                    bracket = false;
                    ans.append(map.getOrDefault(k.toString(), "?"));
                    k = new StringBuilder();
                }
                default -> {
                    if (bracket) k.append(c);
                    else ans.append(c);
                }
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        assert new Solution().evaluate("(name)is(age)yearsold", List.of(
            List.of("name","bob"), List.of("age","two")
        )).equals("bobistwoyearsold");

        assert new Solution().evaluate("hi(name)", List.of(
            List.of("a","b")
        )).equals("hi?");

        assert new Solution().evaluate("(a)(a)(a)aaa", List.of(
            List.of("a","yes")
        )).equals("yesyesyesaaa");
    }

}
