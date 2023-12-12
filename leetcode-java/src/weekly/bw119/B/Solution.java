package weekly.bw119.B;

/**
 * 2957. Remove Adjacent Almost-Equal Characters
 *
 * https://leetcode.cn/contest/biweekly-contest-119/problems/remove-adjacent-almost-equal-characters/
 *
 * You are given a 0-indexed string word.
 *
 * In one operation, you can pick any index i of word and change word[i] to any lowercase English letter.
 *
 * Return the minimum number of operations needed to remove all adjacent almost-equal characters from word.
 *
 * Two characters a and b are almost-equal if a == b or a and b are adjacent in the alphabet.
 */

public class Solution {

    public int removeAlmostEqualCharacters(String word) {
        return dfs(word.toCharArray(), 0, '\0');
    }

    private int dfs(char[] chars, int i, char prev) {
        if (i == chars.length) return 0;
        if (Math.abs(prev - chars[i]) > 1) return dfs(chars, i + 1, chars[i]);
        return 1 + dfs(chars, i + 1, '\0');
    }

    public static void main(String[] args) {
    }

}
