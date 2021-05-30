package weekly.w243.p0p5772checkifwordequalssummationoftwowords;

/**
 * 5772. Check if Word Equals Summation of Two Words
 *
 * https://leetcode-cn.com/contest/weekly-contest-243/problems/check-if-word-equals-summation-of-two-words/
 *
 * The letter value of a letter is its position in the alphabet
 * starting from 0 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, etc.).
 *
 * The numerical value of some string of lowercase English letters s is
 * the concatenation of the letter values of each letter in s,
 * which is then converted into an integer.
 *
 * For example, if s = "acb", we concatenate each letter's letter value,
 * resulting in "021". After converting it, we get 21.
 * You are given three strings firstWord, secondWord, and targetWord,
 * each consisting of lowercase English letters 'a' through 'j' inclusive.
 *
 * Return true if the summation of the numerical values of firstWord and
 * secondWord equals the numerical value of targetWord, or false otherwise.
 */

public class Solution {

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return toInt(firstWord) + toInt(secondWord) == toInt(targetWord);
    }

    private int toInt(String s) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            sb.append(c - 'a');
        }
        return Integer.valueOf(sb.toString(), 10);
    }

    public static void main(String[] args) {
        assert new Solution().isSumEqual("j", "j", "bi");
        assert new Solution().isSumEqual("acb", "cba", "cdb");
        assert !new Solution().isSumEqual("aaa", "a", "aab");
        assert new Solution().isSumEqual("aaa", "a", "aaaa");
    }

}
