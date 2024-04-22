package weekly.w394.B;

import java.util.Arrays;

/**
 * 100291. Count the Number of Special Characters II
 *
 * https://leetcode.cn/contest/weekly-contest-394/problems/count-the-number-of-special-characters-ii/
 *
 * You are given a string word. A letter c is called special if it appears both in lowercase
 * and uppercase in word, and every lowercase occurrence of c appears before the first
 * uppercase occurrence of c.
 *
 * Return the number of special letters in word.
 */

public class Solution {

    public int numberOfSpecialChars(String word) {
        int[] pos = new int[128];
        Arrays.fill(pos, -1);

        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char curr = chars[i];
            if ('a' <= curr && curr <= 'z') {
                pos[curr] = i;
            } else if (pos[curr] == -1) {
                pos[curr] = i;
            }
        }

        int ans = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (pos[c] != -1 && pos[c - 32] != -1 && pos[c] < pos[c - 32]) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
