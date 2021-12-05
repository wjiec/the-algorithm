package problem.p2042checkifnumbersareascendinginasentence;

/**
 * 2042. Check if Numbers Are Ascending in a Sentence
 *
 * https://leetcode-cn.com/problems/check-if-numbers-are-ascending-in-a-sentence/
 *
 * A sentence is a list of tokens separated by a single space with no leading or trailing spaces.
 *
 * Every token is either a positive number consisting of digits 0-9 with no leading zeros,
 * or a word consisting of lowercase English letters.
 *
 * For example, "a puppy has 2 eyes 4 legs" is a sentence with seven tokens:
 *      "2" and "4" are numbers and the other tokens such as "puppy" are words.
 *
 * Given a string s representing a sentence, you need to check if
 * all the numbers in s are strictly increasing from left to right
 * (i.e., other than the last number,
 * each number is strictly smaller than the number on its right in s).
 *
 * Return true if so, or false otherwise.
 */

public class Solution {

    public boolean areNumbersAscending(String s) {
        int prev = 0, curr = 0;
        for (var c : s.toCharArray()) {
            if ('0' <= c && c <= '9') {
                curr = curr * 10 + (c - '0');
            } else if (curr != 0) {
                if (curr <= prev) return false;
                prev = curr;
                curr = 0;
            }
        }
        return curr == 0 || curr > prev;
    }

    public static void main(String[] args) {
        assert new Solution().areNumbersAscending("1 box has 3 blue 4 red 6 green and 12 yellow marbles");
        assert !new Solution().areNumbersAscending("hello world 5 x 5");
        assert !new Solution().areNumbersAscending("sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s");
        assert new Solution().areNumbersAscending("4 5 11 26");
    }

}
