package daily.d230307p1096braceexpansionii;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 1096. Brace Expansion II
 *
 * https://leetcode.cn/problems/brace-expansion-ii/
 *
 * Under the grammar given below, strings can represent a set of lowercase words.
 * Let R(expr) denote the set of words the expression represents.
 *
 * The grammar can best be understood through simple examples:
 *
 * Single letters represent a singleton set containing that word.
 * R("a") = {"a"}
 * R("w") = {"w"}
 * When we take a comma-delimited list of two or more expressions, we take the union of possibilities.
 * R("{a,b,c}") = {"a","b","c"}
 * R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
 * When we concatenate two expressions, we take the set of possible concatenations between two words
 * where the first word comes from the first expression and the second word comes from the second expression.
 * R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
 * R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
 * Formally, the three rules for our grammar:
 *
 * For every lowercase letter x, we have R(x) = {x}.
 * For expressions e1, e2, ... , ek with k >= 2, we have R({e1, e2, ...}) = R(e1) ∪ R(e2) ∪ ...
 * For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)},
 * where + denotes concatenation, and × denotes the cartesian product.
 *
 * Given an expression representing a set of words under the given grammar, return the
 * sorted list of words that the expression represents.
 */

public class Solution {

    // R("a") = {"a"}
    // R("{a,b,c}") = {"a","b","c"}
    // R("{{a,b},{b,c}}") = {"a","b","c"}
    // R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
    public List<String> braceExpansionII(String expression) {
        return new ArrayList<>(expansion(expression));
    }

    private TreeSet<String> expansion(String exp) {
        int rBrace = 0, n = exp.length();
        while (rBrace < n && exp.charAt(rBrace) != '}') rBrace++;
        if (rBrace == n) return new TreeSet<>(Set.of(exp));

        int lBrace = rBrace;
        while (exp.charAt(lBrace) != '{') lBrace--;

        // a {curr} c
        String a = exp.substring(0, lBrace);
        String b = exp.substring(lBrace + 1, rBrace);
        String c = exp.substring(rBrace + 1);
        TreeSet<String> ans = new TreeSet<>();
        for (var bi : b.split(",")) {
            ans.addAll(expansion(a + bi + c));
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().braceExpansionII("a"));
        PrettyPrinter.println(new Solution().braceExpansionII("aba"));
        PrettyPrinter.println(new Solution().braceExpansionII("{a}"));
        PrettyPrinter.println(new Solution().braceExpansionII("{{a,b},{b,c}}"));
        PrettyPrinter.println(new Solution().braceExpansionII("{a,b}{c,{d,e}}"));
        PrettyPrinter.println(new Solution().braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
    }

}
