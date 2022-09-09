package problem.p1813sentencesimilarityiii;

/**
 * 1813. Sentence Similarity III
 *
 * https://leetcode.cn/problems/sentence-similarity-iii/
 *
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
 * Words consist of only uppercase and lowercase English letters.
 *
 * Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary
 * sentence (possibly empty) inside one of these sentences such that the two sentences become equal.
 * For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal
 * by inserting "my name is" between "Hello" and "Jane" in sentence2.
 *
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar.
 * Otherwise, return false.
 */

public class Solution {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.length() == sentence2.length()) {
            return sentence1.equals(sentence2);
        }
        if (sentence1.length() < sentence2.length()) {
            return areSentencesSimilar(sentence2, sentence1);
        }

        // ensure always sentence1.length() > sentence2.length()
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");

        int n1 = words1.length, n2 = words2.length, l1 = 0, l2 = 0;
        while (l2 < n2 && words1[l1].equals(words2[l2])) { l1++; l2++; }
        if (l2 == n2) return true; // 前缀相同
        // 剩下的要么后缀相同, 否则就错误
        l1 = Math.max(0, n1 - (n2 - l2));
        while (l2 < n2 && words1[l1].equals(words2[l2])) { l1++; l2++; }
        return l1 == n1 && l2 == n2;
    }

    public static void main(String[] args) {
        assert !new Solution().areSentencesSimilar("a A", "aAAa");

        assert new Solution().areSentencesSimilar("My name is Haley", "My Haley");
        assert !new Solution().areSentencesSimilar("of", "A lot of words");
        assert new Solution().areSentencesSimilar("Eating right now", "Eating");
    }

}
