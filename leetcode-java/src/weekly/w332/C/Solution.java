package weekly.w332.C;

import common.PrettyPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * 6356. Substring XOR Queries
 *
 * https://leetcode.cn/contest/weekly-contest-332/problems/substring-xor-queries/
 *
 * You are given a binary string s, and a 2D integer array queries
 * where queries[i] = [firsti, secondi].
 *
 * For the ith query, find the shortest substring of s whose decimal
 * value, val, yields secondi when bitwise XORed with firsti.
 *
 * In other words, val ^ firsti == secondi.
 *
 * The answer to the ith query is the endpoints (0-indexed) of the
 * substring [lefti, righti] or [-1, -1] if no such substring exists.
 *
 * If there are multiple answers, choose the one with the minimum lefti.
 *
 * Return an array ans where ans[i] = [lefti, righti] is the answer to the ith query.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

public class Solution {

    public int[][] substringXorQueries(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        Map<Integer, Integer>[] maps = new Map[32]; maps[0] = new HashMap<>();
        for (int i = 1; i < maps.length; i++) {
            maps[i] = new HashMap<>();
            int maxLength = 1 << i;

            int curr = 0, mask = (1 << i) - 1;
            for (int j = 0; j < chars.length; j++) {
                curr = (curr << 1 | (chars[j] - '0')) & mask;
                if (j + 1 >= i) maps[i].putIfAbsent(curr, j - i + 1);
                if (maps[i].size() == maxLength) break;
            }
        }

        int[][] ans = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            ans[i][0] = ans[i][1] = -1;
            int val = queries[i][0] ^ queries[i][1];
            int bit = Integer.toBinaryString(val).length();
            if (maps[bit].containsKey(val)) {
                ans[i][0] = maps[bit].get(val);
                ans[i][1] = ans[i][0] + bit - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().substringXorQueries("101101", new int[][]{{0,5},{1,2}}));
    }

}
