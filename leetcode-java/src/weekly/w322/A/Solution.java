package weekly.w322.A;

/**
 * 6253. Circular Sentence
 *
 * https://leetcode.cn/contest/weekly-contest-322/problems/circular-sentence/
 *
 * A sentence is a list of words that are separated by a single space
 * with no leading or trailing spaces.
 *
 * For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
 * Words consist of only uppercase and lowercase English letters. Uppercase and
 * lowercase English letters are considered different.
 *
 * A sentence is circular if:
 *
 * The last character of a word is equal to the first character of the next word.
 * The last character of the last word is equal to the first character of the first word.
 * For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all
 * circular sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode"
 * and "I like Leetcode" are not circular sentences.
 *
 * Given a string sentence, return true if it is circular. Otherwise, return false.
 */

public class Solution {

    public boolean isCircularSentence(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 1; i < words.length; i++) {
            char curr = words[i].charAt(0);
            char last = words[i - 1].charAt(words[i - 1].length() - 1);
            if (curr != last) return false;
        }

        int n = words.length;
        char last = words[n - 1].charAt(words[n - 1].length() - 1);
        return last == words[0].charAt(0);
    }

    public static void main(String[] args) {
    }

}
