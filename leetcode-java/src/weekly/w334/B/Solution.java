package weekly.w334.B;

/**
 * 2575. Find the Divisibility Array of a String
 *
 * https://leetcode.cn/problems/find-the-divisibility-array-of-a-string/
 *
 * You are given a 0-indexed string word of length n consisting of digits, and a positive integer m.
 *
 * The divisibility array div of word is an integer array of length n such that:
 *
 * div[i] = 1 if the numeric value of word[0,...,i] is divisible by m, or
 * div[i] = 0 otherwise.
 *
 * Return the divisibility array of word.
 */

public class Solution {

    public int[] divisibilityArray(String word, int m) {
        long curr = 0; int idx = 0;
        int[] ans = new int[word.length()];
        for (var c : word.toCharArray()) {
            curr = curr * 10 + (c - '0');
            ans[idx++] = curr % m == 0 ? 1 : 0;
            curr %= m;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
