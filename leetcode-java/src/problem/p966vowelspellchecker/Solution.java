package problem.p966vowelspellchecker;

import java.util.*;

/**
 * 966. Vowel Spellchecker
 *
 * https://leetcode.cn/problems/vowel-spellchecker/
 *
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive),
 * then the query word is returned with the same case as the case in the wordlist.
 *
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 *
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word
 * with any vowel individually, it matches a word in the wordlist (case-insensitive), then
 * the query word is returned with the same case as the match in the wordlist.
 *
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 *
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 */

public class Solution {

    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> matches = new HashSet<>();
        Map<String, String> ignoreCases = new HashMap<>();
        Map<String, String> ignoreVowel = new HashMap<>();

        for (var word : wordlist) {
            matches.add(word);
            ignoreCases.putIfAbsent(word.toLowerCase(), word);
            ignoreVowel.putIfAbsent(replaceVowel(word), word);
        }

        String[] ans = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (matches.contains(queries[i])) ans[i] = queries[i];
            if (ans[i] == null) ans[i] = ignoreCases.get(queries[i].toLowerCase());
            if (ans[i] == null) ans[i] = ignoreVowel.getOrDefault(replaceVowel(queries[i]), "");
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }

    private String replaceVowel(String s) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) sb.append(isVowel(Character.toLowerCase(c)) ? '*' : Character.toLowerCase(c));
        return sb.toString();
    }

    private boolean isVowel(char c) { return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'; }

    public static void main(String[] args) {
        assert Arrays.equals(new Solution().spellchecker(new String[]{"KiTe","kite","hare","Hare"}, new String[]{
            "kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"
        }), new String[]{
            "kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"
        });

        assert Arrays.equals(new Solution().spellchecker(new String[]{"yellow"}, new String[]{
            "YellOw"
        }), new String[]{
            "yellow"
        });

        assert Arrays.equals(new Solution().spellchecker(new String[]{"YellOw"}, new String[]{
            "yollow"
        }), new String[]{
            "YellOw"
        });
    }

}
