package weekly.w394.A;

/**
 * 100294. Count the Number of Special Characters I
 *
 * https://leetcode.cn/contest/weekly-contest-394/problems/count-the-number-of-special-characters-i/
 *
 * You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
 *
 * Return the number of special letters in word.
 */

public class Solution {

    public int numberOfSpecialChars(String word) {
        boolean[] chars = new boolean[128];
        for (var c : word.toCharArray()) chars[c] = true;

        int ans = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (chars[c] && chars[c - 32]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
