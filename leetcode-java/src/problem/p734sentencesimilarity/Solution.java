package problem.p734sentencesimilarity;

import java.util.*;

/**
 * 734. Sentence Similarity
 *
 * https://leetcode-cn.com/problems/sentence-similarity/
 *
 * We can represent a sentence as an array of words, for example,
 * the sentence "I am happy with leetcode" can be
 * represented as arr = ["I","am",happy","with","leetcode"].
 *
 * Given two sentences sentence1 and sentence2 each represented as a string array and
 * given an array of string pairs similarPairs where similarPairs[i] = [xi, yi]
 * indicates that the two words xi and yi are similar.
 *
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 *
 * Two sentences are similar if:
 *
 * They have the same length (i.e., the same number of words)
 * sentence1[i] and sentence2[i] are similar.
 * Notice that a word is always similar to itself, also notice that the similarity
 * relation is not transitive. For example, if the words a and b are similar,
 * and the words b and c are similar, a and c are not necessarily similar.
 */

public class Solution {

    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;
        Map<String, Set<String>> map = new HashMap<>();
        for (var pair : similarPairs) {
            if (!map.containsKey(pair.get(0))) map.put(pair.get(0), new HashSet<>());
            if (!map.containsKey(pair.get(1))) map.put(pair.get(1), new HashSet<>());

            map.get(pair.get(0)).add(pair.get(1));
            map.get(pair.get(1)).add(pair.get(0));
        }

        for (int i = 0; i < sentence1.length; i++) {
            if (sentence1[i].equals(sentence2[i])) continue;
            if (map.containsKey(sentence1[i]) && map.get(sentence1[i]).contains(sentence2[i])) continue;
            if (map.containsKey(sentence2[i]) && map.get(sentence2[i]).contains(sentence1[i])) continue;

            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().areSentencesSimilar(new String[]{
            "great","acting","skills"
        }, new String[]{
            "fine","drama","talent"
        }, List.of(
            List.of("great","fine"),
            List.of("drama","acting"),
            List.of("skills","talent")
        ));

        assert new Solution().areSentencesSimilar(new String[]{"great"},
            new String[]{"great"}, List.of());

        assert !new Solution().areSentencesSimilar(new String[]{"great"},
            new String[]{"doubleplus","good"}, List.of(List.of("great","doubleplus")));
    }

}
